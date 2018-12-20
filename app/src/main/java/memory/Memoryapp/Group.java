package memory.Memoryapp;

import java.util.ArrayList;
import java.util.List;

public class Group {
    public String name, image, uid, manager;
    public List<String> groupMember = new ArrayList<>();

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
}
