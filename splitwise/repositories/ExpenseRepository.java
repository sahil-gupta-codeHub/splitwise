package splitwise.repositories;

import splitwise.models.Expense;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpenseRepository {
    private List<Expense> expenseList = new ArrayList<>();

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }
}
