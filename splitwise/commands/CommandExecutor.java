package splitwise.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {
    private List<Command> commandList = new ArrayList<>();

    public CommandExecutor(SettleUpUserCommand settleUpUserCommand) {
        this.commandList.add(settleUpUserCommand);
    }

    public void addCommand(Command command){
        commandList.add(command);
    }

    public void removeCommand(Command command){
        commandList.remove(command);
    }
    public void execute(String cmd){
        for(Command command : commandList){
            if(command.matches(cmd)){
                command.execute(cmd);
            }
        }
    }
}
