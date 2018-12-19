package memory.Memoryapp;

import java.util.Date;

public class Memory {
    private int memoryID;
    private String memoryName;
    private String description;
    private Date creationTime;
    private String imagesPath;

    public Memory(int memoryID, String memoryName, String description, long creationTime, String imagesPath){
        this.memoryID = memoryID;
        this.memoryName = memoryName;
        this.description = description;
        this.creationTime = new Date();
        this.creationTime.setTime(creationTime);
        this.imagesPath = imagesPath;
    }

    public int getMemoryID() {
        return memoryID;
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

    public String getImagesPath() {
        return imagesPath;
    }

    public void setImagesPath(String imagesPath) {
        this.imagesPath = imagesPath;
    }
}
