package inventory_update_service.events;

import java.time.LocalDateTime;

public record LowStockEvent(
        String productId,
        String productName,
        int remainingStock,            // how many are left (below threshold)
        LocalDateTime occurredAt
) implements InventoryEvent {
}
