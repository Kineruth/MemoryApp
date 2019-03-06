package memory.Memoryapp.Object;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Group Diary that extends class Diary.
 * Its parameters are: Diary's name, its profile image, ID
 * and the admin = the first user who created this group diary.
 * It has a list of all the group members.
 */
public class GroupDiary extends Diary {
    private String admin;
    private List<String> groupMember = new ArrayList<>();
    /**
     Default constructor
     */
    public GroupDiary() {
    }
    /**
     Parameterized constructor
     @param name this group Diary's name.
     @param image this group Diary's profile image.
     @param uid this group Diary's ID.
     @param firstUser this group Diary's creator (first user) = the admin.
     */
    public GroupDiary(String name, String image, String uid , String firstUser) {
        super(name,image,uid);
        this.admin = firstUser;
        this.groupMember.add(firstUser);
    }
    /**
     Copy constructor
     @param g the groupDiary to be copied from.
     */
    public GroupDiary(GroupDiary g){
        super(g.name,g.image,g.uid);
        this.admin = g.admin;
        this.groupMember.addAll(g.groupMember);
    }

    /**
     * @return this group Diary's admin.
     */
    public String getAdmin() {
        return admin;
    }

    /**
     * Sets this group Diary's admin.
     * @param admin
     */
    public void setAdmin(String admin) {
        this.admin = admin;
    }

    /**
     * @return this group Diary's members' list.
     */
    public List<String> getGroupMember() {
        return groupMember;
    }

    /**
     * Sets this group Diary's members' list.
     * @param groupMember the new members' list to be set from.
     */
    public void setGroupMember(List<String> groupMember) {
        this.groupMember = groupMember;
    }

    /**
     * Sets this group Diary's parameters all together.
     * @param g the new group Diary to be set from.
     */
    @Override
    public void setAll(Diary d) {
        super.setAll(d);
        this.admin = ((GroupDiary)d).admin;
        this.groupMember.clear();
        this.groupMember.addAll(((GroupDiary)d).groupMember);
    }
}
