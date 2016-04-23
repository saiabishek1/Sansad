package hackathon.com.sansad.models.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by saketh on 6/12/15.
 */
public class PlaceNews {@SerializedName("id")
@Expose
private String id;
    @SerializedName("news_title")
    @Expose
    private String newsTitle;
    @SerializedName("news_description")
    @Expose
    private String newsDescription;
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("link")
    @Expose
    private String link;
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
     * The newsTitle
     */
    public String getNewsTitle() {
        return newsTitle;
    }

    /**
     *
     * @param newsTitle
     * The news_title
     */
    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    /**
     *
     * @return
     * The newsDescription
     */
    public String getNewsDescription() {
        return newsDescription;
    }

    /**
     *
     * @param newsDescription
     * The news_description
     */
    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    /**
     *
     * @return
     * The placeId
     */
    public String getPlaceId() {
        return placeId;
    }

    /**
     *
     * @param placeId
     * The place_id
     */
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}