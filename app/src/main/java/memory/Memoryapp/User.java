package memory.Memoryapp;

import android.icu.lang.UScript;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String image = "", name, status = "", uid;
    private List<String> groupId = new ArrayList<>();

    public User() {
    }

    public User(String fullName ,String uid) {
        this.name = fullName;
        this.uid = uid;
    }

    public User(String fullName, String status , String uid, String image) {
        this.name = fullName;
        this.status = status;
        this.uid = uid;
        this.image = image;
    }

    public User(String fullName, String status , String uid) {
        this.name = fullName;
        this.status = status;
        this.uid = uid;
    }

    public void setAll(User user){
        this.name = user.name;
        this.status = user.status;
        this.uid = user.uid;
        this.image = user.image;
        this.groupId.addAll(user.groupId);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<String> getGroupId() {
        return groupId;
    }

    public void setGroupId(List<String> groupId) {
        this.groupId = groupId;
    }
}
