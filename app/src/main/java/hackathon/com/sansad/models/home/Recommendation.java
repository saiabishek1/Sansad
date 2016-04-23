package hackathon.com.sansad.models.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by saketh on 3/11/15.
 */
public class Recommendation {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("recommendation_title")
    @Expose
    private String recommendationTitle;
    @SerializedName("recommendation_host")
    @Expose
    private String recommendationHost;
    @SerializedName("image_link")
    @Expose
    private String imageLink;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The recommendationTitle
     */
    public String getRecommendationTitle() {
        return recommendationTitle;
    }

    /**
     *
     * @param recommendationTitle
     * The recommendation_title
     */
    public void setRecommendationTitle(String recommendationTitle) {
        this.recommendationTitle = recommendationTitle;
    }

    /**
     *
     * @return
     * The recommendationHost
     */
    public String getRecommendationHost() {
        return recommendationHost;
    }

    /**
     *
     * @param recommendationHost
     * The recommendation_host
     */
    public void setRecommendationHost(String recommendationHost) {
        this.recommendationHost = recommendationHost;
    }

    /**
     *
     * @return
     * The imageLink
     */
    public String getImageLink() {
        return imageLink;
    }

    /**
     *
     * @param imageLink
     * The image_link
     */
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    /**
     *
     * @return
     * The location
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}