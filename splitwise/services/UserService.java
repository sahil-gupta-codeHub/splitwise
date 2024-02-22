package splitwise.services;

import splitwise.dtos.Transaction;
import splitwise.models.*;
import splitwise.repositories.GroupRepository;
import splitwise.repositories.UserExpenseRepository;
import splitwise.strategies.SettleUpStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserService {

    private GroupRepository groupRepository;
    private UserExpenseRepository userExpenseRepository;
    private SettleUpStrategy settleUpStrategy;

    public UserService(GroupRepository groupRepository, UserExpenseRepository userExpenseRepository, SettleUpStrategy settleUpStrategy) {
        this.groupRepository = groupRepository;
        this.userExpenseRepository = userExpenseRepository;
        this.settleUpStrategy = settleUpStrategy;
    }

    public List<Transaction> settleUser(String userName, String groupName) {
        HashMap<User, Integer> amountPerUserMap = new HashMap<>();
        //get all the expenses for a group
        List<Expense> expenseList = groupRepository.findExpensesByGroup(groupName);

        for(Expense expense : expenseList){
            //get all the user expenses for an expense
            List<UserExpense> userExpenseList = userExpenseRepository.findUserExpenseByExpenseDescription(expense.getDescription());
            //get the final amount paid or received for an user
            for(UserExpense userExpense : userExpenseList){
                if(!amountPerUserMap.containsKey(userExpense.getUser())){
                    amountPerUserMap.put(userExpense.getUser(), 0);
                }
                int amount = amountPerUserMap.get(userExpense.getUser());
                if(UserExpenseType.PAID_BY.equals(userExpense.getUserExpenseType())){
                    amount = amount + userExpense.getAmount();
                }
                else if(UserExpenseType.HAD_TO_PAY.equals(userExpense.getUserExpenseType())){
                    amount = amount - userExpense.getAmount();
                }
                amountPerUserMap.put(userExpense.getUser(), amount);
            }
        }

        //find all the transaction for a specific strategy
        List<Transaction> groupTransactions = settleUpStrategy.settleUpUsers(amountPerUserMap);
        List<Transaction> userTransactions = new ArrayList<>();

        for(Transaction transaction : groupTransactions){
            if(transaction.getFrom().equalsIgnoreCase(userName) || transaction.getTo().equalsIgnoreCase(userName)){
                userTransactions.add(transaction);
            }
        }
        return userTransactions;
    }
}
