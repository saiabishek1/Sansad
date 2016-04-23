package hackathon.com.sansad.models.images;

import com.google.gson.annotations.Expose;

/**
 * Created by utk994 on 08-Mar-16.
 */
public class ImageUploadCallback {
    @Expose
    private int code;
    @Expose
    private String status;
    @Expose
    private ImagesResponse response;

    /**
     *
     * @return
     *     The code
     */
    public int getCode() {
        return code;
    }

    /**
     *
     * @param code
     *     The code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     *
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     *     The response
     */
    public ImagesResponse getResponse() {
        return response;
    }

    /**
     *
     * @param response
     *     The response
     */
    public void setResponse(ImagesResponse response) {
        this.response = response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [response = "+response+", status = "+status+", code = "+code+"]";
    }

}


