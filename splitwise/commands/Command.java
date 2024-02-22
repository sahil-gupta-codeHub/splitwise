package splitwise.commands;

public interface Command {
    boolean matches(String cmd);
    void execute(String emd);
}
