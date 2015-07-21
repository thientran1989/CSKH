package evnspc.cskh.vn.cskh.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Admin on 7/20/2015.
 */
public class My_Convert {
    public static String timestamp2string (Timestamp my_time){
        String S ="loi";
        try {
            S = new SimpleDateFormat("dd/MM/yyyy").format(my_time);
        }catch (Exception e){

        }
        return  S;
    }
}
