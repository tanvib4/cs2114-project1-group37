import java.util.*;

/**
 * Stores the history of {@link Transaction} objects and provides
 * calculations derived from that history, such as the current balance
 * and total amount spent.
 *
 * BudgetTracker owns transaction storage and arithmetic only. It has no
 * knowledge of budget goals (see {@code BudgetGoal}) and does not perform
 * any user interaction or input validation (see {@code BudgetApp}).
 *
 * The balance and total spent are computed from {@code history} on each
 * call rather than cached, so they can never drift out of sync with the
 * recorded transactions.
 * @author Ehren Casto
 * @version Sep 23, 2026
 */
public class BudgetTracker {
    /**
     * All recorded transactions, in the order they were added. An ArrayList
     * is used because order matters: the history must be readable
     * chronologically. Never null; starts empty.
     */    
    private ArrayList<Transaction> history;

    /**
     * Creates a BudgetTracker with an empty transaction history.
     */
    public BudgetTracker() {
        this.history = new ArrayList<>();
    }

    /**
     * Returns the current balance: total income minus total expenses,
     * computed by summing the full transaction history.
     *
     * @return the balance. May be negative if expenses exceed income —
     *         this is a valid result, not an error condition.
     */
    public double getTotal(){
        double total = 0.0;
        for (Transaction t : this.history) {
            if (t.isExpense()){
                total -= t.getAmount();
            } 
            else {
                total += t.getAmount();
            }
        }
        return total;
    }

    /**
     * Returns the sum of all expense transaction amounts in the history.
     * Income transactions are ignored.
     *
     * @return total amount spent; 0 if there are no expense transactions
     */
    public double getTotalSpent(){
        double totalSpent = 0.0;
        for (Transaction t : this.history) {
            if (t.isExpense()){
                totalSpent += t.getAmount();
            }
        }
        return totalSpent;
    }

    /**
     * Returns the number of transactions currently recorded.
     *
     * @return the size of the transaction history
     */
    public int size(){
        return history.size();
    }

    /**
     * Returns all recorded transactions in the order they were added.
     *
     * @return the transaction history; never null, and an empty list
     *         (not null) if no transactions have been recorded. This
     *         returns the live list, not a copy, so modifying it also
     *         modifies this tracker's history.
     */
    public ArrayList<Transaction> getHistory(){
        return this.history;
    }
    
    /**
     * Records a new transaction in the history.
     *
     * @param transaction the transaction to add; must not be null
     * @throws NullPointerException if transaction is null. This is treated
     *         as a programming error, not user input, so it is intentionally
     *         not caught here — it should surface during testing rather
     *         than fail silently.
     */
    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new NullPointerException("Transaction cannot be null");
        }
        this.history.add(transaction);
    }
}
