package splitwise.repositories;

import splitwise.models.User;
import splitwise.models.UserExpense;

import java.util.ArrayList;
import java.util.List;

public class UserExpenseRepository {
    List<UserExpense> expenseList = new ArrayList<>();

    public List<UserExpense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<UserExpense> expenseList) {
        this.expenseList = expenseList;
    }
    public List<UserExpense> findUserExpenseByExpenseDescription(String description){
        List<UserExpense> userExpenses = new ArrayList<>();
        for(UserExpense userExpense : expenseList){
            if(description.equalsIgnoreCase(userExpense.getExpense().getDescription())){
                userExpenses.add(userExpense);
            }
        }
        return userExpenses;
    }
}
