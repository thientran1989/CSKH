package evnspc.cskh.vn.cskh.object;


import android.database.Cursor;

import com.thtsoftlib.function.ThtDatabase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Obj_khachhang implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String MA_DVIQLY;
    public String MA_KHANG;
    public String SO_NHA;
    public String DUONG_PHO;
    public String DCHI_HDON;
    public String TEN_KHANG;
    public String DTHOAI;
    public String EMAIL;
    public String PASSWORD;
    public String ID_HDON;
    
    public long LAST_ID_HDON;
    public long LAST_ID_CHISO;
    public int DXEM_HDON;
    public int DXEM_CHISO;
    
    

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
    

	public long getLAST_ID_HDON() {
		return LAST_ID_HDON;
	}

	public void setLAST_ID_HDON(long lAST_ID_HDON) {
		LAST_ID_HDON = lAST_ID_HDON;
	}

	public long getLAST_ID_CHISO() {
		return LAST_ID_CHISO;
	}

	public void setLAST_ID_CHISO(long lAST_ID_CHISO) {
		LAST_ID_CHISO = lAST_ID_CHISO;
	}

	public int getDXEM_HDON() {
		return DXEM_HDON;
	}

	public void setDXEM_HDON(int dXEM_HDON) {
		DXEM_HDON = dXEM_HDON;
	}

	public int getDXEM_CHISO() {
		return DXEM_CHISO;
	}

	public void setDXEM_CHISO(int dXEM_CHISO) {
		DXEM_CHISO = dXEM_CHISO;
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
            LAST_ID_HDON = rs.getLong(tag_LAST_ID_HDON);
            LAST_ID_CHISO = rs.getLong(tag_LAST_ID_CHISO);
            DXEM_HDON = rs.getInt(tag_DXEM_HDON);
            DXEM_CHISO = rs.getInt(tag_DXEM_CHISO);   
        }catch(SQLException e){

        }

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
                + tag_LAST_ID_HDON + " LONG,"
                + tag_LAST_ID_CHISO + " LONG,"
                + tag_DXEM_HDON + " INTEGER,"
                + tag_DXEM_CHISO + " INTEGER,"
                + tag_EMAIL + " TEXT " + ")";
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
        LAST_ID_HDON = ThtDatabase.get_cursor_long(c, tag_LAST_ID_HDON);
        LAST_ID_CHISO = ThtDatabase.get_cursor_long(c, tag_LAST_ID_CHISO);
        DXEM_HDON = ThtDatabase.get_cursor_int(c, tag_DXEM_HDON);
        DXEM_CHISO = ThtDatabase.get_cursor_int(c, tag_DXEM_CHISO);
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
    
    public static final String tag_LAST_ID_HDON ="LAST_ID_HDON";
    public static final String tag_LAST_ID_CHISO ="LAST_ID_CHISO";
    public static final String tag_DXEM_HDON ="DXEM_HDON";
    public static final String tag_DXEM_CHISO ="DXEM_CHISO";
    
}
