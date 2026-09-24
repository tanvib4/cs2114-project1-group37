import java.time.LocalDate; //This helps java find where the LocalDate class is.  
/**
 * Represents a single financial entry: an amount of money that is either
 * income or an expense, with a description and the date it occurred.
 *
 * A Transaction validates its own data in the constructor, so an invalid
 * Transaction can never exist. It knows nothing about totals, goals, or
 * the user interface.
 *  @author Tanvi Bhat
 *  @version Sep 17, 2026
 */
public class Transaction {
    
    private double amount;
    private String type;
    private String description;
    private LocalDate date; 
        
    /**
     * Creates a transaction.
     *
     * @param amount      
     * the amount of money; must be greater than zero
     * @param type        
     * must be "income" or "expense"
     * @param description 
     * what the transaction was for
     * @param date        
     * the date it occurred
     * @throws IllegalArgumentException if the amount is not positive or the
     *         type is not one of the two accepted values
     */
    public Transaction(double amount, String type, String description, LocalDate date)
    {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount needs to be positive");
        }
        if (!"income".equals(type) && !"expense".equals(type)) {
            throw new IllegalArgumentException("Type should be either income or expense.");
            
        }
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.date = date;
        
    }
    
    /**
     * @return the amount of money which will always be positive.
     */
    public double getAmount() {
        return this.amount;
    }
    
    /**
     * @return either income or expense type.
     */
    public String getType() {
        return this.type;
    }
    
    /**
     * @return the description of the transaction.
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * @return the date the transaction occurred.
     */
    public LocalDate getDate() {
        return this.date;
    }
    
    /**
     * Reports whether this transaction is money going out. Used by
     * BudgetTracker to decide whether to add or subtract the amount.
     *
     * @return true if the type is "expense", false if it is "income"
     */
    public boolean isExpense() {
        return "expense".equals(this.type);
    }
    
    /**
     * @return a readable line that helps the user understand the description of the transaction.
     */
    public String toString() {
        return this.date + " " + this.type + " $" + String.format("%.2f", this.amount) 
        + " " + this.description;
    } 
}