package splitwise.entity;


public class Split {

    private final String userId;
    private long amount;

    public Split(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}