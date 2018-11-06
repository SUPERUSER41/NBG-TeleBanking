package nbg.telebanking.models;

/**
 * author Jevonne Laing
 */

public class Message {
    private int mess_id;
    private String body;
    private String  query_type;
    private String created_at;


    public Message() {
        this.mess_id=0;
        this.body="";
        this.query_type="";
        this.created_at="";
    }

    public Message(int mess_id, String body, String query_type, String created_at) {
        this.mess_id = mess_id;
        this.body = body;
        this.query_type = query_type;
        this.created_at = created_at;
    }

    public Message(Message obj){
        this.mess_id=obj.getMess_id();
        this.body=obj.getBody();
        this.query_type=obj.getQuery_type();
        this.created_at=obj.getCreated_at();
    }

    public int getMess_id() {
        return mess_id;
    }

    public void setMess_id(int mess_id) {
        this.mess_id = mess_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getQuery_type() {
        return query_type;
    }

    public void setQuery_type(String query_type) {
        this.query_type = query_type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
