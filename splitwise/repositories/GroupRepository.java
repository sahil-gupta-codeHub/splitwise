package splitwise.repositories;

import splitwise.models.Expense;
import splitwise.models.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupRepository {
    private List<Group> groupList = new ArrayList<>();

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }
    public List<Expense> findExpensesByGroup(String groupName){
        for(Group group: groupList){
            if(groupName.equals(group.getGroupName())){
                return group.getExpenseList();
            }
        }
        return new ArrayList<>();
    }
}
