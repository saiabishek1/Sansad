package hackathon.com.sansad.models.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by saketh on 2/11/15.
 */
public class Offer {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("offer_by_id")
    @Expose
    private String offerById;
    @SerializedName("offer_by")
    @Expose
    private String offerBy;
    @SerializedName("offer_title")
    @Expose
    private String offerTitle;
    @SerializedName("offer_desc")
    @Expose
    private String offerDesc;
    @SerializedName("offer_expiry")
    @Expose
    private String offerExpiry;
    @SerializedName("offer_link")
    @Expose
    private String offerLink;
    @SerializedName("offer_image_link")
    @Expose
    private String offerImageLink;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("coupon_basecode")
    @Expose
    private String copuonBasecode;
    @SerializedName("coupon_limit")
    @Expose
    private String couponLimit;
    @SerializedName("coupon_sales")
    @Expose
    private String couponSales;
    @SerializedName("coupon_cost")
    @Expose
    private String couponCost;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("recommended")
    @Expose
    private String recommended;
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
     * The offerBy
     */
    public String getOfferBy() {
        return offerBy;
    }

    /**
     *
     * @param offerBy
     * The offer_by
     */
    public void setOfferBy(String offerBy) {
        this.offerBy = offerBy;
    }

    /**
     *
     * @return
     * The offerTitle
     */
    public String getOfferTitle() {
        return offerTitle;
    }

    /**
     *
     * @param offerTitle
     * The offer_title
     */
    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }

    /**
     *
     * @return
     * The offerDesc
     */
    public String getOfferDesc() {
        return offerDesc;
    }

    /**
     *
     * @param offerDesc
     * The offer_desc
     */
    public void setOfferDesc(String offerDesc) {
        this.offerDesc = offerDesc;
    }

    /**
     *
     * @return
     * The offerExpiry
     */
    public String getOfferExpiry() {
        return offerExpiry;
    }

    /**
     *
     * @param offerExpiry
     * The offer_expiry
     */
    public void setOfferExpiry(String offerExpiry) {
        this.offerExpiry = offerExpiry;
    }

    /**
     *
     * @return
     * The offerLink
     */
    public String getOfferLink() {
        return offerLink;
    }

    /**
     *
     * @param offerLink
     * The offer_link
     */
    public void setOfferLink(String offerLink) {
        this.offerLink = offerLink;
    }

    /**
     *
     * @return
     * The offerImageLink
     */
    public String getOfferImageLink() {
        return offerImageLink;
    }

    /**
     *
     * @param offerImageLink
     * The offer_image_link
     */
    public void setOfferImageLink(String offerImageLink) {
        this.offerImageLink = offerImageLink;
    }

    /**
     *
     * @return
     * The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return
     * The copuonBasecode
     */
    public String getCopuonBasecode() {
        return copuonBasecode;
    }

    /**
     *
     * @param copuonBasecode
     * The copuon_basecode
     */
    public void setCopuonBasecode(String copuonBasecode) {
        this.copuonBasecode = copuonBasecode;
    }

    /**
     *
     * @return
     * The couponLimit
     */
    public String getCouponLimit() {
        return couponLimit;
    }

    /**
     *
     * @param couponLimit
     * The coupon_limit
     */
    public void setCouponLimit(String couponLimit) {
        this.couponLimit = couponLimit;
    }

    /**
     *
     * @return
     * The couponSales
     */
    public String getCouponSales() {
        return couponSales;
    }

    /**
     *
     * @param couponSales
     * The coupon_sales
     */
    public void setCouponSales(String couponSales) {
        this.couponSales = couponSales;
    }

    /**
     *
     * @return
     * The couponCost
     */
    public String getCouponCost() {
        return couponCost;
    }

    /**
     *
     * @param couponCost
     * The coupon_cost
     */
    public void setCouponCost(String couponCost) {
        this.couponCost = couponCost;
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

    public String getOfferById() {
        return offerById;
    }

    public void setOfferById(String offerById) {
        this.offerById = offerById;
    }

    public String getRecommended() {
        return recommended;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }
}