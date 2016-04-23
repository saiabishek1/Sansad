
package hackathon.com.sansad.models.api;

import com.google.gson.annotations.Expose;

public class Session {

    @Expose
    private String sessionid;
    @Expose
    private String token;
    @Expose
    private int expires;

    /**
     * 
     * @return
     *     The sessionid
     */
    public String getSessionid() {
        return sessionid;
    }

    /**
     * 
     * @param sessionid
     *     The sessionid
     */
    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    /**
     * 
     * @return
     *     The token
     */
    public String getToken() {
        return token;
    }

    /**
     * 
     * @param token
     *     The token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 
     * @return
     *     The expires
     */
    public int getExpires() {
        return expires;
    }

    /**
     * 
     * @param expires
     *     The expires
     */
    public void setExpires(int expires) {
        this.expires = expires;
    }

}
