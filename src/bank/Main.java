package bank;

public class Main {
    public static void main(String[] args) {
        BankAccount ba = new BankAccount("test");
        BankAccount ba2 = new BankAccount("test2");
        BankAccount ba3 = new BankAccount("test3");
        
        
        try {
            ba.deposit(100);
            
            ba.withdraw(50);
            ba.withdraw(40);
        } catch (IllegalArgumentException ex) {
            System.out.println("ERROR: INVALID AMOUNT ENTERED OR BANK ACCOUNT IS ALREADY CLOSED!");
            ex.printStackTrace();
        }

        for (String transaction : ba.getTransactionsList()) {
            System.out.println(transaction);
        }
    }
}   