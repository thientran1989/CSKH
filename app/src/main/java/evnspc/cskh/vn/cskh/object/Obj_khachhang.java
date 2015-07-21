package evnspc.cskh.vn.cskh.object;

import com.thtsoftlib.function.ThtDatabase;

import android.database.Cursor;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Obj_khachhang implements Serializable{
    public String MA_DVIQLY;
    public String MA_KHANG;
    public String SO_NHA;
    public String DUONG_PHO;
    public String DCHI_HDON;
    public String TEN_KHANG;
    public String DTHOAI;
    public String EMAIL;
    public String ID_HDON;
    public String PASSWORD;

    public String getID_HDON() {
        return ID_HDON;
    }

    public void setID_HDON(String ID_HDON) {
        this.ID_HDON = ID_HDON;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getMA_DVIQLY() {
        return MA_DVIQLY;
    }

    public void setMA_DVIQLY(String MA_DVIQLY) {
        this.MA_DVIQLY = MA_DVIQLY;
    }

    public String getMA_KHANG() {
        return MA_KHANG;
    }

    public void setMA_KHANG(String MA_KHANG) {
        this.MA_KHANG = MA_KHANG;
    }

    public String getSO_NHA() {
        return SO_NHA;
    }

    public void setSO_NHA(String SO_NHA) {
        this.SO_NHA = SO_NHA;
    }

    public String getDUONG_PHO() {
        return DUONG_PHO;
    }

    public void setDUONG_PHO(String DUONG_PHO) {
        this.DUONG_PHO = DUONG_PHO;
    }

    public String getDCHI_HDON() {
        return DCHI_HDON;
    }

    public void setDCHI_HDON(String DCHI_HDON) {
        this.DCHI_HDON = DCHI_HDON;
    }

    public String getTEN_KHANG() {
        return TEN_KHANG;
    }

    public void setTEN_KHANG(String TEN_KHANG) {
        this.TEN_KHANG = TEN_KHANG;
    }

    public String getDTHOAI() {
        return DTHOAI;
    }

    public void setDTHOAI(String DTHOAI) {
        this.DTHOAI = DTHOAI;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }



    public void set_result(ResultSet rs){
        try{
            MA_DVIQLY = rs.getString(tag_MA_DVIQLY);
            MA_KHANG = rs.getString(tag_MA_KHANG);
            SO_NHA = rs.getString(tag_SO_NHA);
            DUONG_PHO = rs.getString(tag_DUONG_PHO);
            DCHI_HDON = rs.getString(tag_DCHI_HDON);
            TEN_KHANG = rs.getString(tag_TEN_KHANG);
            DTHOAI = rs.getString(tag_DTHOAI);
            EMAIL = rs.getString(tag_EMAIL);
            ID_HDON = rs.getString(tag_DTHOAI);
            PASSWORD = rs.getString(tag_PASSWORD);
        }catch(SQLException e){

        }

    }
    public void set_obj(Cursor c) {
        MA_DVIQLY = ThtDatabase.get_cursor_string(c, tag_MA_DVIQLY);
        MA_KHANG = ThtDatabase.get_cursor_string(c, tag_MA_KHANG);
        SO_NHA = ThtDatabase.get_cursor_string(c, tag_SO_NHA);
        DUONG_PHO = ThtDatabase.get_cursor_string(c, tag_DUONG_PHO);
        DCHI_HDON = ThtDatabase.get_cursor_string(c, tag_DCHI_HDON);
        TEN_KHANG = ThtDatabase.get_cursor_string(c, tag_TEN_KHANG);
        DTHOAI = ThtDatabase.get_cursor_string(c, tag_DTHOAI);
        EMAIL = ThtDatabase.get_cursor_string(c, tag_EMAIL);
        ID_HDON = ThtDatabase.get_cursor_string(c, tag_ID_HDON);
        PASSWORD = ThtDatabase.get_cursor_string(c, tag_PASSWORD);
	}

	public static String get_SQL_TAO_BANG_KHANG (){
       return "create table "
                + tag_TABLE_KHANG + "("
                + tag_MA_DVIQLY + " TEXT,"
                + tag_MA_KHANG + " TEXT,"
                + tag_SO_NHA + " TEXT,"
                + tag_DUONG_PHO + " TEXT,"
                + tag_DCHI_HDON + " TEXT,"
                + tag_TEN_KHANG + " TEXT,"
                + tag_DTHOAI + " TEXT,"
                + tag_PASSWORD + " TEXT,"
                + tag_ID_HDON + " TEXT,"
                + tag_EMAIL + " TEXT " + ")";
    }

    public static final String tag_TABLE_KHANG ="KHANG";

    public static final String tag_MA_DVIQLY ="MA_DVIQLY";
    public static final String tag_MA_KHANG ="MA_KHANG";
    public static final String tag_SO_NHA ="SO_NHA";
    public static final String tag_DUONG_PHO ="DUONG_PHO";
    public static final String tag_DCHI_HDON ="DCHI_HDON";
    public static final String tag_TEN_KHANG ="TEN_KHANG";
    public static final String tag_DTHOAI ="DTHOAI";
    public static final String tag_EMAIL ="EMAIL";
    public static final String tag_ID_HDON ="ID_HDON";
    public static final String tag_PASSWORD ="PASSWORD";
}
