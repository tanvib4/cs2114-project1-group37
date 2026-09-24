import student.TestCase;
import java.time.LocalDate;
/**
 * Test class for BudgetTracker.
 *
 * @author Ehren Casto
 * @version Sep 23, 2026
 */
public class BudgetTrackerTest extends student.TestCase {
    private BudgetTracker tracker;

    /**
     * Set up for all test methods. Runs before every test.
     */
    public void setUp() {
        tracker = new BudgetTracker();
    }


    /**
     * Test method for addTransaction. A valid transaction should be added,
     * and a null transaction should throw instead of being added.
     */
    public void testAddTransaction() {
        tracker.addTransaction(new Transaction(25.50, "expense", "groceries", LocalDate.now()));
        assertEquals(tracker.size(), 1);

        try {
            tracker.addTransaction(null);
            fail("Expected NullPointerException for null transaction");
        }
        catch (NullPointerException e) {
            // expected
        }
        assertEquals(tracker.size(), 1);
    }


    /**
     * Test method for getHistory. Should return transactions in the order
     * added, and an empty list (not null) when no transactions exist.
     */
    public void testGetHistory() {
        assertTrue(tracker.getHistory().isEmpty());

        Transaction first = new Transaction(100, "income", "paycheck", LocalDate.now());
        Transaction second = new Transaction(30, "expense", "groceries", LocalDate.now());
        tracker.addTransaction(first);
        tracker.addTransaction(second);

        assertEquals(tracker.getHistory().get(0), first);
        assertEquals(tracker.getHistory().get(1), second);
    }


    /**
     * Test method for getTotal. Should be income minus expenses, and 0
     * for an empty tracker.
     */
    public void testGetTotal() {
        assertEquals(tracker.getTotal(), 0.0, 0.001);

        tracker.addTransaction(new Transaction(100, "income", "paycheck", LocalDate.now()));
        tracker.addTransaction(new Transaction(30, "expense", "groceries", LocalDate.now()));

        assertEquals(tracker.getTotal(), 70.0, 0.001);
    }


    /**
     * Test method for getTotalSpent. Should sum only expense transactions,
     * and be 0 when there are none.
     */
    public void testGetTotalSpent() {
        assertEquals(tracker.getTotalSpent(), 0.0, 0.001);

        tracker.addTransaction(new Transaction(100, "income", "paycheck", LocalDate.now()));
        assertEquals(tracker.getTotalSpent(), 0.0, 0.001);

        tracker.addTransaction(new Transaction(30, "expense", "groceries", LocalDate.now()));
        tracker.addTransaction(new Transaction(20, "expense", "gas", LocalDate.now()));
        assertEquals(tracker.getTotalSpent(), 50.0, 0.001);
    }


    /**
     * Test method for size.
     */
    public void testSize() {
        assertEquals(tracker.size(), 0);

        tracker.addTransaction(new Transaction(100, "income", "paycheck", LocalDate.now()));
        tracker.addTransaction(new Transaction(30, "expense", "groceries", LocalDate.now()));

        assertEquals(tracker.size(), 2);
    }
}