import java.time.LocalDateTime;

public class Transaction {
    public enum TransactionType {
        DEPOSIT,
        WITHDRAW,
        TRANSFER
    }
    
    private TransactionType type;
    private double amount;
    private String fromAccNum;
    private String toAccNum;
    private LocalDateTime timestamp;
    private boolean success;
    private String message;
    
    public Transaction(TransactionType type, double amount, 
                      String fromAccNum, String toAccNum, 
                      boolean success, String message) {
        this.type = type;
        this.amount = amount;
        this.fromAccNum = fromAccNum;
        this.toAccNum = toAccNum;
        this.timestamp = LocalDateTime.now();
        this.success = success;
        this.message = message;
    }
    
    public TransactionType getType() {
        return type;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getFromAccountNumber() {
        return fromAccNum;
    }
    
    public String getToAccountNumber() {
        return toAccNum;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public String getMessage() {
        return message;
    }
    
    @Override
    public String toString() {
        return String.format("Transaction{type=%s, amount=%.2f, from=%s, to=%s, " +
                           "timestamp=%s, success=%s, message='%s'}", 
                           type, amount, fromAccNum, toAccNum, 
                           timestamp, success, message);
    }
}

