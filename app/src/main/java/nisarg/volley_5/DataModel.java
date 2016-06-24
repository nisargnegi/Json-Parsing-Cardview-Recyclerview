package nisarg.volley_5;

/**
 * Created by ravi negi on 17-06-2016.
 */
public class DataModel {

    String user_id;
    String descrip;
    String drawable;

    public DataModel(String user_id, String descrip, String drawable) {
        this.user_id = user_id;
        this.descrip = descrip;
        this.drawable = drawable;
    }


    public String getUser_id() {
        return user_id;
    }

    public String getDescrip() {
        return descrip;
    }

    public String getDrawable() {
        return drawable;
    }




}
