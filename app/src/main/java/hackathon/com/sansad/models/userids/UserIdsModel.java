package hackathon.com.sansad.models.userids;

/**
 * Created by utk994 on 25-Nov-15.
 */

public class UserIdsModel
{
    private UserIdsResponse response;

    private String status;

    private Integer code;

    public UserIdsResponse getResponse ()
    {
        return response;
    }

    public void setResponse (UserIdsResponse response)
    {
        this.response = response;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public Integer getCode ()
    {
        return code;
    }

    public void setCode (Integer code)
    {
        this.code = code;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [response = "+response+", status = "+status+", code = "+code+"]";
    }
}
