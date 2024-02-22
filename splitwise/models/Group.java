package splitwise.models;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private Long groupId;
    private String groupName;
    private List<User> userList;

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    private List<Expense> expenseList;

    public Group(Long groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.userList = new ArrayList<>();
        this.expenseList = new ArrayList<>();
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}
