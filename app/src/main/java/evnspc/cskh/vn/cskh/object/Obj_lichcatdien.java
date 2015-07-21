package evnspc.cskh.vn.cskh.object;

import com.thtsoftlib.function.ThtDatabase;
import android.database.Cursor;

import evnspc.cskh.vn.cskh.utils.Utils;


public class Obj_lichcatdien {
	public Long id_lichcatdien;
	public String ma_tram;
	public String ngay;
	public String khu_vuc;
	public String thoi_gian;
	public String pham_vi;
	public int tt_xem;
	
	public Long getId_lichcatdien() {
		return id_lichcatdien;
	}
	public void setId_lichcatdien(Long id_lichcatdien) {
		this.id_lichcatdien = id_lichcatdien;
	}
	public String getMa_tram() {
		return ma_tram;
	}
	public void setMa_tram(String ma_tram) {
		this.ma_tram = ma_tram;
	}
	public String getNgay() {
		return ngay;
	}
	public void setNgay(String ngay) {
		this.ngay = ngay;
	}
	public String getKhu_vuc() {
		return khu_vuc;
	}
	public void setKhu_vuc(String khu_vuc) {
		this.khu_vuc = khu_vuc;
	}
	public String getThoi_gian() {
		return thoi_gian;
	}
	public void setThoi_gian(String thoi_gian) {
		this.thoi_gian = thoi_gian;
	}
	public String getPham_vi() {
		return pham_vi;
	}
	public void setPham_vi(String pham_vi) {
		this.pham_vi = pham_vi;
	}
	public int getTt_xem() {
		return tt_xem;
	}
	public void setTt_xem(int tt_xem) {
		this.tt_xem = tt_xem;
	}
	public void set_obj(Cursor c) {
		id_lichcatdien = c.getLong(c.getColumnIndex(Utils.id_lichcatdien));
		ma_tram = ThtDatabase.get_cursor_string(c, tag_MA_TRAM);
		ngay = ThtDatabase.get_cursor_string(c, Utils.ngay);
		khu_vuc = ThtDatabase.get_cursor_string(c, Utils.khu_vuc);
		thoi_gian = ThtDatabase.get_cursor_string(c, Utils.thoi_gian);
		pham_vi = ThtDatabase.get_cursor_string(c, Utils.pham_vi);
		tt_xem = ThtDatabase.get_cursor_int(c, Utils.tt_xem);
	}
    public static String get_SQL_TAO_BANG (){
        return "create table "
                + Utils.table_lichcatdien + "("
                + Utils.id_lichcatdien + " Long,"
                + tag_MA_TRAM + " TEXT,"
                + Utils.ngay + " TEXT,"
                + Utils.khu_vuc + " TEXT,"
                + Utils.thoi_gian + " TEXT,"
                + Utils.pham_vi + " TEXT,"
                + Utils.tt_xem + " INTEGER " + ")";
    }
    public static final String tag_MA_TRAM="MA_TRAM";
}
