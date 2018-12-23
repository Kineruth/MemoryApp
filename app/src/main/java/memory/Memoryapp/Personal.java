package memory.Memoryapp;

public class Personal {
    private String name, image, uid;

    public Personal() {
    }

    public Personal(String name, String image, String uid) {
        this.name = name;
        this.image = image;
        this.uid = uid;
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

    public void setAll(Personal p){
        this.name = p.name;
        this.image = p.image;
        this.uid = p.uid;
    }
}
