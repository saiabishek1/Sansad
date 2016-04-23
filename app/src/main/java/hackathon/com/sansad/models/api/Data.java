
package hackathon.com.sansad.models.api;

import com.google.gson.annotations.Expose;

import hackathon.com.sansad.models.user.User;


public class Data {

    @Expose
    private Session session;
    @Expose
    private User user;

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

    /**
     * 
     * @return
     *     The user
     */
    public User getUser() {
        return user;
    }

    /**
     * 
     * @param User
     *     The user
     */
    public void setUser(User User) {
        this.user = User;
    }

}
