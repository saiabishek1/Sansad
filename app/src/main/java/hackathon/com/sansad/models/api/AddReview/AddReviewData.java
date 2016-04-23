package hackathon.com.sansad.models.api.AddReview;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import hackathon.com.sansad.models.api.Session;


/**
 * Created by saketh on 9/12/15.
 */
public class AddReviewData {@SerializedName("session")
@Expose
private Session session;

    /**
     *
     * @return
     * The session
     */
    public Session getSession() {
        return session;
    }

    /**
     *
     * @param session
     * The session
     */
    public void setSession(Session session) {
        this.session = session;
    }

}