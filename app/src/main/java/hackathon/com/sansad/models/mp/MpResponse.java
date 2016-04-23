package hackathon.com.sansad.models.mp;

/**
 * Created by utk994 on 23-Apr-16.
 */
public class MpResponse
{
    private Response response;

    private String status;

    private String code;

    public Response getResponse ()
    {
        return response;
    }

    public void setResponse (Response response)
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

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [response = "+response+", status = "+status+", code = "+code+"]";
    }
}


