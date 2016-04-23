package hackathon.com.sansad.models.places;

import com.google.gson.annotations.Expose;

/**
 * Created by utk994 on 10/2/2015.
 */
public class PlacesModel {

    @Expose
    private int code;
    @Expose
    private String status;
    @Expose
    private PlacesResponse response;

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
    public PlacesResponse getResponse() {
        return response;
    }

    /**
     *
     * @param response
     *     The response
     */
    public void setResponse(PlacesResponse response) {
        this.response = response;
    }

}
