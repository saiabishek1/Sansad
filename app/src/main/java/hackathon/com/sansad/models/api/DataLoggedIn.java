
package hackathon.com.sansad.models.api;

import com.google.gson.annotations.Expose;

public class DataLoggedIn {

    @Expose
    private Session session;
    @Expose
    private UserLoggedIn user;

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
    public UserLoggedIn getUser() {
        return user;
    }

    /**
     *
     * @param User
     *     The user
     */
    public void setUser(UserLoggedIn User) {
        this.user = User;
    }

}
