/**
 * Stores the user's budget goal and performs calculations using the total
 * spending supplied by BudgetTracker. BudgetGoal does not store transactions or
 * current spending.
 *
 * @author Liangru Ji
 * @version 2026.9.23
 */
public class BudgetGoal
{
    // ~ Fields
    // .................................................................

    private double goal;

    // ~ Constructors ..........................................................
    
    // -------------------------------------------------------------------------
    /**
     * Creates a new BudgetGoal.
     *
     * @param goal
     *            the user's spending goal
     * @throws IllegalArgumentException
     *             if the goal is not positive
     */
    public BudgetGoal(double goal)
    {
        if (goal <= 0)
        {
            throw new IllegalArgumentException("Budget goal must be positive");
        }

        this.goal = goal;
    }

    // ~ Public Methods ........................................................

    // -------------------------------------------------------------------------
    /**
     * Returns the current budget goal.
     *
     * @return the budget goal
     */
    public double getGoal()
    {
        return this.goal;
    }

    // -------------------------------------------------------------------------
    /**
     * Changes the budget goal.
     *
     * @param goal
     *            the new spending goal
     */
    public void setGoal(double goal)
    {
        if (goal <= 0)
        {
            throw new IllegalArgumentException("Budget goal must be positive");
        }

        this.goal = goal;
    }

    // -------------------------------------------------------------------------
    /**
     * Determines whether total spending is greater than the goal. Spending
     * exactly equal to the goal is not over budget.
     *
     * @param totalSpent
     *            the total amount spent
     * @return true if total spending is greater than the goal
     */
    public boolean isOverBudget(double totalSpent)
    {
        return totalSpent > this.goal;
    }

    // -------------------------------------------------------------------------
    /**
     * Calculates how much money remains in the budget. A negative result means
     * the user is over budget.
     *
     * @param totalSpent
     *            the total amount spent
     * @return the goal minus total spending
     */
    public double amountRemaining(double totalSpent)
    {
        return this.goal - totalSpent;
    }

    // -------------------------------------------------------------------------
    /**
     * Calculates the percentage of the budget that has been used. The result
     * can be greater than 100 if the user is over budget.
     *
     * @param totalSpent
     *            the total amount spent
     * @return the percentage of the goal used
     */
    public double percentUsed(double totalSpent)
    {
        return totalSpent / this.goal * 100.0;
    }
}
