package evnspc.cskh.vn.cskh.object;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Obj_sanluong implements Serializable{

	private static final long serialVersionUID = 1L;
	public int ID_CHISO;
	public String MA_KHANG;
	public int THANG;
	public int NAM;
	public int KY;
	public int CHISO_CU;
	public int CHISO_MOI;
	public int SAN_LUONG;
    public String LOAI_CHISO;
    public Timestamp NGAY_DKY;
    public Timestamp NGAY_CKY;

    public static final String tag_ID_CHISO="ID_CHISO";
    public static final String tag_MA_KHANG="MA_KHANG";
    public static final String tag_THANG="THANG";
    public static final String tag_NAM="NAM";
    public static final String tag_KY="KY";
    public static final String tag_CHISO_CU="CHISO_CU";
    public static final String tag_CHISO_MOI="CHISO_MOI";
    public static final String tag_SAN_LUONG="SAN_LUONG";
    public static final String tag_LOAI_CHISO="LOAI_CHISO";
    public static final String tag_NGAY_DKY="NGAY_DKY";
    public static final String tag_NGAY_CKY="NGAY_CKY";

    public int getID_CHISO() {
        return ID_CHISO;
    }

    public void setID_CHISO(int ID_CHISO) {
        this.ID_CHISO = ID_CHISO;
    }

    public String getMA_KHANG() {
        return MA_KHANG;
    }

    public void setMA_KHANG(String MA_KHANG) {
        this.MA_KHANG = MA_KHANG;
    }

    public int getTHANG() {
        return THANG;
    }

    public void setTHANG(int THANG) {
        this.THANG = THANG;
    }

    public int getNAM() {
        return NAM;
    }

    public void setNAM(int NAM) {
        this.NAM = NAM;
    }

    public int getKY() {
        return KY;
    }

    public void setKY(int KY) {
        this.KY = KY;
    }

    public int getCHISO_CU() {
        return CHISO_CU;
    }

    public void setCHISO_CU(int CHISO_CU) {
        this.CHISO_CU = CHISO_CU;
    }

    public int getCHISO_MOI() {
        return CHISO_MOI;
    }

    public void setCHISO_MOI(int CHISO_MOI) {
        this.CHISO_MOI = CHISO_MOI;
    }

    public int getSAN_LUONG() {
        return SAN_LUONG;
    }

    public void setSAN_LUONG(int SAN_LUONG) {
        this.SAN_LUONG = SAN_LUONG;
    }

    public String getLOAI_CHISO() {
        return LOAI_CHISO;
    }

    public void setLOAI_CHISO(String LOAI_CHISO) {
        this.LOAI_CHISO = LOAI_CHISO;
    }

    public Timestamp getNGAY_DKY() {
        return NGAY_DKY;
    }

    public void setNGAY_DKY(Timestamp NGAY_DKY) {
        this.NGAY_DKY = NGAY_DKY;
    }

    public Timestamp getNGAY_CKY() {
        return NGAY_CKY;
    }

    public void setNGAY_CKY(Timestamp NGAY_CKY) {
        this.NGAY_CKY = NGAY_CKY;
    }

    public static String get_SQL_TAO_BANG(){
        return  "create table "
                + tag_TABLE_CHISO + "("
                + tag_ID_CHISO + " INTEGER,"
                + tag_MA_KHANG + " TEXT,"
                + tag_THANG + " INTEGER,"
                + tag_NAM + " INTEGER,"
                + tag_KY + " INTEGER,"
                + tag_CHISO_CU + " INTEGER,"
                + tag_CHISO_MOI + " INTEGER,"
                + tag_SAN_LUONG + " INTEGER,"
                + tag_NGAY_DKY + " DATE,"
                + tag_NGAY_CKY + " DATE,"
                + tag_LOAI_CHISO + " TEXT " + ")";
    }
    public static final String tag_TABLE_CHISO="GCS_CHISO";
    
    public void set_result(ResultSet rs){
        try{
            ID_CHISO= rs.getInt(tag_ID_CHISO);
            MA_KHANG= rs.getString(tag_MA_KHANG);
        	THANG= rs.getInt(tag_THANG);
        	NAM= rs.getInt(tag_NAM);
        	KY= rs.getInt(tag_KY);
        	CHISO_CU= rs.getInt(tag_CHISO_CU);
        	CHISO_MOI= rs.getInt(tag_CHISO_MOI);
        	SAN_LUONG= rs.getInt(tag_SAN_LUONG);
            LOAI_CHISO= rs.getString(tag_LOAI_CHISO);
            NGAY_DKY= rs.getTimestamp(tag_NGAY_DKY);
            NGAY_CKY= rs.getTimestamp(tag_NGAY_CKY);
        }catch(SQLException e){

        }

    }


}
