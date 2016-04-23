package hackathon.com.sansad.models.places;

/**
 * Created by utk994 on 10/2/2015.
 */
import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class PlacesResponse {

    @Expose
    private int error;
    @Expose
    private String message;
    @Expose
    private ArrayList<Places> data;

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
    public ArrayList<Places> getData() {
        return data;
    }

    /**
     *
     * @param data
     *     The data
     */
    public void setData(ArrayList<Places> data) {
        this.data = data;
    }

}
