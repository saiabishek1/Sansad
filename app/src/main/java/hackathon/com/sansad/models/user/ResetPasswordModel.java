
package hackathon.com.sansad.models.user;

import com.google.gson.annotations.Expose;

public class ResetPasswordModel {

    @Expose
    private int code;
    @Expose
    private String status;
    @Expose
    private ResetPasswordResponse response;

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
    public ResetPasswordResponse getResponse() {
        return response;
    }

    /**
     * @param response The response
     */
    public void setResponse(ResetPasswordResponse response) {
        this.response = response;
    }

}
