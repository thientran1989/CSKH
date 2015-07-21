package evnspc.cskh.vn.cskh.object;

import com.thtsoftlib.function.ThtDatabase;
import android.database.Cursor;

import evnspc.cskh.vn.cskh.utils.Utils;


public class Obj_phanhoi {
	public Long id_phanhoi;
	public int ngay;
	public int thang;
	public int nam;
	public String noidung_phanhoi;
	public int loai_phanhoi;
	public int tt_phanhoi;
	public String ma_khachhang;
	
	
	
	public String getMa_khachhang() {
		return ma_khachhang;
	}
	public void setMa_khachhang(String ma_khachhang) {
		this.ma_khachhang = ma_khachhang;
	}
	public void setId_phanhoi(Long id_phanhoi) {
		this.id_phanhoi = id_phanhoi;
	}
	public int getNgay() {
		return ngay;
	}
	public void setNgay(int ngay) {
		this.ngay = ngay;
	}
	public int getThang() {
		return thang;
	}
	public void setThang(int thang) {
		this.thang = thang;
	}
	public int getNam() {
		return nam;
	}
	public void setNam(int nam) {
		this.nam = nam;
	}
	public String getNoidung_phanhoi() {
		return noidung_phanhoi;
	}
	public void setNoidung_phanhoi(String noidung_phanhoi) {
		this.noidung_phanhoi = noidung_phanhoi;
	}
	public int getLoai_phanhoi() {
		return loai_phanhoi;
	}
	public void setLoai_phanhoi(int loai_phanhoi) {
		this.loai_phanhoi = loai_phanhoi;
	}
	public int getTt_phanhoi() {
		return tt_phanhoi;
	}
	public void setTt_phanhoi(int tt_phanhoi) {
		this.tt_phanhoi = tt_phanhoi;
	}
	public Long getId_phanhoi() {
		return id_phanhoi;
	}
	public void set_obj(Cursor c) {
		id_phanhoi = c.getLong(c.getColumnIndex(Utils.id_phanhoi));
		ngay = ThtDatabase.get_cursor_int(c, Utils.ngay);
//		thang = ThtDatabase.get_cursor_int(c, Utils.thang);
//		nam = ThtDatabase.get_cursor_int(c, Utils.nam);
		noidung_phanhoi = ThtDatabase.get_cursor_string(c, Utils.noidung_phanhoi);
		loai_phanhoi = ThtDatabase.get_cursor_int(c, Utils.loai_phanhoi);
		tt_phanhoi = ThtDatabase.get_cursor_int(c, Utils.tt_phanhoi);
		
	}
	public static String get_SQL_TAO_BANG(){
        return "create table "
                + Utils.table_phanhoi + "("
                + Utils.id_phanhoi + " Long,"
                + Utils.ngay + " INTEGER,"
//                + Utils.thang + " INTEGER,"
//                + Utils.nam + " INTEGER,"
                + Utils.noidung_phanhoi + " TEXT,"
                + Utils.loai_phanhoi + " INTEGER,"
                + tag_MA_KHANG + " TEXT,"
                + Utils.tt_phanhoi + " INTEGER " + ")";
    }
    public static final String tag_MA_KHANG="MA_KHANG";

}
