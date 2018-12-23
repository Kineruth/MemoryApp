package memory.Memoryapp;

public class GroupDataHolder {

    private Group group = null;
    private static final GroupDataHolder data = new GroupDataHolder();

    private GroupDataHolder(){
        group = new Group();
    }

    public static GroupDataHolder getGroupDataHolder(){
        return data;
    }

    public Group getGroup(){
        return group;
    }
}
