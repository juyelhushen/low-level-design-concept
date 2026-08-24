package online_shopping_cart.entity;

import online_shopping_cart.enums.CouponType;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Coupon {
    private final String code;
    private final CouponType type;
    private final double value;
    private final LocalDate validUntil;
    private final double minCartValue;
    private final int maxUsageCount;
    private final AtomicInteger usageCount = new AtomicInteger(0);

    public Coupon(String code, CouponType type, double value, LocalDate validUntil, double minCartValue, int maxUsageCount) {
        this.code = code;
        this.type = type;
        this.value = value;
        this.validUntil = validUntil;
        this.minCartValue = minCartValue;
        this.maxUsageCount = maxUsageCount;
    }


    public boolean isValid(double cartValue) {
        return LocalDate.now().isBefore(validUntil)
                && cartValue >= minCartValue
                && usageCount.get() < maxUsageCount;
    }

    // CAS-based: prevents the same coupon being used twice concurrently
    // Uses Compare-And-Swap (CAS) operations for lock-free concurrency control
    public boolean tryUse() {
        int current;
        do {
            // Fetch the current usage count atomically
            current = usageCount.get();
            
            // Check if coupon has already been used the maximum allowed times
            if (current >= maxUsageCount) return false;
            
            // Attempt atomic compare-and-set: if usageCount still equals 'current',
            // increment it to 'current + 1'. If another thread modified it, this fails
            // and the loop retries. Returns true on success, false if CAS failed.
        } while (!usageCount.compareAndSet(current, current + 1));
        
        // Coupon usage count incremented successfully; usage is permitted
        return true;
    }

    public CouponType getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public double getValue() {
        return value;
    }
}
