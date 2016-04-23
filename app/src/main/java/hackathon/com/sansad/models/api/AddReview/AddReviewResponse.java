package hackathon.com.sansad.models.api.AddReview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by saketh on 9/12/15.
 */
public class AddReviewResponse {@SerializedName("error")
@Expose
private int error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private AddReviewData data;

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
    public AddReviewData getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(AddReviewData data) {
        this.data = data;
    }

}
