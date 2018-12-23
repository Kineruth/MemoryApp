package memory.Memoryapp.Object;

import java.util.ArrayList;
import java.util.List;

public class GroupDiary extends Diary {
    private String manager;
    private List<String> groupMember = new ArrayList<>();

    public GroupDiary() {
    }

    public GroupDiary(String name, String image, String uid , String user) {
        super(name,image,uid);
        this.manager = user;
        this.groupMember.add(user);
    }

    public GroupDiary(GroupDiary g){
        super(g.name,g.image,g.uid);
        this.manager = g.manager;
        this.groupMember.addAll(g.groupMember);
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public List<String> getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(List<String> groupMember) {
        this.groupMember = groupMember;
    }

    public void setAll(GroupDiary g){
        setAll((Diary)g);
        this.manager = g.manager;
        this.groupMember.clear();
        this.groupMember.addAll(g.groupMember);
    }
}
