package memory.Memoryapp.Object;

import java.util.Date;
import java.util.ArrayList;

/**
 * This class represents a single Memory in a Diary.
 * A Memory is an image and its description. It has other parameters.
 */
public class Memory {
    private int memoryID;
    private String userID;
    private String memoryName;
    private String description;
    private Date creationTime;
    private ArrayList<String> images;

    /**
     * Parameterized Constructor
     * @param memoryID this Memory's ID.
     * @param userID this Memory's user (who created it) ID.
     * @param memoryName this Memory's name.
     * @param description this Memory's description.
     * @param creationTime the time this Memory has been created.
     * @param images
     */
    public Memory(int memoryID, String userID, String memoryName, String description, long creationTime, ArrayList<String> images){
        this.memoryID = memoryID;
        this.userID = userID;
        this.memoryName = memoryName;
        this.description = description;
        this.creationTime = new Date();
        this.creationTime.setTime(creationTime);
        this.images = images;
    }

    /**
     *
     * @return this Memory's ID.
     */
    public int getMemoryID() {
        return memoryID;
    }

    /**
     * @return this Memory's user (creator) ID.
     */
    public String getUserID(){ return userID; }

    /**
     * Sets this Memory's user (creator) ID.
     * @param userID the ID to be set from.
     */
    public void setUserID(String userID){ this.userID = userID; }

    /**
     * @return this Memory's name.
     */
    public String getMemoryName() {
        return memoryName;
    }

    /**
     * Sets this Memory's name.
     * @param memoryName the name to be set from.
     */
    public void setMemoryName(String memoryName) {
        this.memoryName = memoryName;
    }

    /**
     * @return this Memory's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets this Memory's description.
     * @param description the description to be set from.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return this Memory's creation time.
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * Sets this Memory's creation time.
     * @param creationTime the creation time to be set from.
     */
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * @return
     */
    public ArrayList<String> getImages() {
        return images;
    }

    /**
     *
     * @param images
     */
    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}
