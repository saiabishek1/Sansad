package hackathon.com.sansad.models.chat;

import com.google.gson.annotations.Expose;

/**
 * Created by utk994 on 27-Jan-16.
 */
public class Chat {

    @Expose
    private String other_user;
    @Expose
    private String message;
    @Expose
    private String timestamp;
    @Expose
    private int unread;
    @Expose
    private String status;
    @Expose
    private String profile_pic;
    @Expose
    private String name;

    /**
     * @return The other_user
     */
    public String getOther_user() {
        return other_user;
    }

    /**
     * @param other_user The id
     */
    public void setOther_user(String other_user) {
        this.other_user = other_user;
    }


    public String getMessage() {
        return message;
    }

    /**
     * @param message The Minimum Spending
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The rating
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The rating
     */
    public void setStatus(String status) {
        this.status = status;
    }


    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp The timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return The timestamp
     */

    public int getUnread() {
        return unread;
    }

    /**
     * @param unread The unread meassages
     */
    public void setUnread(int unread) {
        this.unread = unread;
    }

    /**
     * @return The unread messages
     */

    public String getProfilePic() {
        return profile_pic;
    }

    /**
     * @param profile_pic The profile picture
     */
    public void setProfilePic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    /**
     * @return The profile picture
     */

    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The name
     */

}
