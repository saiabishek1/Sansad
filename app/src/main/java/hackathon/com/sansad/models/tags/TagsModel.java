package hackathon.com.sansad.models.tags;

/**
 * Created by utk994 on 24-Apr-16.
 */

public class TagsModel
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

