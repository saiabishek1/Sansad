package hackathon.com.sansad;

import android.graphics.drawable.Drawable;

import java.util.Date;

/**
 * Created by utk994 on 28/04/15.
 */
public class RowItem {


    private String name;
    private String constit;
    private String points;
    private String rank;

    private Drawable profilePic;





    public RowItem(String name, String constit, String points, String rank, Drawable profilePic)
    {
        this.name = name;
        this.constit=constit;
        this.points=points;
        this.rank=rank;
        this.profilePic = profilePic;


    }


    public String getName() {
        return name;
    }
    public String getConstit() {
        return constit;
    }
    public String getPoints() {
        return points;
    }
    public String getRank() {
        return rank;
    }



    public Drawable getProfilePic() {
        return profilePic;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setConstit(String constit) {
        this.constit = constit;
    }

    public void setPoints(String points) {
        this.points = points;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }


    public void setProfilePic(Drawable profilePic) {
        this.profilePic = profilePic;
    }




}