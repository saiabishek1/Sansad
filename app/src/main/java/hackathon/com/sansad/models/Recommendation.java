package hackathon.com.sansad.models;

/**
 * Created by utk994 on 25-Feb-16.
 */
public class Recommendation {
    public String placeName;
    public String distance;
    public String image;


    public String id;

    public Recommendation(String placeName1, String id, String distance1, String image1) {

        placeName = placeName1;
        distance = distance1;
        image = image1;
        this.id = id;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getImage1() {
        return image;
    }

    public void setImage1(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

}
