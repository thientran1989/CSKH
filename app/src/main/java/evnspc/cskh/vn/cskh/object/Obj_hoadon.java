package evnspc.cskh.vn.cskh.object;

import android.database.Cursor;

public class Obj_hoadon {
	public int ID_HDON;
	public String MA_KHANG;
	public int THANG;
	public int NAM;
	public int KY;
	public int SO_TIEN;
	public int TIEN_GTGT;
	public int TONG_TIEN;
    public int DIEN_TTHU;

    public int getID_HDON() {
        return ID_HDON;
    }

    public void setID_HDON(int ID_HDON) {
        this.ID_HDON = ID_HDON;
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

    public int getSO_TIEN() {
        return SO_TIEN;
    }

    public void setSO_TIEN(int SO_TIEN) {
        this.SO_TIEN = SO_TIEN;
    }

    public int getTIEN_GTGT() {
        return TIEN_GTGT;
    }

    public void setTIEN_GTGT(int TIEN_GTGT) {
        this.TIEN_GTGT = TIEN_GTGT;
    }

    public int getTONG_TIEN() {
        return TONG_TIEN;
    }

    public void setTONG_TIEN(int TONG_TIEN) {
        this.TONG_TIEN = TONG_TIEN;
    }

    public int getDIEN_TTHU() {
        return DIEN_TTHU;
    }

    public void setDIEN_TTHU(int DIEN_TTHU) {
        this.DIEN_TTHU = DIEN_TTHU;
    }

    //	public void set_obj(Cursor c) {
//		id_tieuthu = c.getLong(c.getColumnIndex(Utils.id_tieuthu));
//		ma_khachhang = ThtDatabase.get_cursor_string(c, Utils.ma_khachhang);
//		ngay = ThtDatabase.get_cursor_int(c, Utils.ngay);
//		thang = ThtDatabase.get_cursor_int(c, Utils.thang);
//		nam = ThtDatabase.get_cursor_int(c, Utils.nam);
//		ky = ThtDatabase.get_cursor_int(c, Utils.ky);
//		san_luong = ThtDatabase.get_cursor_string(c, Utils.san_luong);
//		so_tien = ThtDatabase.get_cursor_string(c, Utils.so_tien);
//		tt_xem = ThtDatabase.get_cursor_int(c, Utils.tt_xem);
//
//	}
	public static String get_SQL_TAO_BANG(){
        return  "create table "
                + tag_TABLE_HDON + "("
                + tag_ID_HDON + " INTEGER,"
                + tag_MA_KHANG + " TEXT,"
                + tag_THANG + " INTEGER,"
                + tag_NAM + " INTEGER,"
                + tag_KY + " INTEGER,"
                + tag_SO_TIEN + " INTEGER,"
                + tag_TIEN_GTGT + " INTEGER,"
                + tag_TONG_TIEN + " INTEGER,"
                + tag_DIEN_TTHU + " INTEGER " + ")";
    }
    public void set_obj(Cursor c) {
        ID_HDON = c.getInt(c.getColumnIndex(tag_ID_HDON));
        MA_KHANG = c.getString(c.getColumnIndex(tag_MA_KHANG));
        THANG = c.getInt(c.getColumnIndex(tag_THANG));
        NAM = c.getInt(c.getColumnIndex(tag_NAM));
        KY = c.getInt(c.getColumnIndex(tag_KY));
        SO_TIEN = c.getInt(c.getColumnIndex(tag_SO_TIEN));
        TIEN_GTGT = c.getInt(c.getColumnIndex(tag_TIEN_GTGT));
        TONG_TIEN = c.getInt(c.getColumnIndex(tag_TONG_TIEN));
        DIEN_TTHU = c.getInt(c.getColumnIndex(tag_DIEN_TTHU));

	}
    public static final String tag_TABLE_HDON ="HDN_HDON";

    public static final String tag_ID_HDON ="ID_HDON";
    public static final String tag_MA_KHANG ="MA_KHANG";
    public static final String tag_THANG ="THANG";
    public static final String tag_NAM ="NAM";
    public static final String tag_KY ="KY";
    public static final String tag_SO_TIEN ="SO_TIEN";
    public static final String tag_TIEN_GTGT ="TIEN_GTGT";
    public static final String tag_TONG_TIEN ="TONG_TIEN";
    public static final String tag_DIEN_TTHU ="DIEN_TTHU";

}
