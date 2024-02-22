package splitwise.runner;

import splitwise.controllers.UserController;
import splitwise.dtos.Transaction;
import splitwise.models.*;
import splitwise.repositories.ExpenseRepository;
import splitwise.repositories.GroupRepository;
import splitwise.repositories.UserExpenseRepository;
import splitwise.repositories.UserRepository;
import splitwise.services.UserService;
import splitwise.strategies.HeapSettleStrategy;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        // 1. Create users
        User chaitanya = new User("chaitanya","abc", "1234", "4321");
        User amit = new User("amit", "abc","1234", "4321");
        User sundar = new User("sundar","abc", "1234", "4321");
        User ramesh = new User("ramesh","abc", "1234", "4321");

        List<User> goaGuys = new ArrayList<>();
        goaGuys.add(chaitanya);
        goaGuys.add(amit);
        goaGuys.add(sundar);
        goaGuys.add(ramesh);

        // 2. create the group and add these users to the group
        Group goaTrip = new Group(1L,"GOA-TRIP");
        goaTrip.setUserList(goaGuys);

        // They went for goa. Go for Dinner
        // 3. create expenses
        Expense dinnerExpense = new Expense(1L,"Dinner", 5000, ExpenseType.REGULAR);

        // 4. add the expense share of everyone
        UserExpense chaitanyaShare
                = new UserExpense(chaitanya, 1000,dinnerExpense,  UserExpenseType.HAD_TO_PAY);

        UserExpense amitShare
                = new UserExpense(amit, 1000,dinnerExpense,  UserExpenseType.HAD_TO_PAY);

        UserExpense sundarShare
                = new UserExpense(sundar,  1000, dinnerExpense,UserExpenseType.HAD_TO_PAY);

        UserExpense rameshShare
                = new UserExpense(ramesh,2000,dinnerExpense, UserExpenseType.HAD_TO_PAY);

        // 5. Capture who paid what
        UserExpense paidByChaitanya
                = new UserExpense(chaitanya,  3000, dinnerExpense,UserExpenseType.PAID_BY);

        UserExpense paidBySundar
                = new UserExpense(sundar,  2000, dinnerExpense,UserExpenseType.PAID_BY);

        // Manually add all these details to the database/repositories.

        UserRepository userRepository = new UserRepository();
        GroupRepository groupRepository = new GroupRepository();
        UserExpenseRepository userExpenseRepository = new UserExpenseRepository();
        ExpenseRepository expenseRepository = new ExpenseRepository();

        userRepository.setUserList(goaGuys);
        goaTrip.getExpenseList().add(dinnerExpense);
        groupRepository.getGroupList().add(goaTrip);
        expenseRepository.getExpenseList().add(dinnerExpense);

        userExpenseRepository.getExpenseList().add(chaitanyaShare);
        userExpenseRepository.getExpenseList().add(amitShare);
        userExpenseRepository.getExpenseList().add(sundarShare);
        userExpenseRepository.getExpenseList().add(rameshShare);

        userExpenseRepository.getExpenseList().add(paidByChaitanya);
        userExpenseRepository.getExpenseList().add(paidBySundar);
        UserController userController = new UserController(
                new UserService(groupRepository, userExpenseRepository, new HeapSettleStrategy()));

        List<Transaction> userTransactions = userController.settleUser("sundar", "GOA-TRIP");

        for(Transaction transaction: userTransactions){
            System.out.println(transaction.getFrom()+" -> "
                    +transaction.getTo()+" : "+transaction.getAmount());
        }

        /*
        Expected :
            Amit -> Chaitanya : 1000
            Sundar -> Chaitanya : 1000
            Ramesh -> Chaitanya : 2000
         */


    }
}
