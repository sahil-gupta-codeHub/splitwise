package splitwise.models;

public class UserExpense {
    private User user;
    private int amount;
    private Expense expense;
    private UserExpenseType userExpenseType;

    public UserExpense(User user, int amount, Expense expense, UserExpenseType userExpenseType) {
        this.user = user;
        this.amount = amount;
        this.expense = expense;
        this.userExpenseType = userExpenseType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public UserExpenseType getUserExpenseType() {
        return userExpenseType;
    }

    public void setUserExpenseType(UserExpenseType userExpenseType) {
        this.userExpenseType = userExpenseType;
    }
}
