package hackathon.com.sansad.models.images;

import java.util.ArrayList;

/**
 * Created by utk994 on 08-Mar-16.
 */
public class ImageDownloadResponse {
    private String message;

    private String error;

    private ArrayList<ImageDownloadData> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public ArrayList<ImageDownloadData> getData() {
        return data;
    }

    public void setData(ArrayList<ImageDownloadData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo [message = " + message + ", error = " + error + ", data = " + data + "]";
    }
}
