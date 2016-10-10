package dataprovider;

/**
 * Created by Patrick on 9/23/2016.
 */

public class Team {

    private int _id;
    private String teamId;
    private String name;
    private String userId;
    private String leagueId;

    public Team(int id, String tid, String nm, String uid, String lid){
        set_id(id);
        setTeamId(tid);
        setName(nm);
        setUserId(uid);
        setLeagueId(lid);
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }
}
