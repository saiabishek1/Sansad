package hackathon.com.sansad.models.places;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by utk994 on 10/2/2015.
 */
public class Places {



    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("phone")
    @Expose
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @SerializedName("minimum_spending")
    @Expose
    private String minimumSpending;
    @SerializedName("rating")
    @Expose
    private String rating;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("photo_link")
    @Expose
    private String photoLink;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("no_of_ratings")
    @Expose
    private String noOfRatings;
    @SerializedName("featured")
    @Expose
    private String featured;
    @SerializedName("popular")
    @Expose
    private String popular;
    @SerializedName("recommended")
    @Expose
    private String recommended;
    @SerializedName("leuk_admin")
    @Expose
    private String leukAdmin;
    @SerializedName("views")
    @Expose
    private String views;
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
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The minimumSpending
     */
    public String getMinimumSpending() {
        return minimumSpending;
    }

    /**
     *
     * @param minimumSpending
     * The minimum_spending
     */
    public void setMinimumSpending(String minimumSpending) {
        this.minimumSpending = minimumSpending;
    }

    /**
     *
     * @return
     * The rating
     */
    public String getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     * The rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     * The address
     */
    public void setAddress(String address) {
        this.address = address;
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
     * The latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The photoLink
     */
    public String getPhotoLink() {
        return photoLink;
    }

    /**
     *
     * @param photoLink
     * The photo_link
     */
    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    /**
     *
     * @return
     * The review
     */
    public String getReview() {
        return review;
    }

    /**
     *
     * @param review
     * The review
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     *
     * @return
     * The noOfRatings
     */
    public String getNoOfRatings() {
        return noOfRatings;
    }

    /**
     *
     * @param noOfRatings
     * The no_of_ratings
     */
    public void setNoOfRatings(String noOfRatings) {
        this.noOfRatings = noOfRatings;
    }

    /**
     *
     * @return
     * The featured
     */
    public String getFeatured() {
        return featured;
    }

    /**
     *
     * @param featured
     * The featured
     */
    public void setFeatured(String featured) {
        this.featured = featured;
    }

    /**
     *
     * @return
     * The popular
     */
    public String getPopular() {
        return popular;
    }

    /**
     *
     * @param popular
     * The popular
     */
    public void setPopular(String popular) {
        this.popular = popular;
    }

    /**
     *
     * @return
     * The recommended
     */
    public String getRecommended() {
        return recommended;
    }

    /**
     *
     * @param recommended
     * The recommended
     */
    public void setRecommended(String recommended) {
        this.recommended = recommended;
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

    public String getLeukAdmin() {
        return leukAdmin;
    }

    public void setLeukAdmin(String leukAdmin) {
        this.leukAdmin = leukAdmin;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }
}