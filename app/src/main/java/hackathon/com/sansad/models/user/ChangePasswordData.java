
package hackathon.com.sansad.models.user;

import com.google.gson.annotations.Expose;

import hackathon.com.sansad.models.api.Session;

public class ChangePasswordData {

    @Expose
    private Session session;

    /**
     * 
     * @return
     *     The session
     */
    public Session getSession() {
        return session;
    }

    /**
     * 
     * @param session
     *     The session
     */
    public void setSession(Session session) {
        this.session = session;
    }

}
