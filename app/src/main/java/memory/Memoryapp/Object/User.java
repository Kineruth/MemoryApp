package memory.Memoryapp.Object;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the user using the app.
 */
public class User {

    private String image = "", name, status = "", uid;
    private List<String> groupId = new ArrayList<>();

    /**
     * Default Constructor
     */
    public User() {groupId.clear(); }

    /**
     * Parameterized Constructor
     * @param fullName this user's full name.
     * @param uid this user ID.
     */
    public User(String fullName ,String uid) {
        this.name = fullName;
        this.uid = uid;
    }
    /**
     * Parameterized Constructor
     * @param fullName this user's full name.
     * @param status this user's status.
     * @param uid this user ID.
     * @param image this user's profile image
     */
    public User(String fullName, String status , String uid, String image) {
        this.name = fullName;
        this.status = status;
        this.uid = uid;
        this.image = image;
    }
    /**
     * Parameterized Constructor
     * @param fullName this user's full name.
     * @param status this user's status.
     * @param uid this user ID.
     */
    public User(String fullName, String status , String uid) {
        this.name = fullName;
        this.status = status;
        this.uid = uid;
    }

    /**
     * Sets all this User's parameters by a given other User.
     * @param user a given User to be set from.
     */
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

    @Override
    public String toString() {
        return "User{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", uid='" + uid + '\'' +
                ", groupId=" + groupId +
                '}';
    }
}
