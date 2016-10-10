package dataprovider;

import java.util.Date;

/**
 * Created by Patrick on 9/23/2016.
 */

public class Player {

    private int _id;
    private String playerId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public Player(int id, String pid, String first, String last, Date dob){
        set_id(id);
        setPlayerId(pid);
        setFirstName(first);
        setLastName(last);
        setDateOfBirth(dob);
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
