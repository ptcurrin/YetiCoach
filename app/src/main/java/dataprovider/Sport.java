package dataprovider;

/**
 * Created by Patrick on 9/23/2016.
 */

public class Sport {

    private int _id;
    private String sportId;
    private String name;
    private String description;
    private String imgPth;

    public Sport(int id, String sid, String nm, String desc, String ip){
        set_id(id);
        setSportId(sid);
        setName(nm);
        setDescription(desc);
        setImgPth(ip);
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getSportId() {
        return sportId;
    }

    public void setSportId(String sportId) {
        this.sportId = sportId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgPth() {
        return imgPth;
    }

    public void setImgPth(String imgPth) {
        this.imgPth = imgPth;
    }
}
