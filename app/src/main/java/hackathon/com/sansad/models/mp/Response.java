package hackathon.com.sansad.models.mp;

import java.util.ArrayList;

/**
 * Created by utk994 on 23-Apr-16.
 */
public class Response
{
    private String message;

    private String error;

    private ArrayList<MpData> data;

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

    public ArrayList<MpData> getData ()
    {
        return data;
    }

    public void setData ( ArrayList<MpData> data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", error = "+error+", data = "+data+"]";
    }
}

