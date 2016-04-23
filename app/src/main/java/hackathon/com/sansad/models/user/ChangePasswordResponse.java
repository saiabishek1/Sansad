
package hackathon.com.sansad.models.user;

import com.google.gson.annotations.Expose;

public class ChangePasswordResponse {

    @Expose
    private int error;
    @Expose
    private String message;
    @Expose
    private ChangePasswordData data;

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
    public ChangePasswordData getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(ChangePasswordData data) {
        this.data = data;
    }

}
