package splitwise.commands;

import splitwise.controllers.UserController;
import splitwise.repositories.GroupRepository;
import splitwise.repositories.UserExpenseRepository;
import splitwise.services.UserService;
import splitwise.strategies.HeapSettleStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CommandExecutor commandExecutor = new CommandExecutor(
                                            new SettleUpUserCommand(new UserController(
                                                      new UserService(new GroupRepository(),
                                                              new UserExpenseRepository(),
                                                              new HeapSettleStrategy()
                                                      ))));
        while(true){
            String input = scanner.nextLine();
            commandExecutor.execute(input);
        }
    }
}
