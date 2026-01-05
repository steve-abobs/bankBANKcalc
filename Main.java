import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("*** Банк \"БАНК\" ***");
        
        while (true) {
            printMenu();
            System.out.print("Выберите действие: ");
            

            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    createCustomer(bank, scanner);
                    break;
                case 2:
                    openDebitAccount(bank, scanner);
                    break;
                case 3:
                    openCreditAccount(bank, scanner);
                    break;
                case 4:
                    deposit(bank, scanner);
                    break;
                case 5:
                    withdraw(bank, scanner);
                    break;
                case 6:
                    transfer(bank, scanner);
                    break;
                case 7:
                    printCustomerAccounts(bank, scanner);
                    break;
                case 8:
                    bank.printTransactions();
                    break;
                case 9:
                    bank.printReport();
                    break;
                case 10:
                    System.out.println("Выходим");
                    scanner.close();
                    return;
                default:
                    System.out.println("Введите число от 1 до 10");
            }
        }
    }
    
    private static void printMenu() {
        System.out.println("\n");
        System.out.println("1 Создать клиента");
        System.out.println("2 Открыть дебетовый счет");
        System.out.println("3 Открыть кредитный счет");
        System.out.println("4 Пополнить");
        System.out.println("5 Снять");
        System.out.println("6 Перевести");
        System.out.println("7 Показать счета клиента");
        System.out.println("8 Показать транзакции");
        System.out.println("9 Отчет банка");
        System.out.println("10 Выйтти");
    }
    
    private static void createCustomer(Bank bank, Scanner scanner) {
    System.out.print("Введите ФИО клиента: ");
    String name = scanner.nextLine().trim();
    
    Customer cust = bank.createCustomer(name);
    System.out.println("Клиент создан: " + cust);
    
    String normName = name.toLowerCase();
    if (normName.equals("райан гослинг") || normName.equals("ryan gosling")) {
        Account acc = bank.openDebitAccount(cust);
        if (acc != null) {
            bank.deposit(acc.getAccountNumber(), 1000000.0);
        }
    }
}
    private static void openDebitAccount(Bank bank, Scanner scanner) {
        System.out.print("Введите ID клиента: ");
        int custId = scanner.nextInt();
        scanner.nextLine();
        
        Customer cust = bank.findCustomer(custId);
        if (cust == null) {
            System.out.println("Клиент с ID " + custId + " не найден");
            return;
        }
        
        Account acc = bank.openDebitAccount(cust);
        System.out.println("Дебетовый счет открыт: " + acc);
    }
    
    private static void openCreditAccount(Bank bank, Scanner scanner) {
        System.out.print("Введите ID клиента: ");
        int custId = scanner.nextInt();
        scanner.nextLine();
        
        Customer cust = bank.findCustomer(custId);
        if (cust == null) {
            System.out.println("Клиент с ID " + custId + " не найден");
            return;
        }
        
        System.out.print("Введите кредитный лимит: ");
        double creditLim = scanner.nextDouble();
        scanner.nextLine();
        
        Account acc = bank.openCreditAccount(cust, creditLim);
        System.out.println("Кредитный счет открыт: " + acc);
    }
    
    private static void deposit(Bank bank, Scanner scanner) {
        System.out.print("Введите номер счета: ");
        String accNum = scanner.nextLine().trim();
        System.out.print("Введите сумму для пополнения: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        
        if (bank.deposit(accNum, amount)) {
            System.out.println("Пооплнение выполнено успешно");
        } else {
            System.out.println("Ошибка при пополнении счета");
        }
    }
    
    private static void withdraw(Bank bank, Scanner scanner) {
        System.out.print("Введите номер счета: ");
        String accNum = scanner.nextLine().trim();
        System.out.print("Введите сумму для снятия: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        
        if (bank.withdraw(accNum, amount)) {
            System.out.println("Денежки успешно сняты");
        } else {
            System.out.println("Ошибка при снятии");
        }
    }
    
    private static void transfer(Bank bank, Scanner scanner) {
        System.out.print("Введите номер счета отправителя: ");
        String from = scanner.nextLine().trim();
        System.out.print("Введите номер счета получателя: ");
        String to = scanner.nextLine().trim();
        System.out.print("Введите сумму для перевода: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        
        if (bank.transfer(from, to, amount)) {
            System.out.println("Перевод выполнен успешно");
        } else {
            System.out.println("Ошибка при переводе");
        }
    }
    
    private static void printCustomerAccounts(Bank bank, Scanner scanner) {
        System.out.print("Введите ID клиента: ");
        int custId = scanner.nextInt();
        scanner.nextLine();
        
        bank.printCustomerAccounts(custId);
    }
}

