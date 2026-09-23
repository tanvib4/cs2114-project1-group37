import student.TestCase;
import java.time.LocalDate;

/**
 * Tests for the Transaction class.
 *
 * @author bhatr
 * @version Sep 17, 2026
 */
public class TransactionTest extends TestCase {

    private Transaction expense;
    private Transaction income;
    private LocalDate today;

    /**
     * Sets up a fresh expense and income transaction before each test.
     */
    public void setUp() {
        today = LocalDate.of(2026, 9, 17);
        expense = new Transaction(25.50, "expense", "groceries", today);
        income = new Transaction(100.00, "income", "paycheck", today);
    }

    /**
     * Tests that the getters return what was passed to the constructor.
     */
    public void testGetters() {
        assertEquals(25.50, expense.getAmount(), 0.001);
        assertEquals("expense", expense.getType());
        assertEquals("groceries", expense.getDescription());
        assertEquals(today, expense.getDate());
    }

    /**
     * Tests that isExpense reports true for an expense and false for income.
     */
    public void testIsExpense() {
        assertTrue(expense.isExpense());
        assertFalse(income.isExpense());
    }

    /**
     * Tests that a negative amount is rejected.
     */
    public void testNegativeAmountRejected() {
        Exception thrown = null;
        try {
            new Transaction(-5, "expense", "bad", today);
        }
        catch (IllegalArgumentException e) {
            thrown = e;
        }
        assertNotNull(thrown);
    }

    /**
     * Tests that a zero amount is rejected.
     */
    public void testZeroAmountRejected() {
        Exception thrown = null;
        try {
            new Transaction(0, "income", "bad", today);
        }
        catch (IllegalArgumentException e) {
            thrown = e;
        }
        assertNotNull(thrown);
    }

    /**
     * Tests that a type other than income or expense is rejected.
     */
    public void testBadTypeRejected() {
        Exception thrown = null;
        try {
            new Transaction(10, "banana", "bad", today);
        }
        catch (IllegalArgumentException e) {
            thrown = e;
        }
        assertNotNull(thrown);
    }

    /**
     * Tests that toString includes the amount, type, and description.
     */
    public void testToString() {
        String result = expense.toString();
        assertTrue(result.contains("expense"));
        assertTrue(result.contains("groceries"));
        assertTrue(result.contains("25.5"));
    }
}