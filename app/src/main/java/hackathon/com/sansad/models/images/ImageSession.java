package hackathon.com.sansad.models.images;

/**
 * Created by utk994 on 08-Mar-16.
 */
public class ImageSession{
    private String token;

    private String sessionid;

    public String getToken ()
    {
        return token;
    }

    public void setToken (String token)
    {
        this.token = token;
    }

    public String getSessionid ()
    {
        return sessionid;
    }

    public void setSessionid (String sessionid)
    {
        this.sessionid = sessionid;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [token = "+token+", sessionid = "+sessionid+"]";
    }
}


