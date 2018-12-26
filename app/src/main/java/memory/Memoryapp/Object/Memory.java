package memory.Memoryapp.Object;

import java.util.Date;
import java.util.ArrayList;

public class Memory {
    private String uid;
    private String memoryName;
    private String description;
    private Date creationTime;
    private String image;

    public Memory(String uid, String memoryName, String description, Date creationTime, String image) {
        this.uid = uid;
        this.memoryName = memoryName;
        this.description = description;
        this.creationTime = creationTime;
        this.image = image;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMemoryName() {
        return memoryName;
    }

    public void setMemoryName(String memoryName) {
        this.memoryName = memoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
