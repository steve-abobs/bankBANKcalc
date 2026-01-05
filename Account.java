public class Account {
    private static int nextAccNum = 1000;
    
    private String accNum;
    private double balance;
    private Customer owner;
    
    public Account(Customer owner) {
        this.accNum = String.valueOf(nextAccNum++);
        this.balance = 0.0;
        this.owner = owner;
    }
    
    public String getAccountNumber() {
        return accNum;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public Customer getOwner() {
        return owner;
    }
    
    protected void setBalance(double balance) {
        this.balance = balance;
    }
    
    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        balance += amount;
        return true;
    }
    
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }
        if (balance < amount) {
            return false;
        }
        balance -= amount;
        return true;
    }
    
    public boolean transfer(Account to, double amount) {
        if (amount <= 0) {
            return false;
        }
        if (this.withdraw(amount)) {
            if (to.deposit(amount)) {
                return true;
            } else {
                this.deposit(amount);
                return false;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Account{accountNumber='" + accNum + "', balance=" + balance + 
               ", owner=" + owner.getFullName() + "}";
    }
}

