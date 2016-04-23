package hackathon.com.sansad.models.images;

/**
 * Created by utk994 on 08-Mar-16.
 */
public class ImageData {
    private ImageSession session;

    private String url;

    public ImageSession getSession ()
    {
        return session;
    }

    public void setSession (ImageSession session)
    {
        this.session = session;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [session = "+session+", url = "+url+"]";
    }
}
