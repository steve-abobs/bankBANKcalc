import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;
    private List<Account> accounts;
    private List<Transaction> transactions;
    
    public Bank() {
        this.customers = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }
    
    public Customer createCustomer(String fullName) {
        Customer cust = new Customer(fullName);
        customers.add(cust);
        return cust;
    }
    
    public Account openDebitAccount(Customer owner) {
        Account acc = new DebitAccount(owner);
        accounts.add(acc);
        return acc;
    }
    
    public Account openCreditAccount(Customer owner, double creditLimit) {
        Account acc = new CreditAccount(owner, creditLimit);
        accounts.add(acc);
        return acc;
    }
    
    public Account findAccount(String accNum) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accNum)) {
                return acc;
            }
        }
        return null;
    }
    
    public Customer findCustomer(int custId) {
        for (Customer cust : customers) {
            if (cust.getId() == custId) {
                return cust;
            }
        }
        return null;
    }
    
    public boolean deposit(String accNum, double amount) {
        Account acc = findAccount(accNum);
        if (acc == null) {
            transactions.add(new Transaction(Transaction.TransactionType.DEPOSIT, amount, 
                                           null, accNum, false, 
                                           "Счет не найден"));
            return false;
        }
        
        boolean succ = acc.deposit(amount);
        String msg = succ ? "OK" : "Неверная сумма для пополнения";
        transactions.add(new Transaction(Transaction.TransactionType.DEPOSIT, amount, 
                                       null, accNum, succ, msg));
        return succ;
    }
    
    public boolean withdraw(String accNum, double amount) {
        Account acc = findAccount(accNum);
        if (acc == null) {
            transactions.add(new Transaction(Transaction.TransactionType.WITHDRAW, amount, 
                                           accNum, null, false, 
                                           "Счет не найден"));
            return false;
        }
        
        boolean succ = acc.withdraw(amount);
        String msg = succ ? "OK" : "Недостаточно средств или неверная сумма";
        transactions.add(new Transaction(Transaction.TransactionType.WITHDRAW, amount, 
                                       accNum, null, succ, msg));
        return succ;
    }
    
    public boolean transfer(String from, String to, double amount) {
        Account fromAcc = findAccount(from);
        Account toAcc = findAccount(to);
        
        if (fromAcc == null) {
            transactions.add(new Transaction(Transaction.TransactionType.TRANSFER, amount, 
                                           from, to, false, "Счет отправителя не найден"));
            return false;
        }
        
        if (toAcc == null) {
            transactions.add(new Transaction(Transaction.TransactionType.TRANSFER, amount, 
                                           from, to, false, "Счет получателя не найден"));
            return false;
        }
        
        if (from.equals(to)) {
            transactions.add(new Transaction(Transaction.TransactionType.TRANSFER, amount, 
                                           from, to, false, "Нельзя перевести на тот же счет"));
            return false;
        }
        
        boolean succ = fromAcc.transfer(toAcc, amount);
        String msg = succ ? "OK" : "Недостаточно средств или неверная сумма";
        transactions.add(new Transaction(Transaction.TransactionType.TRANSFER, amount, 
                                       from, to, succ, msg));
        return succ;
    }
    
    public void printCustomerAccounts(int custId) {
        Customer cust = findCustomer(custId);
        if (cust == null) {
            System.out.println("Клиент с ID " + custId + " не найдене");
            return;
        }
        
        System.out.println("\nСчета клиента: " + cust.getFullName() + " (ID: " + custId + ")");
        boolean found = false;
        for (Account acc : accounts) {
            if (acc.getOwner().getId() == custId) {
                System.out.println(acc);
                found = true;
            }
        }
        if (!found) {
            System.out.println("У клиента нет счетов");
        }
        System.out.println();
    }
    
    public void printTransactions() {
        System.out.println("\nИстория");
        if (transactions.isEmpty()) {
            System.out.println("Транзакций пока нет");
        } else {
            for (Transaction trans : transactions) {
                System.out.println(trans);
            }
        }
        System.out.println();
    }
    
    public void printReport() {
        System.out.println("\nОтчет банка");
        
        int debitCount = 0;
        int creditCount = 0;
        double debitTotal = 0.0;
        double creditTotal = 0.0;
        int succTrans = 0;
        int failTrans = 0;
        
        for (Account acc : accounts) {
            if (acc instanceof DebitAccount) {
                debitCount++;
                debitTotal += acc.getBalance();
            } else if (acc instanceof CreditAccount) {
                creditCount++;
                creditTotal += acc.getBalance();
            }
        }
        
        for (Transaction trans : transactions) {
            if (trans.isSuccess()) {
                succTrans++;
            } else {
                failTrans++;
            }
        }
        
        System.out.println("Количество дебетовых счетов: " + debitCount);
        System.out.println("Суммарный баланс дебетовых счетов: " + String.format("%.2f", debitTotal));
        System.out.println("Количество кредитных счетов: " + creditCount);
        System.out.println("Суммарный баланс кредитных счетов: " + String.format("%.2f", creditTotal));
        System.out.println("Успешных операций: " + succTrans);
        System.out.println("Неуспешных операций: " + failTrans);
        System.out.println();
    }
    
    public List<Customer> getCustomers() {
        return customers;
    }
}

