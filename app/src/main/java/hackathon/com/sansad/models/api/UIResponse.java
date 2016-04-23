package hackathon.com.sansad.models.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import hackathon.com.sansad.models.user.User;

/**
 * Created by saketh on 17/11/15.
 */
public class UIResponse {
    @SerializedName("error")
    @Expose
    private int error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private User data;

    /**
     * @return The error
     */
    public int getError() {
        return error;
    }

    /**
     * @param error The error
     */
    public void setError(int error) {
        this.error = error;
    }

    /**
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The data
     */
    public User getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(User data) {
        this.data = data;
    }
}
