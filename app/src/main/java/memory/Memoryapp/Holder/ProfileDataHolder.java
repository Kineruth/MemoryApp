package memory.Memoryapp.Holder;

import memory.Memoryapp.Object.User;

public class ProfileDataHolder {

    private User user = null;
    private static final ProfileDataHolder data = new ProfileDataHolder();

    private ProfileDataHolder(){
        user = new User();
    }

    public static ProfileDataHolder getUserDataHolder(){
        return data;
    }

    public User getUser(){
        return user;
    }
}
