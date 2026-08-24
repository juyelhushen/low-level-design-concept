package splitwise.entity;

import java.math.BigDecimal;

public record Balance(
        String debtorId,
        String creditorId,
        long amount
) {


}
