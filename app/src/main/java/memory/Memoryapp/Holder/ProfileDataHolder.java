package memory.Memoryapp.Holder;

import memory.Memoryapp.Object.User;

public class ProfileDataHolder {

    private User profile = null;
    private static final ProfileDataHolder data = new ProfileDataHolder();

    private ProfileDataHolder(){
        profile = new User();
    }

    public static ProfileDataHolder getProfileDataHolder(){
        return data;
    }

    public User getProfile(){
        return profile;
    }

    public void clearProfile(){
        profile.setAll(new User());
    }
}
