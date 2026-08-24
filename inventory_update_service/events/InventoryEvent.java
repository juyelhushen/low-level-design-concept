package inventory_update_service.events;

import java.time.LocalDateTime;

// Sealed: only the four types below can implement this.
// Every switch on InventoryEvent is exhaustive — no default needed,
// and the compiler catches any unhandled case immediately.

public sealed interface InventoryEvent
        permits BackInStockEvent, OutOfStockEvent, PriceDropEvent, LowStockEvent {

    String productId();
    String productName();
    LocalDateTime occurredAt();

}
