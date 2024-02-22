package splitwise.controllers;

import splitwise.dtos.Transaction;
import splitwise.models.Expense;
import splitwise.services.UserService;

import java.util.List;

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    public List<Transaction> settleUser(String userName, String groupName){
        return userService.settleUser(userName, groupName);
    }
}
