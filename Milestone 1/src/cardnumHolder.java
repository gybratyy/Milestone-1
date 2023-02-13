import dbpack.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class cardnumHolder {
    static dbinter d = new dbmeth();
    public static int getCardNum(String username, String password) throws SQLException {
        d.connect();
        String givenum = "select card_number from users where username='"+username+"'and password='"+password+"';";
        ResultSet r = d.result(givenum);
        if(r.next()){
            return r.getInt("card_number");
        }
        else{
            return 0;
        }

    }
}
