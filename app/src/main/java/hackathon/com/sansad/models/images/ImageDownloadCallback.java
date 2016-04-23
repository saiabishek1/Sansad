package hackathon.com.sansad.models.images;

/**
 * Created by utk994 on 08-Mar-16.
 */
public class ImageDownloadCallback {
    private ImageDownloadResponse response;


    private String status;

    private String code;

    public ImageDownloadResponse getImageDownloadResponse() {
        return response;
    }

    public void setImageDownloadResponse(ImageDownloadResponse response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ClassPojo [response = " + response + ", status = " + status + ", code = " + code + "]";
    }
}

