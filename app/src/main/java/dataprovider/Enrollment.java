package dataprovider;

/**
 * Created by Patrick on 9/23/2016.
 */

public class Enrollment {

    private int _id;
    private String transId;
    private String parentId;
    private String teamId;

    public Enrollment(int id, String trid, String pid, String tmid){
        set_id(id);
        setTransId(trid);
        setParentId(pid);
        setTeamId(tmid);
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
