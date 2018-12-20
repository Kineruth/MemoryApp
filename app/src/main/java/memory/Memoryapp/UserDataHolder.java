package memory.Memoryapp;

public class UserDataHolder {

    private User user = null;
    private static final UserDataHolder data = new UserDataHolder();

    private UserDataHolder(){
        user = new User();
    }

    public static UserDataHolder getUserDataHolder(){
        return data;
    }

    public User getUser(){
        return user;
    }
}
