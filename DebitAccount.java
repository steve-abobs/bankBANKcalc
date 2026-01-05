public class DebitAccount extends Account {
    
    public DebitAccount(Customer owner) {
        super(owner);
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            return false;
        }
        if (getBalance() >= amount) {
            setBalance(getBalance() - amount);
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "DebitAccount{accountNumber='" + getAccountNumber() + "', balance=" + 
               getBalance() + ", owner=" + getOwner().getFullName() + "}";
    }
}

