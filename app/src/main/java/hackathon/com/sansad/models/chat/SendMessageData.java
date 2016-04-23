package hackathon.com.sansad.models.chat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import hackathon.com.sansad.models.api.Session;

/**
 * Developer: chipset
 * Package : com.webloominc.leuk.models.messages
 * Project : Leuk
 * Date : 24/11/15
 */
public class SendMessageData {

    @SerializedName("session")
    @Expose
    private Session session;

    /**
     * @return The session
     */
    public Session getSession() {
        return session;
    }

    /**
     * @param session The session
     */
    public void setSession(Session session) {
        this.session = session;
    }

}