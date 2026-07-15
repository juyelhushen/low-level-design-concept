package LibraryManagementSystem.entity;

import LibraryManagementSystem.enums.FineStatus;

public class Fine {

    private final String fineId;
    private final String lendingId;
    private final String memberId;
    private final double amount;
    private FineStatus status;

    public Fine(String fineId,
                String lendingId,
                String memberId,
                double amount) {

        this.fineId = fineId;
        this.lendingId = lendingId;
        this.memberId = memberId;
        this.amount = amount;
        this.status = FineStatus.PENDING;

    }

    public String getFineId()    { return fineId; }
    public String getLendingId() { return lendingId; }
    public String getMemberId()  { return memberId; }
    public double getAmount()    { return amount; }
    public FineStatus getStatus(){ return status; }

    public void markPaid() { this.status = FineStatus.PAID; }
}
