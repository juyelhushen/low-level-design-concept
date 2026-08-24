package inventory_update_service.events;

import java.time.LocalDateTime;

public record BackInStockEvent(
        String productId,
        String productName,
        int availableQuantity,
        LocalDateTime occurredAt
) implements InventoryEvent {

}
