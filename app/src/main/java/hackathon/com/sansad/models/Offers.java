package hackathon.com.sansad.models;

/**
 * Created by utk994 on 08-Mar-16.
 */
public class Offers {
    public String offerName;
    public String image;

    public Offers(String offerName1, String image1) {

        offerName = offerName1;
        image = image1;
    }

    public String getofferName() {
        return offerName;
    }

    public void setofferName(String offerName) {
        this.offerName = offerName;
    }


    public String getImage1() {
        return image;
    }

    public void setImage1(String image) {
        this.image = image;
    }
}
