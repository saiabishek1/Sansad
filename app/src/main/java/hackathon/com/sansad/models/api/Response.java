
package hackathon.com.sansad.models.api;

import com.google.gson.annotations.Expose;

public class Response {

    @Expose
    private int error;
    @Expose
    private String message;
    @Expose
    private DataLoggedIn data;

    /**
     * 
     * @return
     *     The error
     */
    public int getError() {
        return error;
    }

    /**
     * 
     * @param error
     *     The error
     */
    public void setError(int error) {
        this.error = error;
    }

    /**
     * 
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 
     * @return
     *     The data
     */
    public DataLoggedIn getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(DataLoggedIn data) {
        this.data = data;
    }

}
