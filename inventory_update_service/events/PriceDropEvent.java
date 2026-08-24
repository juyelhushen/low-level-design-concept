package inventory_update_service.events;

import java.time.LocalDateTime;

public record PriceDropEvent(
        String productId,
        String productName,
        double oldPrice,
        double newPrice,               // newPrice < oldPrice always — enforced by InventoryService
        LocalDateTime occurredAt
) implements InventoryEvent {

    public double dropPercent() {
        return ((oldPrice - newPrice) / oldPrice) * 100;
    }

}
