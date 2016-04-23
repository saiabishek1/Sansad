package hackathon.com.sansad.models.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saketh on 19/11/15.
 */
public class LocationResponse {@SerializedName("error")
@Expose
private int error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<LocationData> data = new ArrayList<LocationData>();

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
    public List<LocationData> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<LocationData> data) {
        this.data = data;
    }

}
