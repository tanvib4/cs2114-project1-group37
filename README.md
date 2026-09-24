# cs2114-project1-group37
Building a budget that tracks expenses and income throughout a week, month, and  year.
# Budgetly

A lightweight command-line budget tracker written in Java. Budgetly lets you
record income and expense transactions, set a spending goal, and see at a
glance how your spending compares to that goal.

---

## Authors

| File | Author |
|---|---|
| `BudgetApp.java` | Golden You |
| `BudgetGoal.java` | Liangru Ji |
| `BudgetGoalTest.java` | Liangru Ji |
| `BudgetTracker.java` | Ehren Casto |
| `BudgetTrackerTest.java` | Ehren Casto |
| `InputReader.java` | Golden You |
| `Transaction.java` | Tanvi Bhat |
| `Transaction.java` | Tanvi Bhat |

---

## Features

- Add income or expense transactions with an amount, description, and
  automatic date stamp
- Set a monthly (or any-period) spending goal
- View the full transaction history in chronological order
- View your current balance, total spent, and goal progress at any time
- Robust input validation — every prompt re-asks until the answer is usable

---

## How to Run

1. Compile all five source files:

```bash
javac Transaction.java BudgetGoal.java BudgetTracker.java InputReader.java BudgetApp.java
```

2. Run the app:

```bash
java BudgetApp
```

---

## Menu Options

```
===== Budget Tracker =====
1. Add Transaction
2. Set Goal
3. View History
4. View Status
5. Exit
==========================
```

| Option | What it does |
|---|---|
| **1 Add Transaction** | Prompts for an amount, type (`income` or `expense`), and description. Records the transaction with today's date. |
| **2 Set Goal** | Sets a positive spending limit. Can be updated at any time. |
| **3 View History** | Prints every recorded transaction in the order it was added. |
| **4 View Status** | Shows current balance, total spent, and how much of the goal has been used or exceeded. |
| **5 Exit** | Closes the scanner and exits the program. |

---

## Project Structure

```
Budgetly/
├── BudgetApp.java       # Entry point; drives the menu loop and user interaction
├── BudgetGoal.java      # Stores the spending goal and performs goal calculations
├── BudgetTracker.java   # Stores transaction history and computes balance/total spent
├── InputReader.java     # All keyboard reading; re-prompts on invalid input
└── Transaction.java     # Represents one financial entry; validates its own data
```

### Class responsibilities

**`Transaction`**
Represents a single financial entry. Validates that the amount is positive
and the type is exactly `"income"` or `"expense"`. Once created, a
Transaction is immutable. Transactions are formatted for display by
`toString()`, which produces lines like:
```
2026-09-23 expense $45.0 Groceries
```

**`BudgetTracker`**
Owns the `ArrayList` of Transactions. Computes balance (income minus
expenses) and total spent on every call by iterating the full history, so
the values never drift out of sync with the recorded data.

**`BudgetGoal`**
Stores a single positive spending target. Provides `isOverBudget()`,
`amountRemaining()`, and `percentUsed()` given a total-spent value
supplied by `BudgetTracker`. Has no knowledge of transactions itself.

**`InputReader`**
The only class that reads from the keyboard. Every method loops and
re-prompts until it receives valid input, keeping validation logic out
of `BudgetApp`.

**`BudgetApp`**
Drives the program. Displays the menu, delegates reading to `InputReader`,
delegates arithmetic to `BudgetTracker` and `BudgetGoal`, and prints
results. Performs no calculations of its own.

---

## Known Issues

- `Transaction.java` line 18 contains a typo:
  `IllegalArugmentException` should be `IllegalArgumentException`.
  This will cause a compile error and must be fixed before the project
  will build.
- `BudgetGoal` has a no-arg constructor referenced in `BudgetApp`
  (`goal = new BudgetGoal()`) but the class only defines a one-argument
  constructor. Either a no-arg constructor needs to be added to
  `BudgetGoal`, or `BudgetApp` needs to be updated to match.

---

## Example Session

```
===== Budget Tracker =====
1. Add Transaction
...
Enter your choice: 1

----- Add Transaction -----
Enter amount: 2000
Enter type (income or expense): income
Enter description: September salary
Added: 2026-09-23 income $2000.0 September salary

Enter your choice: 2

----- Set Goal -----
Enter goal amount: 800
Goal set to $800.0

Enter your choice: 1

----- Add Transaction -----
Enter amount: 120
Enter type (income or expense): expense
Enter description: Groceries
Added: 2026-09-23 expense $120.0 Groceries

Enter your choice: 4

----- Budget Status -----
Balance: $1880.0
Total spent: $120.0
You have $680.0 left of your $800.0 goal.
```
