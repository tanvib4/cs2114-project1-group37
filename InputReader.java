import java.util.Scanner;

/**
 * Reads values from the keyboard, re-prompting until the input is usable.
 * This is the only class that reads user input.
 *
 * @author Golden You
 * @version Sep 23, 2026
 */
public class InputReader
{
    private Scanner scanner;

    /**
     * Creates an InputReader that reads from standard input.
     */
    public InputReader()
    {
        scanner = new Scanner(System.in);
    }


    /**
     * Prompts for an amount, rejecting text, zero, and negatives.
     *
     * @param prompt the text shown before reading
     * @return a number greater than zero
     */
    public double readPositiveDouble(String prompt)
    {
        while (true)
        {
            System.out.print(prompt);

            try
            {
                double value = Double.parseDouble(scanner.nextLine());

                if (Double.isNaN(value) || Double.isInfinite(value))
                {
                    System.out.println("Please enter a valid number.");
                }
                else if (value <= 0)
                {
                    System.out.println("Amount must be greater than zero.");
                }
                else
                {
                    return value;
                }

            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }


    /**
     * Prompts for a transaction type. Capitalization is ignored, but the
     * value returned is always lowercase for Transaction to compare.
     *
     * @param prompt the text shown before reading
     * @return "income" or "expense"
     */
    public String readType(String prompt)
    {
        while (true)
        {
            System.out.print(prompt);

            String type = scanner.nextLine().trim();

            if (type.equalsIgnoreCase("income"))
            {
                return "income";
            }

            if (type.equalsIgnoreCase("expense"))
            {
                return "expense";
            }

            System.out.println("Please enter \"income\" or \"expense.\"");
        }
    }


    /**
     * Prompts for text, rejecting blank or spaces-only input.
     *
     * @param prompt the text shown before reading
     * @return the user's text, trimmed and never empty
     */
    public String readNonEmptyString(String prompt)
    {
        while (true)
        {
            System.out.print(prompt);

            String input = scanner.nextLine().trim();

            if (!input.isEmpty())
            {
                return input;
            }

            System.out.println("Input cannot be blank.");
        }
    }


    /**
     * Prompts for a menu choice, rejecting text and out-of-range numbers.
     *
     * @param min the lowest acceptable choice
     * @param max the highest acceptable choice
     * @return a number between min and max, inclusive
     */
    public int readMenuChoice(int min, int max)
    {
        while (true)
        {
            System.out.print("Enter your choice: ");

            try
            {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice >= min && choice <= max)
                {
                    return choice;
                }

                System.out.println(
                    "Please enter a number from " + min + " to " + max + ".");

            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid choice. Please enter a number.");
            }
        }
    }


    /**
     * Closes the Scanner. Call once, at exit.
     */
    public void close()
    {
        scanner.close();
    }
}