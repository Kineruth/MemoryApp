package memory.Memoryapp;

import android.icu.lang.UScript;

import java.util.ArrayList;
import java.util.List;

public class User {

    public String image, name, status, uid;
    public List<String> groupId = new ArrayList<>();

    public User() {
    }

    public User(String fullName ,String uid) {
        this.name = fullName;
        this.uid = uid;
        this.status = "";
        this.image = "";
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
        this.image = "";
    }

    public void setAll(User user){
        this.name = user.name;
        this.status = user.status;
        this.uid = user.uid;
        this.image = user.image;
        this.groupId.addAll(user.groupId);
    }
}
