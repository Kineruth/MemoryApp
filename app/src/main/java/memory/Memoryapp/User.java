package memory.Memoryapp;

public class User {

    public String name, status, uid;

    public User() {
    }

    public User(String fullName ,String uid) {
        this.name = fullName;
        this.uid = uid;
        this.status = "";
    }

    public User(String fullName, String status , String uid) {
        this.name = fullName;
        this.status = status;
        this.uid = uid;
    }
}
