package bank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FixedDepositAccount {
    private final String name;          // account holder's name
    private final String accountNo;     // randomly generated acct no

    // DateTime ref: https://www.w3schools.com/java/java_date.asp
    private final LocalDateTime creationDate;   // account creation date 
    private LocalDateTime closingDate;          // account closing date 
    
    private float balance; 
    private float interest;
    private int duration;

    private boolean isClosed;           // indicates if this account has been closed
    private boolean hasChangedInterest;
    private boolean hasChangedDuration;

    private List<String> transactionsList = new ArrayList<>(); 
    
    public FixedDepositAccount(String name) {
        this(name, Constants.DEFAULT_BALANCE, Constants.DEFAULT_INTEREST, Constants.DEFAULT_DURATION);
    }
    public FixedDepositAccount(String name, float balance) {
        this(name, balance, Constants.DEFAULT_INTEREST, Constants.DEFAULT_DURATION);
    }
    public FixedDepositAccount(String name, float balance, float interest) {
        this(name, balance, interest, Constants.DEFAULT_DURATION);
    }
    public FixedDepositAccount(String name, float balance, float interest, int duration) {
        this.name = name;

        // Get randomised acct no. (abs used here as randomUUID() provides -ve values too)
        long uniqueLong = Math.abs(UUID.randomUUID().getMostSignificantBits());
        this.accountNo = "" + uniqueLong;   

        this.creationDate = LocalDateTime.now();
        
        this.balance = balance;
        this.interest = interest;
        this.duration = duration;

        this.isClosed = false;
        this.hasChangedInterest = false;
        this.hasChangedDuration = false;
    }

    public void deposit(float amount) {
        if(amount <= 0 || isClosed)
            throw new IllegalArgumentException();

        //balance += amount;

        LocalDateTime dateTimeNow = LocalDateTime.now();
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String transaction = "deposit $%.2f at %s".formatted(amount, dateTimeNow.format(dtFormatter));
        
        transactionsList.add(transaction);
    }
    
    public void withdraw(float amount) {
        if(amount <= 0 || amount > balance || isClosed)
            throw new IllegalArgumentException();

        //balance -= amount;

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
        return balance + interest;
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

    public float getInterest() {
        return interest;
    }
    public void setInterest(float interest) {
        // Allow only one-time change
        if(hasChangedInterest)
            throw new IllegalArgumentException();
        
        this.interest = interest;
        hasChangedInterest = true;
    }
    
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        // Allow only one-time change
        if(hasChangedDuration)
            throw new IllegalArgumentException();

        this.duration = duration;
        hasChangedDuration = true;
    }
}
