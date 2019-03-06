package memory.Memoryapp.Object;

/**
 *This class represents a single Diary.
 * Its parameters are: Diary's name, its profile image and ID.
 */
public abstract class Diary {
    protected String name, image, uid;
    /**
    Default constructor
     */
    public Diary(){
    }
    /**
    Parameterized Constructor
     @param name this Diary's name.
     @param image this Diary's profile image.
     @param uid this Diary's ID.
     */
    public Diary(String name, String image, String uid) {
        this.name = name;
        this.image = image;
        this.uid = uid;
    }
    /**
    Copy constructor
     @param d the Diary to be copied from.
     */
    public Diary(Diary d){
        this.name = d.name;
        this.image = d.image;
        this.uid = d.uid;
    }
    /**
     @return this Diary's name
     */
    public String getName() {
        return name;
    }
    /**
    Sets this Diary's name
     @param name the new name to set from.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     @return this Diary's profile image
     */
    public String getImage() {
        return image;
    }
    /**
    Sets this Diary's profile image
     @param image the new image to be set from.
     */
    public void setImage(String image) {
        this.image = image;
    }
    /**
     @return this Diary's ID
     */
    public String getUid() {
        return uid;
    }
    /**
    Sets this Diary's ID
     @param uid the new ID to be set from.
     */
    public void setUid(String uid) {
        this.uid = uid;
    }
    /**
    Sets this Diary's parameters all together
     @param d the new diary to be set from.
     */
    public void setAll(Diary d){
        this.name = d.name;
        this.image = d.image;
        this.uid = d.uid;
    }
    /**
    Checks if a given obj is equal to this Diary by checking their unique ID.
     @param obj the object to be checked if equal to this Diary.
     @return true if they are the same.
     */
    @Override
    public boolean equals(Object obj) {
        return this.uid == ((Diary)obj).uid;
    }
}
