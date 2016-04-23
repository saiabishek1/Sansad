package hackathon.com.sansad.models.images;

/**
 * Created by utk994 on 08-Mar-16.
 */
public class ImageDownloadData {
    private String likes;

    private String link;

    private String name;

    private String dislikes;

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDislikes() {
        return dislikes;
    }

    public void setDislikes(String dislikes) {
        this.dislikes = dislikes;
    }

    @Override
    public String toString() {
        return "ClassPojo [likes = " + likes + ", link = " + link + ", name = " + name + ", dislikes = " + dislikes + "]";
    }
}
