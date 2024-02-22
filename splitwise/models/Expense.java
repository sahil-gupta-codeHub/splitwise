package splitwise.models;

public class Expense {
    private Long id;
    private String description;
    private int amount;
    private ExpenseType expenseType;

    public Expense(Long id, String description, int amount, ExpenseType expenseType) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.expenseType = expenseType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }
}
