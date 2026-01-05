public class CreditAccount extends Account {
    private double creditLim;
    
    public CreditAccount(Customer owner, double creditLim) {
        super(owner);
        this.creditLim = creditLim;
    }
    
    public double getCreditLimit() {
        return creditLim;
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }
        double newBal = getBalance() - amount;
        if (newBal >= -creditLim) {
            setBalance(newBal);
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "CreditAccount{accountNumber='" + getAccountNumber() + "', balance=" + 
               getBalance() + ", creditLimit=" + creditLim + 
               ", owner=" + getOwner().getFullName() + "}";
    }
}

