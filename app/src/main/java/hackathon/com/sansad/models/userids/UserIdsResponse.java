package hackathon.com.sansad.models.userids;

import java.util.ArrayList;

/**
 * Created by utk994 on 25-Nov-15.
 */

public class UserIdsResponse
{
    private String message;

    private Integer error;

    private ArrayList<UserIds> data;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public Integer getError ()
    {
        return error;
    }

    public void setError (Integer error)
    {
        this.error = error;
    }

    public ArrayList<UserIds> getData ()
    {
        return data;
    }

    public void setData (ArrayList<UserIds> data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", error = "+error+", data = "+data+"]";
    }
}