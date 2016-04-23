package hackathon.com.sansad.models.api.getReviews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by saketh on 9/12/15.
 */
public class GetReviewsModel {
    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private GetReviewsResponse response;

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
    public GetReviewsResponse getResponse() {
        return response;
    }

    /**
     * @param response The response
     */
    public void setResponse(GetReviewsResponse response) {
        this.response = response;
    }

}
