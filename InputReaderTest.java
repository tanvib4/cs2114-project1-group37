import student.TestCase;

/**
 * Tests for InputReader. Keyboard input is simulated with setSystemIn, so
 * each test checks both a valid entry and a rejected one. The reader is
 * created after setSystemIn because its Scanner reads System.in in the
 * constructor.
 *
 * @author Golden You
 * @version Sep 23, 2026
 */
public class InputReaderTest extends TestCase {

    /**
     * A valid amount is returned; text, negatives, and zero are rejected
     * first.
     */
    public void testReadPositiveDouble() {
        setSystemIn("47\n");
        assertEquals(47.0, new InputReader().readPositiveDouble("A: "), 0.001);

        setSystemIn("apple\n-5\n0\n12.50\n");
        InputReader reader = new InputReader();

        assertEquals(12.50, reader.readPositiveDouble("A: "), 0.001);
        
    }


    /**
     * Both types are returned in lowercase whatever the case typed, and an
     * unrecognized type is rejected first.
     */
    public void testReadType() {
        setSystemIn("INCOME\n");
        assertEquals("income", new InputReader().readType("T: "));

        setSystemIn("spending\nexpense\n");
        assertEquals("expense", new InputReader().readType("T: "));
    }


    /**
     * Text is returned trimmed, and blank or spaces-only input is rejected
     * first.
     */
    public void testReadNonEmptyString() {
        setSystemIn("  groceries  \n");
        assertEquals("groceries", new InputReader().readNonEmptyString("D: "));

        setSystemIn("\n   \ngas\n");
        InputReader reader = new InputReader();

        assertEquals("gas", reader.readNonEmptyString("D: "));
        
    }


    /**
     * Choices inside the range are returned, including both endpoints, and
     * out-of-range numbers and text are rejected first.
     */
    public void testReadMenuChoice() {
        setSystemIn("1\n");
        assertEquals(1, new InputReader().readMenuChoice(1, 5));

        setSystemIn("5\n");
        assertEquals(5, new InputReader().readMenuChoice(1, 5));

        setSystemIn("9\nabc\n4\n");
        InputReader reader = new InputReader();

        assertEquals(4, reader.readMenuChoice(1, 5));
        reader.close();
    }
}