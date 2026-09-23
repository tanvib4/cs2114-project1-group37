import java.time.LocalDate;

/**
 * Runs the Budgetly program. Displays the menu, responds to the user's
 * choices, and prints results. All keyboard reading is delegated to
 * InputReader and all arithmetic to BudgetTracker and BudgetGoal; this
 * class performs no calculations of its own.
 *
 * @author Golden You
 * @version Sep 23, 2026
 */
public class BudgetApp {

    private InputReader input;
    private BudgetTracker tracker;
    private BudgetGoal goal;

    /**
     * Creates the app with an empty tracker, an unset goal, and a reader.
     */
    public BudgetApp() {
        input = new InputReader();
        tracker = new BudgetTracker();
        goal = new BudgetGoal();
    }

    /**
     * Starts the program.
     *
     * @param args command line arguments, not used
     */
    public static void main(String[] args) {
        BudgetApp app = new BudgetApp();
        app.run();
    }

    /**
     * Loops the menu until the user chooses to exit.
     */
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = input.readMenuChoice(1, 5);

            if (choice == 1) {
                handleAddTransaction();
            }
            else if (choice == 2) {
                handleSetGoal();
            }
            else if (choice == 3) {
                handleViewHistory();
            }
            else if (choice == 4) {
                handleViewStatus();
            }
            else if (choice == 5) {
                running = false;
                System.out.println("Goodbye");
                input.close();
            }
        }
    }

    /**
     * Prints the numbered menu options.
     */
    public void displayMenu() {
        System.out.println();
        System.out.println("===== Budget Tracker =====");
        System.out.println("1. Add Transaction");
        System.out.println("2. Set Goal");
        System.out.println("3. View History");
        System.out.println("4. View Status");
        System.out.println("5. Exit");
        System.out.println("==========================");
    }

    /**
     * Prompts for a transaction's details and records it. If the values are
     * rejected by the Transaction constructor, the error is reported and
     * nothing is added.
     */
    public void handleAddTransaction() {
        System.out.println();
        System.out.println("----- Add Transaction -----");

        double amount = input.readPositiveDouble("Enter amount: ");
        String type = input.readType("Enter type (income or expense): ");
        String description = input.readNonEmptyString("Enter description: ");

        try {
            Transaction transaction =
                new Transaction(amount, type, description, LocalDate.now());
            tracker.addTransaction(transaction);
            System.out.println("Added: " + transaction);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Could not add transaction: " + e.getMessage());
        }
    }

    /**
     * Prompts for a spending target and sets it on the goal.
     */
    public void handleSetGoal() {
        System.out.println();
        System.out.println("----- Set Goal -----");

        double amount = input.readPositiveDouble("Enter goal amount: ");
        goal.setGoal(amount);
        System.out.println("Goal set to $" + amount);
    }

    /**
     * Prints every recorded transaction, or a message if there are none.
     */
    public void handleViewHistory() {
        System.out.println();
        System.out.println("----- Transaction History -----");

        if (tracker.size() == 0) {
            System.out.println("No transactions yet.");
        }
        else {
            for (Transaction t : tracker.getHistory()) {
                System.out.println(t);
            }
        }
    }

    /**
     * Prints the current balance, total spent, and progress toward the goal.
     */
    public void handleViewStatus() {
        System.out.println();
        System.out.println("----- Budget Status -----");

        double spent = tracker.getTotalSpent();
        System.out.println("Balance: $" + tracker.getTotal());
        System.out.println("Total spent: $" + spent);

        if (goal.getGoal() == 0) {
            System.out.println("No goal set yet.");
        }
        else if (goal.isOverBudget(spent)) {
            System.out.println("You are $" + (spent - goal.getGoal())
                + " over your $" + goal.getGoal() + " goal.");
        }
        else {
            System.out.println("You have $" + goal.amountRemaining(spent)
                + " left of your $" + goal.getGoal() + " goal.");
        }
    }
}