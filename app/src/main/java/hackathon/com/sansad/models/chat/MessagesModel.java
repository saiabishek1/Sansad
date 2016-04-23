package hackathon.com.sansad.models.chat;

import com.google.gson.annotations.Expose;

/**
 * Created by utk994 on 10/2/2015.
 */
public class MessagesModel {
    @Expose
    private int code;
    @Expose
    private String status;
    @Expose
    private MessagesResponse response;

    /**
     * @return The code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The response
     */
    public MessagesResponse getResponse() {
        return response;
    }

    /**
     * @param response The response
     */
    public void setResponse(MessagesResponse response) {
        this.response = response;
    }
}