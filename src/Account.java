import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account implements AccountService {

    private ArrayList<Transaction> transactions = new ArrayList<>();
    private int balance = 0;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }


    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        balance += amount;
        this.transactions.add(new Transaction(LocalDate.now(),amount,balance));
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Amount must be less than balance");
        }
        balance -= amount;
        this.transactions.add(new Transaction(LocalDate.now(),-amount,balance));
    }

    // Vous avez demandé de fournir les dates dans les tests sans modifier les fonctions de l'interface AccountService, c'est pourquoi j'ai pensé à ajouter ces méthodes spécifiquement pour les tests
    public void deposit(int amount, LocalDate date) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be greater than 0");
        balance += amount;
        transactions.add(new Transaction(date, amount, balance));
    }

    public void withdraw(int amount, LocalDate date) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be greater than 0");
        if (amount > balance) throw new IllegalArgumentException("Insufficient balance");
        balance -= amount;
        transactions.add(new Transaction(date, -amount, balance));
    }


    @Override
    public void printStatement() {
        System.out.println("Date        ||    Amount        ||       Balance");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        List<Transaction> reverseTransactions = new ArrayList<>(transactions);
        Collections.reverse(reverseTransactions);

        for (Transaction t : reverseTransactions) {
            String formattedDate = t.getDate().format(formatter);
            System.out.println(formattedDate + "   ||    " + t.getAmount() + "        ||       " + t.getBalance());
        }
    }

}