package memory.Memoryapp.Object;

public abstract class Diary {
    protected String name, image, uid;

    public Diary(){
    }

    public Diary(String name, String image, String uid) {
        this.name = name;
        this.image = image;
        this.uid = uid;
    }

    public Diary(Diary d){
        this.name = d.name;
        this.image = d.image;
        this.uid = d.uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setAll(Diary d){
        this.name = d.name;
        this.image = d.image;
        this.uid = d.uid;
    }
}
