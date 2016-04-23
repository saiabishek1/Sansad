package hackathon.com.sansad.models.userids;

/**
 * Created by utk994 on 25-Nov-15.
 */
public class UserIds {
    private String id;

    private String name;

    private String profile_img;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", name = " + name + ", profile_img = " + profile_img + "]";
    }
}