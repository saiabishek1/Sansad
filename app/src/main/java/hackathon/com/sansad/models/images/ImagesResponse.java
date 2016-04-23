package hackathon.com.sansad.models.images;

/**
 * Created by utk994 on 08-Mar-16.
 */
public class ImagesResponse {
    private String message;

    private String error;

    private ImageData data;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getError ()
    {
        return error;
    }

    public void setError (String error)
    {
        this.error = error;
    }

    public ImageData getData ()
    {
        return data;
    }

    public void setData (ImageData data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", error = "+error+", data = "+data+"]";
    }
}
