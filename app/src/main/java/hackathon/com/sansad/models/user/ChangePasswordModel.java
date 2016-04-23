
package hackathon.com.sansad.models.user;

import com.google.gson.annotations.Expose;

public class ChangePasswordModel {

    @Expose
    private int code;
    @Expose
    private String status;
    @Expose
    private ChangePasswordResponse response;

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
    public ChangePasswordResponse getResponse() {
        return response;
    }

    /**
     * @param response The response
     */
    public void setResponse(ChangePasswordResponse response) {
        this.response = response;
    }

}
