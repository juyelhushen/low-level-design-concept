package splitwise.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Settlement {
    private final String settlementId;
    private final String payerId;
    private final String receiverId;
    private final long amount;
    private final LocalDateTime settlementAt;

    public Settlement(String payerId, String receiverId,
                      long amount, LocalDateTime settlementAt) {
        this.settlementId = UUID.randomUUID().toString();
        this.payerId = payerId;
        this.receiverId = receiverId;
        this.amount = amount;
        this.settlementAt = settlementAt;
    }

    public String getSettlementId() {
        return settlementId;
    }

    public String getPayerId() {
        return payerId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public long getAmount() {
        return amount;
    }

    public LocalDateTime getSettlementAt() {
        return settlementAt;
    }
}
