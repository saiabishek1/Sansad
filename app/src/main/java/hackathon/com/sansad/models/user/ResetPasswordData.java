
package hackathon.com.sansad.models.user;

import com.google.gson.annotations.Expose;

public class ResetPasswordData {

    @Expose
    private String url;

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
