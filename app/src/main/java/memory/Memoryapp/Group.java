package memory.Memoryapp;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name, image, uid, manager;
    private List<String> groupMember = new ArrayList<>();

    public Group() {
    }

    public Group(String name) {
        this.name = name;
        this.image = "";
        this.uid = "";
        this.manager = "";
    }

    public Group(String name, String image, String uid , String user) {
        this.name = name;
        this.image = image;
        this.uid = uid;
        this.manager = user;
        this.groupMember.add(user);
    }

    public Group(Group g){
        this.name = g.name;
        this.image = g.image;
        this.uid = g.uid;
        this.manager = g.manager;
        this.groupMember.addAll(g.groupMember);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public void setAll(Group g){
        this.name = g.name;
        this.image = g.image;
        this.uid = g.uid;
        this.manager = g.manager;
        this.groupMember.clear();
        this.groupMember.addAll(g.groupMember);
    }
}
