
package hackathon.com.sansad.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("profile_img")
    @Expose
    private String profileImg;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;


    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("signup_timestamp")
    @Expose
    private String signupTimestamp;

    @SerializedName("gcmRegID")
    @Expose
    private String gcmRegID;
    /* @SerializedName("places_visited")
     @Expose
     private String placesVisited;
     @SerializedName("events_going_to")
     @Expose
     private String eventsGoingTo;*/

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The name
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
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return The profileImg
     */
    public String getProfileImg() {
        return profileImg;
    }

    /**
     * @param profileImg The profile_img
     */
    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    /**
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender The gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }


    /**
     * @return The dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob The dob
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return The signupTimestamp
     */
    public String getSignupTimestamp() {
        return signupTimestamp;
    }

    /**
     * @param signupTimestamp The signup_timestamp
     */
    public void setSignupTimestamp(String signupTimestamp) {
        this.signupTimestamp = signupTimestamp;
    }


    /**
     * @return The gcmRegID
     */
    public String getGcmRegID() {
        return gcmRegID;
    }

    /**
     * @param gcmRegID The gcmRegID
     */
    public void setGcmRegID(String gcmRegID) {
        this.gcmRegID = gcmRegID;
    }

}
