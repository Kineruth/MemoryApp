package memory.Memoryapp.Object;

/**
 * This class represents the user's personal diary.
 * PersonalDiary inherits from class Diary.
 */
public class PersonalDiary extends Diary {

    /**
     * Default Constructor
     */
    public PersonalDiary() {
        super();
    }

    /**
     * Parameterized Constructor
     * @param name this personalDiary's name.
     * @param image this personalDiary's profile image.
     * @param uid this personalDiary's ID.
     */
    public PersonalDiary(String name, String image, String uid) {
       super(name,image,uid);
    }

    /**
     * Sets all this personalDiary's parameters by a given other personalDiary.
     * @param p a given personalDiary to be set from.
     */
    public void setAll(PersonalDiary p){
       setAll((Diary)p);
    }
}
