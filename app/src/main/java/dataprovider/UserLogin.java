package dataprovider;

/**
 * Created by Patrick on 9/23/2016.
 */

public class UserLogin {

    private int _id;
    private String email = "";
    private String password = "";

    UserLogin(int id, String em, String pass){
        setId(id);
        setEmail(em);
        setPassword(pass);
    }

    public int getId(){
        return _id;
    }

    public void setId(int _id){
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
