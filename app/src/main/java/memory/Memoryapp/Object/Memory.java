package memory.Memoryapp.Object;

import java.util.Date;
import java.util.ArrayList;

public class Memory {
    private int memoryID;
    private String userID;
    private String memoryName;
    private String description;
    private Date creationTime;
    private ArrayList<String> images;

    public Memory(int memoryID, String userID, String memoryName, String description, long creationTime, ArrayList<String> images){
        this.memoryID = memoryID;
        this.userID = userID;
        this.memoryName = memoryName;
        this.description = description;
        this.creationTime = new Date();
        this.creationTime.setTime(creationTime);
        this.images = images;
    }

    public int getMemoryID() {
        return memoryID;
    }

    public String getUserID(){ return userID; }

    public void setUserID(String userID){ this.userID = userID; }

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

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}
