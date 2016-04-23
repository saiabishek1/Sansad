package hackathon.com.sansad.models.chat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Developer: chipset
 * Package : com.webloominc.leuk.models.messages
 * Project : Leuk
 * Date : 24/11/15
 */
public class SendMessageResponse {

    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private SendMessageData data;

    /**
     * @return The error
     */
    public Integer getError() {
        return error;
    }

    /**
     * @param error The error
     */
    public void setError(Integer error) {
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
    public SendMessageData getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(SendMessageData data) {
        this.data = data;
    }

}