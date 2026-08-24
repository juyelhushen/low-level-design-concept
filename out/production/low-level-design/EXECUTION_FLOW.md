# CAS-Based tryUse() Execution Flow

## Scenario Setup
- Coupon with `maxUsageCount = 3`
- `usageCount` (AtomicInteger) starts at `0`

---

## Single Thread Execution (Normal Case)

### Call 1: tryUse()
```
START: usageCount = 0

Step 1: current = usageCount.get()
        → current = 0

Step 2: if (current >= maxUsageCount)  // 0 >= 3?
        → NO, continue

Step 3: compareAndSet(current=0, current+1=1)
        → Compare: Is usageCount still 0? YES ✓
        → Set usageCount = 1
        → Return TRUE (CAS succeeded)

END: usageCount = 1, returns TRUE (coupon can be used)
```

### Call 2: tryUse()
```
START: usageCount = 1

Step 1: current = usageCount.get()
        → current = 1

Step 2: if (current >= maxUsageCount)  // 1 >= 3?
        → NO, continue

Step 3: compareAndSet(current=1, current+1=2)
        → Compare: Is usageCount still 1? YES ✓
        → Set usageCount = 2
        → Return TRUE

END: usageCount = 2, returns TRUE
```

### Call 3: tryUse()
```
START: usageCount = 2

Step 1: current = usageCount.get()
        → current = 2

Step 2: if (current >= maxUsageCount)  // 2 >= 3?
        → NO, continue

Step 3: compareAndSet(current=2, current+1=3)
        → Compare: Is usageCount still 2? YES ✓
        → Set usageCount = 3
        → Return TRUE

END: usageCount = 3, returns TRUE
```

### Call 4: tryUse() (Exceeds limit)
```
START: usageCount = 3

Step 1: current = usageCount.get()
        → current = 3

Step 2: if (current >= maxUsageCount)  // 3 >= 3?
        → YES! ✓ Coupon limit reached
        → Return FALSE immediately

END: usageCount = 3 (unchanged), returns FALSE ❌
```

---

## Concurrent Execution (Race Condition Handling)

### Scenario: Two threads call tryUse() simultaneously
```
Initial State: usageCount = 2, maxUsageCount = 3

╔════════════════════════════════════════════════════════════╗
║                    THREAD-A           THREAD-B            ║
╚════════════════════════════════════════════════════════════╝

T1  current_A = get() → 2    |  current_B = get() → 2
    (Both read same value!)

T2  if (2 >= 3)? NO           |  if (2 >= 3)? NO
    (Both pass check)

T3  compareAndSet(2, 3)       |  compareAndSet(2, 3)
    ✓ CAS SUCCEEDS            |  ✗ CAS FAILS
    usageCount = 3            |  (usageCount was changed by A)
    return TRUE               |  (Continue loop)

T4                           |  Loop back to Step 1
                             |  current_B = get() → 3
                             |  
T5                           |  if (3 >= 3)? YES
                             |  return FALSE
                             |  (Coupon limit now reached)

RESULT:
- Thread-A: TRUE (used coupon successfully)
- Thread-B: FALSE (coupon limit exceeded)
- usageCount: 3 (correct! Not 4)
```

---

## Key Execution Points

### ✅ CAS Succeeds
```
Thread reads:    usageCount = 5
Thread compares: Is usageCount still 5?  → YES
Thread updates:  usageCount = 6
Result:          Exit loop, return TRUE
```

### ❌ CAS Fails (Retry Loop)
```
Thread-A reads:  usageCount = 5
Thread-B reads:  usageCount = 5
Thread-B does:   compareAndSet(5, 6) → SUCCESS, usageCount = 6
Thread-A does:   compareAndSet(5, 6) → FAIL! (usageCount is now 6)
                 (Loop continues)
                 current = get() → 6
                 compareAndSet(6, 7) → SUCCESS, usageCount = 7
Result:          Thread-A retries and succeeds on 2nd attempt
```

---

## Why This Matters

### Without CAS (Simple increment - ❌ WRONG):
```
Thread-A: Read usageCount = 2
          Increment: 2 + 1 = 3
          Write usageCount = 3

Thread-B: Read usageCount = 2 (Race!)
          Increment: 2 + 1 = 3
          Write usageCount = 3

Result: Both threads think they used the coupon,
        but usageCount only increased by 1!
        SECURITY ISSUE: Coupon overused!
```

### With CAS (Compare-And-Swap - ✅ CORRECT):
```
Thread-A: Read usageCount = 2
          CAS(2 → 3) → SUCCESS
          usageCount = 3

Thread-B: Read usageCount = 2
          CAS(2 → 3) → FAIL (A already changed it)
          Retry: Read usageCount = 3
          CAS(3 → 4) → FAIL (limit check)
          Return FALSE

Result: Only one thread successfully increments.
        Coupon usage is protected!
```

---

## Memory Visibility Guarantee

`AtomicInteger` operations are **volatile**, ensuring:
1. **Thread-A's write** to usageCount is immediately visible to **Thread-B**
2. No stale cached values
3. Correct ordering of operations across threads

This is why AtomicInteger is essential—without it, threads could see outdated values.
