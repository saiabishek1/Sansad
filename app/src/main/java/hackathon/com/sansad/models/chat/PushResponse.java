package hackathon.com.sansad.models.chat;


import hackathon.com.sansad.models.api.Response;

/**
 * Created by utk994 on 01-Feb-16.
 */

public class PushResponse
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
