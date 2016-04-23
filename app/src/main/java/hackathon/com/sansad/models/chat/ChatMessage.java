package hackathon.com.sansad.models.chat;

/**
 * Created by utk994 on 20-Nov-15.
 */
public class ChatMessage {
    private boolean  isMine;
    private String content;
    private String timestamp;

    public ChatMessage(String message, boolean mine,String timestamp) {
        content = message;
        isMine = mine;
        this.timestamp=timestamp;

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setIsMine(boolean isMine) {
        this.isMine = isMine;
    }


}