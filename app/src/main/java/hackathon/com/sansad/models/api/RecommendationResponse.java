package hackathon.com.sansad.models.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import hackathon.com.sansad.models.Recommendation;

/**
 * Created by saketh on 17/11/15.
 */
public class RecommendationResponse {
    @SerializedName("error")
    @Expose
    private int error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ArrayList<Recommendation> data = new ArrayList<Recommendation>();

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
    public ArrayList<Recommendation> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(ArrayList<Recommendation> data) {
        this.data = data;
    }

}
