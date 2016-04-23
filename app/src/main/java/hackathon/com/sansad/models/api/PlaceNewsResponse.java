package hackathon.com.sansad.models.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saketh on 6/12/15.
 */
public class PlaceNewsResponse{

    @SerializedName("error")
    @Expose
    private int error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<PlaceNews> data = new ArrayList<PlaceNews>();

    /**
     *
     * @return
     * The error
     */
    public int getError() {
        return error;
    }

    /**
     *
     * @param error
     * The error
     */
    public void setError(int error) {
        this.error = error;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The data
     */
    public List<PlaceNews> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<PlaceNews> data) {
        this.data = data;
    }

}