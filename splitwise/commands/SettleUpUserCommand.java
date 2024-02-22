package splitwise.commands;

import splitwise.controllers.UserController;
import splitwise.dtos.Transaction;

import java.util.List;

public class SettleUpUserCommand implements Command{
    private UserController userController;

    public SettleUpUserCommand(UserController userController) {
        this.userController = userController;
    }

    public static final String SETTLE_UP_USER = "settleUser";
    @Override
    public boolean matches(String cmd) {
        List<String> words = List.of(cmd.split(" "));
        return words.get(2).equals(SETTLE_UP_USER);
    }

    @Override
    public void execute(String cmd) {
        List<String> words = List.of(cmd.split(" "));
        List<Transaction> transactions = userController.settleUser(words.get(0), words.get(1));

        for(Transaction transaction : transactions){
            System.out.println("FROM: " + transaction.getFrom() + "TO: " +
                                transaction.getTo() + "Amount: " + transaction.getAmount());
        }
    }

}
