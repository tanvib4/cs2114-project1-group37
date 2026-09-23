import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 * 
 * @author Liangru Ji
 * @version 2026.9.23
 */
public class BudgetGoalTest
    extends TestCase
{
    private BudgetGoal budgetGoal;

    // -------------------------------------------------------------------------
    /**
     * Creates a new BudgetGoal before every test.
     */
    public void setUp()
    {
        budgetGoal = new BudgetGoal(500.0);
    }


    // -------------------------------------------------------------------------
    /**
     * Tests the constructor and getGoal method.
     */
    public void testConstructorAndGetGoal()
    {
        assertEquals(500.0, budgetGoal.getGoal(), 0.001);
    }


    // -------------------------------------------------------------------------
    /**
     * Tests that the constructor rejects zero and negative goals.
     */
    public void testInvalidConstructor()
    {
        try
        {
            new BudgetGoal(0);
            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException exception)
        {
            assertNotNull(exception);
        }

        try
        {
            new BudgetGoal(-100);
            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException exception)
        {
            assertNotNull(exception);
        }
    }


    // -------------------------------------------------------------------------
    /**
     * Tests changing the budget goal.
     */
    public void testSetGoal()
    {
        budgetGoal.setGoal(600.0);

        assertEquals(600.0, budgetGoal.getGoal(), 0.001);
    }


    // -------------------------------------------------------------------------
    /**
     * Tests that an invalid new goal is rejected and the old goal remains.
     */
    public void testInvalidSetGoal()
    {
        try
        {
            budgetGoal.setGoal(-100.0);
            fail("Expected an IllegalArgumentException");
        }
        catch (IllegalArgumentException exception)
        {
            assertNotNull(exception);
        }

        assertEquals(500.0, budgetGoal.getGoal(), 0.001);
    }


    // -------------------------------------------------------------------------
    /**
     * Tests whether spending is over budget.
     */
    public void testIsOverBudget()
    {
        assertFalse(budgetGoal.isOverBudget(400.0));
        assertFalse(budgetGoal.isOverBudget(500.0));
        assertTrue(budgetGoal.isOverBudget(520.0));
    }


    // -------------------------------------------------------------------------
    /**
     * Tests the amount remaining in the budget.
     */
    public void testAmountRemaining()
    {
        assertEquals(200.0, budgetGoal.amountRemaining(300.0), 0.001);

        assertEquals(0.0, budgetGoal.amountRemaining(500.0), 0.001);

        assertEquals(-200.0, budgetGoal.amountRemaining(700.0), 0.001);
    }


    // -------------------------------------------------------------------------
    /**
     * Tests the percentage of the budget used.
     */
    public void testPercentUsed()
    {
        assertEquals(50.0, budgetGoal.percentUsed(250.0), 0.001);

        assertEquals(100.0, budgetGoal.percentUsed(500.0), 0.001);

        assertEquals(120.0, budgetGoal.percentUsed(600.0), 0.001);
    }
}
