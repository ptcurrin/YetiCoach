package dataprovider;

/**
 * Created by Patrick on 9/23/2016.
 */

public class Transaction {

    private int _id;
    private String transId;
    private String purpose;
    private String amount;

    public Transaction(int id, String tid, String pps, String amt){
        set_id(id);
        setTransId(tid);
        setPurpose(pps);
        setAmount(amt);
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
