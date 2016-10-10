package dataprovider;

/**
 * Created by Patrick on 9/23/2016.
 */

public class Parent {

    private int _id;
    private String parentId;
    private String playerId;
    private String userId;

    public Parent(int id, String parId, String plyrId, String uid){
        set_id(id);
        setParentId(parId);
        setPlayerId(plyrId);
        setUserId(uid);
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
