package bank;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BankAccount {
    private final String name;          // account holder's name
    private final String accountNo;     // randomly generated acct no

    // DateTime ref: https://www.w3schools.com/java/java_date.asp
    private final LocalDateTime creationDate;   // account creation date 
    private LocalDateTime closingDate;          // account closing date 
    
    private float balance;              // account balance
    private boolean isClosed;           // indicates if this account has been closed

    private List<String> transactionsList = new ArrayList<>(); 
    
    public BankAccount(String name) {
        this(name, 0);
    }
    public BankAccount(String name, float balance) {
        this.name = name;

        // Get randomised acct no. (abs used here as randomUUID() provides -ve values too)
        long uniqueLong = Math.abs(UUID.randomUUID().getMostSignificantBits());
        this.accountNo = "" + uniqueLong;   

        this.creationDate = LocalDateTime.now();
        
        this.balance = balance;
    }

    public void deposit(float amount) {
        if(amount <= 0 || isClosed)
            throw new IllegalArgumentException();

        balance += amount;

        LocalDateTime dateTimeNow = LocalDateTime.now();
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String transaction = "deposit $%.2f at %s".formatted(amount, dateTimeNow.format(dtFormatter));
        
        transactionsList.add(transaction);
    }
    
    public void withdraw(float amount) {
        if(amount <= 0 || amount > balance || isClosed)
            throw new IllegalArgumentException();

        balance -= amount;

        LocalDateTime dateTimeNow = LocalDateTime.now();
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String transaction = "withdraw $%.2f at %s".formatted(amount, dateTimeNow.format(dtFormatter));
        
        transactionsList.add(transaction);
    }

    public String getName() {
        return name;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getClosingDate() {
        return closingDate;
    }
    public void setClosingDate() {
        closingDate = LocalDateTime.now();;
    }

    public float getBalance() {
        return balance;
    }
    public void setBalance(float balance) {
        this.balance = balance;
    }

    public boolean isClosed() {
        return isClosed;
    }
    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public List<String> getTransactionsList() {
        return transactionsList;
    }
    public void setTransactionsList(List<String> transactionsList) {
        this.transactionsList = transactionsList;
    }
}
