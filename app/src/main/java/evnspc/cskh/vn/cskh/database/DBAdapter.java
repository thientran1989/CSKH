package evnspc.cskh.vn.cskh.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.thtsoftlib.function.ThtDatabase;
import evnspc.cskh.vn.cskh.object.Obj_khachhang;
import evnspc.cskh.vn.cskh.object.Obj_lichcatdien;
import evnspc.cskh.vn.cskh.object.Obj_phanhoi;
import evnspc.cskh.vn.cskh.object.Obj_hoadon;
import evnspc.cskh.vn.cskh.utils.Utils;

public class DBAdapter {
	private DatabaseHelper mDbHelper;
	private static SQLiteDatabase mDB;

	private final Context mContext;
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "evnsmartcare";

	// private static void NangCap(String cmd) {
	// try {
	// mDB.execSQL(cmd);
	// } catch (Exception e) {
	// //int j = 0;
	// }
	// }

	public DBAdapter open() {
		mDbHelper = new DatabaseHelper(mContext, DATABASE_NAME, null,
				DATABASE_VERSION);
		mDB = mDbHelper.getWritableDatabase();

		// NangCap("ALTER TABLE HSO_CHIETTINH ADD COLUMN DA_XONG INTEGER NULL");
		// NangCap("ALTER TABLE HSO_CHIETTINH ADD COLUMN SO_HO String NULL");
		// NangCap("ALTER TABLE HSO_CHIETTINH ADD COLUMN NGAY_KS String NULL");
		// NangCap("ALTER TABLE HSO_CHIETTINH ADD COLUMN Xt double DEFAULT 0 NULL");
		// NangCap("ALTER TABLE HSO_CHIETTINH ADD COLUMN Yt double DEFAULT 0 NULL");
		// NangCap("ALTER TABLE HSO_CHIETTINH ADD COLUMN Xp double DEFAULT 0 NULL");
		// NangCap("ALTER TABLE HSO_CHIETTINH ADD COLUMN Yp double DEFAULT 0 NULL");
		//
		// NangCap(CREATE_LIENKET_VATTU);
		// NangCap(CREATE_DANHMUC_TRAM);

		return this;
	}

	public void close() {
		mDbHelper.close();
		mDB.close();
	}

	public DBAdapter(Context mContext) {
		this.mContext = mContext;
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context, String Name, CursorFactory factory,
				int version) {
			super(context, Name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(Obj_khachhang.get_SQL_TAO_BANG_KHANG());
			db.execSQL(Obj_lichcatdien.get_SQL_TAO_BANG());
			db.execSQL(Obj_phanhoi.get_SQL_TAO_BANG());
			db.execSQL(Obj_hoadon.get_SQL_TAO_BANG());

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + Obj_khachhang.tag_TABLE_KHANG);
			db.execSQL("DROP TABLE IF EXISTS " + Utils.table_lichcatdien);
			db.execSQL("DROP TABLE IF EXISTS " + Utils.table_phanhoi);
//			db.execSQL("DROP TABLE IF EXISTS " + Obj_hoadon..table_tieuthu);
			onCreate(db);

		}
	}

	// KHACH HANG
	public static void Insert_khachhang(Obj_khachhang kHang) {
        mDB.beginTransaction();
        try {
            ContentValues initVal = new ContentValues();
            initVal.put(kHang.tag_MA_DVIQLY, kHang.getMA_DVIQLY());
            initVal.put(kHang.tag_MA_KHANG, kHang.getMA_KHANG());
            initVal.put(kHang.tag_SO_NHA, kHang.getSO_NHA());
            initVal.put(kHang.tag_DUONG_PHO, kHang.getDUONG_PHO());
            initVal.put(kHang.tag_DCHI_HDON, kHang.getDCHI_HDON());
            initVal.put(kHang.tag_TEN_KHANG, kHang.getTEN_KHANG());
            initVal.put(kHang.tag_DTHOAI, kHang.getDTHOAI());
            initVal.put(kHang.tag_EMAIL, kHang.getEMAIL());
            initVal.put(kHang.tag_PASSWORD, kHang.getPASSWORD());
            initVal.put(kHang.tag_LAST_ID_CHISO, kHang.getLAST_ID_CHISO());
            initVal.put(kHang.tag_LAST_ID_HDON, kHang.getLAST_ID_HDON());
            initVal.put(kHang.tag_DXEM_CHISO, kHang.getDXEM_CHISO());
            initVal.put(kHang.tag_DXEM_HDON, kHang.getDXEM_HDON());
            mDB.insert(kHang.tag_TABLE_KHANG, null, initVal);
            mDB.setTransactionSuccessful();
        }catch (Exception e){

        }finally {
            mDB.endTransaction();
        }

	}
    // HOA DON
    public void insert_hoadon(Obj_hoadon kHang) {
        mDB.beginTransaction();
        try {
            ContentValues initVal = new ContentValues();
            initVal.put(kHang.tag_ID_HDON, kHang.getID_HDON());
            initVal.put(kHang.tag_MA_KHANG, kHang.getMA_KHANG());
            initVal.put(kHang.tag_THANG, kHang.getTHANG());
            initVal.put(kHang.tag_NAM, kHang.getNAM());
            initVal.put(kHang.tag_KY, kHang.getKY());
            initVal.put(kHang.tag_SO_TIEN, kHang.getSO_TIEN());
            initVal.put(kHang.tag_TIEN_GTGT, kHang.getTIEN_GTGT());
            initVal.put(kHang.tag_TONG_TIEN, kHang.getTONG_TIEN());
            initVal.put(kHang.tag_DIEN_TTHU, kHang.getDIEN_TTHU());
            mDB.insert(kHang.tag_TABLE_HDON, null, initVal);
            mDB.setTransactionSuccessful();
        }catch (Exception e){

        }finally {
            mDB.endTransaction();
        }

    }
    public String delete_hoadon(Obj_hoadon oHD) {
        String kq ="start";
        mDB.beginTransaction();
        try {
            mDB.delete(oHD.tag_TABLE_HDON, oHD.tag_ID_HDON+"=?",
                    new String[] { String.valueOf(oHD.ID_HDON) });
            kq ="Xoa HD thanh cong !";
            mDB.setTransactionSuccessful();
        }catch (Exception e){
            kq = e.toString();
        }finally {
            mDB.endTransaction();
        }
        return  kq;
    }
	public void update_khachhang(Obj_khachhang kHang) {
        mDB.beginTransaction();
        try {
            ContentValues initVal = new ContentValues();
            initVal.put(kHang.tag_MA_DVIQLY, kHang.getMA_DVIQLY());
            initVal.put(kHang.tag_MA_KHANG, kHang.getMA_KHANG());
            initVal.put(kHang.tag_SO_NHA, kHang.getSO_NHA());
            initVal.put(kHang.tag_DUONG_PHO, kHang.getDUONG_PHO());
            initVal.put(kHang.tag_DCHI_HDON, kHang.getDCHI_HDON());
            initVal.put(kHang.tag_TEN_KHANG, kHang.getTEN_KHANG());
            initVal.put(kHang.tag_DTHOAI, kHang.getDTHOAI());
            initVal.put(kHang.tag_EMAIL, kHang.getEMAIL());
            initVal.put(kHang.tag_PASSWORD, kHang.getPASSWORD());
            initVal.put(kHang.tag_LAST_ID_CHISO, kHang.getLAST_ID_CHISO());
            initVal.put(kHang.tag_LAST_ID_HDON, kHang.getLAST_ID_HDON());
            initVal.put(kHang.tag_DXEM_CHISO, kHang.getDXEM_CHISO());
            initVal.put(kHang.tag_DXEM_HDON, kHang.getDXEM_HDON());
            mDB.update(kHang.tag_TABLE_KHANG, initVal, null, null);

            mDB.setTransactionSuccessful();
        } catch (Exception e) {

        } finally {
            mDB.endTransaction();
        }
    }
    public String xoa_khachhang() {
        String kq ="start";
        mDB.beginTransaction();
        try {
            mDB.delete(Obj_khachhang.tag_TABLE_KHANG,null,null);
			mDB.delete(Obj_hoadon.tag_TABLE_HDON,null,null);
            kq ="Đăng xuất thành công !";
            mDB.setTransactionSuccessful();
        }catch (Exception e){
            kq = e.toString();
        }finally {
            mDB.endTransaction();
        }
        return  kq;
    }
	
// KHACH HANG
//	public static long Insert_tieuthu(Obj_hoadon kHang) {
//		ContentValues initVal = new ContentValues();
//		initVal.put(Utils.id_tieuthu, kHang.id_tieuthu);
//		initVal.put(kHang.tag_MA_KHANG, kHang.ma_khachhang);
//		initVal.put(Utils.ngay, kHang.ngay);
//		initVal.put(Utils.thang, kHang.thang);
//		initVal.put(Utils.nam, kHang.nam);
//		initVal.put(Utils.ky, kHang.ky);
//		initVal.put(Utils.san_luong, kHang.san_luong);
//		initVal.put(Utils.so_tien, kHang.so_tien);
//		initVal.put(Utils.tt_xem, kHang.tt_xem);
//		return mDB.insert(Utils.table_tieuthu, null, initVal);
//	}
	// KHACH HANG
		public static long Insert_lichcatdien(Obj_lichcatdien kHang) {
			ContentValues initVal = new ContentValues();
			initVal.put(Utils.id_lichcatdien, kHang.id_lichcatdien);
			initVal.put(kHang.tag_MA_TRAM, kHang.ma_tram);
			initVal.put(Utils.ngay, kHang.ngay);
			initVal.put(Utils.khu_vuc, kHang.khu_vuc);
			initVal.put(Utils.thoi_gian, kHang.thoi_gian);
			initVal.put(Utils.pham_vi, kHang.pham_vi);
			initVal.put(Utils.tt_xem, kHang.tt_xem);
			return mDB.insert(Utils.table_lichcatdien, null, initVal);
		}

		// PHAN HOI
//		public static long Insert_phanhoi(Obj_phanhoi kHang) {
//			ContentValues initVal = new ContentValues();
//			initVal.put(Utils.id_phanhoi, kHang.id_phanhoi);
//			initVal.put(Utils.ngay, kHang.ngay);
//			initVal.put(Utils.thang, kHang.thang);
//			initVal.put(Utils.nam, kHang.nam);
//			initVal.put(Utils.noidung_phanhoi, kHang.noidung_phanhoi);
//			initVal.put(Utils.loai_phanhoi, kHang.loai_phanhoi);
//			initVal.put(Utils.tt_phanhoi, kHang.tt_phanhoi);
//			initVal.put(kHang.tag_MA_KHANG, kHang.ma_khachhang);
//			return mDB.insert(Utils.table_phanhoi, null, initVal);
//		}

//	 public void xoa_data_chua_dong_bo() {
//		 mDB.delete(Utils.table_benhnhan, Utils.version+" =0",null);
//		 mDB.delete(Utils.table_mau, Utils.version+" =0",null);
//		 mDB.delete(Utils.table_nhom, Utils.version+" =0",null);
//		 mDB.delete(Utils.table_thuoc, Utils.version+" =0",null);
//	 }
//	 public void xoa_all_data() {
//		 mDB.delete(Utils.table_benhnhan,null,null);
//		 mDB.delete(Utils.table_mau,null,null);
//		 mDB.delete(Utils.table_nhom,null,null);
//		 mDB.delete(Utils.table_thuoc,null,null);
//	 }
//	 public void xoa_thuoc_chua_dong_bo() {
//		 mDB.delete(Utils.table_thuoc, Utils.version+" =0",null);
//	 }
//	 public void xoa_nhom_chua_dong_bo() {
//		 mDB.delete(Utils.table_nhom, Utils.version+" =0",null);
//	 }
//	 public void xoa_mau_chua_dong_bo() {
//		 mDB.delete(Utils.table_mau, Utils.version+" =0",null);
//	 }
//	 public void xoa_het_tieuthu() {
//		 mDB.delete(Utils.table_tieuthu,null,null);
//	 }
	 public void xoa_het_lichcatdien() {
		 mDB.delete(Utils.table_lichcatdien,null,null);
	 }


//	public boolean delete_VTU_BY_MA_YCAU_KNAI(String MA_YCAU_KNAI,
//			int MA_LOAI_TTOAN) {
//		if (MA_LOAI_TTOAN < 0)
//			return mDB.delete(Table.TABLE_HSO_VATTU_CTIET, "MA_YCAU_KNAI =?",
//					new String[] { MA_YCAU_KNAI }) > 0;
//		else
//			return mDB.delete(Table.TABLE_HSO_VATTU_CTIET,
//					"MA_YCAU_KNAI =? and MA_LOAI_TTOAN = ?", new String[] {
//							MA_YCAU_KNAI, String.valueOf(MA_LOAI_TTOAN) }) > 0;
//	}
//
//
//	public boolean delete_NHAP_LIEU(Obj_nhap_lieu NL) {
//		return mDB.delete(Table.TABLE_NHAP_LIEU, "STT=?",
//				new String[] { String.valueOf(NL.STT) }) > 0;
//	}

	/*
	 * list
	 */
	
	// LICH CAT DIEN
	public List<Obj_lichcatdien> get_list_lichcatdien() {
		Cursor c = null;
		List<Obj_lichcatdien> list_lichcatdien = null;
		mDB.beginTransaction();
		try {
			c = mDB.rawQuery("SELECT * FROM " + Utils.table_lichcatdien+" ORDER BY "+Utils.ngay+" DESC", null);
			c.moveToFirst();
			list_lichcatdien = new ArrayList<Obj_lichcatdien>();
			while (!c.isAfterLast()) {
				Obj_lichcatdien lCD = new Obj_lichcatdien();
				lCD.set_obj(c);
				list_lichcatdien.add(lCD);
				c.moveToNext();
			}
			c.close();
			mDB.setTransactionSuccessful();
		} catch (Exception e) {

		} finally {
			mDB.endTransaction();
		}
		return list_lichcatdien;
	}
	
// LICH PHAN HOI
	public List<Obj_phanhoi> get_list_phanhoi() {
		Cursor c = null;
		List<Obj_phanhoi> list_phanhoi = null;
		mDB.beginTransaction();
		try {
			c = mDB.rawQuery("SELECT * FROM " + Utils.table_phanhoi, null);
			c.moveToFirst();
			list_phanhoi = new ArrayList<Obj_phanhoi>();
			while (!c.isAfterLast()) {
				Obj_phanhoi pH = new Obj_phanhoi();
				pH.set_obj(c);
				list_phanhoi.add(pH);
				c.moveToNext();
			}
			c.close();
			mDB.setTransactionSuccessful();
		} catch (Exception e) {

		} finally {
			mDB.endTransaction();
		}
		return list_phanhoi;
	}
// TIEU THU
	public List<Obj_hoadon> get_list_tieuthu() {
		Cursor c = null;
		List<Obj_hoadon> list_tT = null;
		mDB.beginTransaction();
		try {
			c = mDB.rawQuery("SELECT * FROM " + Obj_hoadon.tag_TABLE_HDON, null);
			c.moveToFirst();
			list_tT = new ArrayList<Obj_hoadon>();
			while (!c.isAfterLast()) {
				Obj_hoadon tT = new Obj_hoadon();
				tT.set_obj(c);
				list_tT.add(tT);
				c.moveToNext();
			}
			c.close();
			mDB.setTransactionSuccessful();
		} catch (Exception e) {

		} finally {
			mDB.endTransaction();
		}
		return list_tT;
	}
	
	
//	// nhap lieu
//	public String[] get_list_nhap_lieu(int loai_nhap_lieu) {
//		Cursor c = null;
//		c = mDB.rawQuery("SELECT * FROM " + Table.TABLE_NHAP_LIEU + " where "
//				+ Utils.loai_nhap_lieu + " = " + loai_nhap_lieu
//				+ " ORDER BY STT DESC", null);
//		c.moveToFirst();
//		List<String> arr_nhap_lieu = new ArrayList<String>();
//		while (!c.isAfterLast()) {
//			String values = ThtDatabase.get_cursor_string(c, Utils.nhap_lieu);
//			arr_nhap_lieu.add(values);
//			c.moveToNext();
//		}
//		c.close();
//		return arr_nhap_lieu.toArray(new String[arr_nhap_lieu.size()]);
//	}
//
//	public List<Obj_nhap_lieu> get_list_NL(int loai_nhap_lieu) {
//		Cursor c = null;
//		c = mDB.rawQuery("SELECT * FROM " + Table.TABLE_NHAP_LIEU + " where "
//				+ Utils.loai_nhap_lieu + " = " + loai_nhap_lieu
//				+ " ORDER BY STT DESC", null);
//		c.moveToFirst();
//		List<Obj_nhap_lieu> arr_nhaplieu = new ArrayList<Obj_nhap_lieu>();
//		while (!c.isAfterLast()) {
//			Obj_nhap_lieu nhaplieu = new Obj_nhap_lieu();
//			nhaplieu.set_obj(c);
//			arr_nhaplieu.add(nhaplieu);
//			c.moveToNext();
//		}
//		c.close();
//		return arr_nhaplieu;
//	}

	/*
	 * object
	 */
	public Obj_khachhang get_HSO(String mAKH) {
		Cursor c = null;
		c = mDB.rawQuery("SELECT * FROM " + Obj_khachhang.tag_TABLE_KHANG + " where "
				+ Obj_khachhang.tag_MA_KHANG + " ='" + mAKH+"'", null);
		c.moveToFirst();
		Obj_khachhang kH = new Obj_khachhang();
		kH.set_obj(c);
		c.close();
		return kH;
	}
	public Obj_khachhang get_khachhang() {
		Obj_khachhang kH = null;
		mDB.beginTransaction();
		try {
		if (get_soluong_khachhang()>0){
			try {
				Cursor c = null;
				c = mDB.rawQuery("SELECT * FROM " + Obj_khachhang.tag_TABLE_KHANG +" LIMIT 1", null);
				c.moveToFirst();
				kH = new Obj_khachhang();
				kH.set_obj(c);
				c.close();
				mDB.setTransactionSuccessful();
			} catch (Exception e) {
				
			}
		}
		} catch (Exception e) {

		} finally {
			mDB.endTransaction();
		}
		return kH;
	}
//	public Long get_max_id_benhnhan() {
//		Long max_id =(long)123456789;
//		try {
//			max_id = Function.Get_imei1(mContext);
//		} catch (Exception e) {
//			
//		}
//		try {
//			if (get_SL_benhnhan()>0){
//				try {
//					Cursor c = mDB
//							.rawQuery(
//									"SELECT * FROM "+Utils.table_benhnhan+" WHERE "+Utils.id_benhnhan_phone
//									+"=(SELECT max("+Utils.id_benhnhan_phone+") FROM "+Utils.table_benhnhan+")",
//									null);
//					c.moveToFirst();
//					max_id = c.getLong(c.getColumnIndex(Utils.id_benhnhan_phone));
//				} catch (Exception e) {
//					
//				}
//			}
//		} catch (Exception e) {
//			
//		}
//		
//		return max_id;
//	}
//	public Long get_max_id_thuoc() {
//		Long max_id =(long)123456789;
//		try {
//			max_id = Function.Get_imei1(mContext);
//		} catch (Exception e) {
//			
//		}
//		try {
//			if (get_SL_thuoc()>0){
//				try {
//					Cursor c = mDB
//							.rawQuery(
//									"SELECT * FROM "+Utils.table_thuoc+" WHERE "+Utils.id_thuoc_phone
//									+"=(SELECT max("+Utils.id_thuoc_phone+") FROM "+Utils.table_thuoc+")",
//									null);
//					c.moveToFirst();
//					max_id = c.getLong(c.getColumnIndex(Utils.id_thuoc_phone));
//				} catch (Exception e) {
//					
//				}
//			}
//		} catch (Exception e) {
//			
//		}
//		return max_id;
//	}
//	public Long get_max_id_mau() {
//		Long max_id =(long)123456789;
//		try {
//			max_id = Function.Get_imei1(mContext);
//		} catch (Exception e) {
//			
//		}
//		try {
//			if (get_SL_mau()>0){
//				try {
//					Cursor c = mDB
//							.rawQuery(
//									"SELECT * FROM "+Utils.table_mau+" WHERE "+Utils.id_mau_phone
//									+"=(SELECT max("+Utils.id_mau_phone+") FROM "+Utils.table_mau+")",
//									null);
//					c.moveToFirst();
//					max_id = c.getLong(c.getColumnIndex(Utils.id_mau_phone));
//				} catch (Exception e) {
//					
//				}
//			}
//		} catch (Exception e) {
//			
//		}
//		
//		return max_id;
//	}
//	public Long get_max_id_nhom() {
//		Long max_id =(long)123456789;
//		try {
//			max_id = Function.Get_imei1(mContext);
//		} catch (Exception e) {
//			
//		}
//		try {
//			if (get_SL_nhom()>0){
//				try {
//					Cursor c = mDB
//							.rawQuery(
//									"SELECT * FROM "+Utils.table_nhom+" WHERE "+Utils.id_nhom_phone
//									+"=(SELECT max("+Utils.id_nhom_phone+") FROM "+Utils.table_nhom+")",
//									null);
//					c.moveToFirst();
//					max_id = c.getLong(c.getColumnIndex(Utils.id_nhom_phone));
//				} catch (Exception e) {
//					
//				}
//			}
//		} catch (Exception e) {
//			
//		}
//		
//		return max_id;
//	}
//	// max version
//	public int get_max_version_nhom() {
//		int max_version =0;
//		try {
//			if (get_SL_nhom()>0){
//				try {
//					Cursor c = mDB
//							.rawQuery(
//									"SELECT * FROM "+Utils.table_nhom+" WHERE "+Utils.version
//									+"=(SELECT max("+Utils.version+") FROM "+Utils.table_nhom+")",
//									null);
//					c.moveToFirst();
//					max_version = c.getInt(c.getColumnIndex(Utils.version));
//				} catch (Exception e) {
//					
//				}
//			}
//		} catch (Exception e) {
//			
//		}
//		
//		return max_version;
//	}
//	public int get_max_version_mau() {
//		int max_version =0;
//		try {
//			if (get_SL_mau()>0){
//				try {
//					Cursor c = mDB
//							.rawQuery(
//									"SELECT * FROM "+Utils.table_mau+" WHERE "+Utils.version
//									+"=(SELECT max("+Utils.version+") FROM "+Utils.table_mau+")",
//									null);
//					c.moveToFirst();
//					max_version = c.getInt(c.getColumnIndex(Utils.version));
//				} catch (Exception e) {
//					
//				}
//			}
//		} catch (Exception e) {
//			
//		}
//		
//		return max_version;
//	}
//	public int get_max_version_thuoc() {
//		int max_version =0;
//		try {
//			if (get_SL_thuoc()>0){
//				try {
//					Cursor c = mDB
//							.rawQuery(
//									"SELECT * FROM "+Utils.table_thuoc+" WHERE "+Utils.version
//									+"=(SELECT max("+Utils.version+") FROM "+Utils.table_thuoc+")",
//									null);
//					c.moveToFirst();
//					max_version = c.getInt(c.getColumnIndex(Utils.version));
//				} catch (Exception e) {
//					
//				}
//			}
//		} catch (Exception e) {
//			
//		}
//		
//		return max_version;
//	}
//	public int get_max_version_benhnhan() {
//		int max_version =0;
//		try {
//			if (get_SL_benhnhan()>0){
//				try {
//					Cursor c = mDB
//							.rawQuery(
//									"SELECT * FROM "+Utils.table_benhnhan+" WHERE "+Utils.version
//									+"=(SELECT max("+Utils.version+") FROM "+Utils.table_benhnhan+")",
//									null);
//					c.moveToFirst();
//					max_version = c.getInt(c.getColumnIndex(Utils.version));
//				} catch (Exception e) {
//					
//				}
//			}
//		} catch (Exception e) {
//			
//		}
//		
//		return max_version;
//	}
//	
	/*
	 * so luong
	 */
	public int get_soluong_khachhang() {
		int SL =0;
		mDB.beginTransaction();
		try {
			SL= ThtDatabase.get_so_luong(mDB, Obj_khachhang.tag_TABLE_KHANG);
			mDB.setTransactionSuccessful();
		} catch (Exception e) {

		} finally {
			mDB.endTransaction();
		}
		return SL;
	}
	public int get_soluong_lichcatdien() {
		return ThtDatabase.get_so_luong(mDB, Utils.table_lichcatdien);
	}
	public int get_soluong_phanhoi() {
		return ThtDatabase.get_so_luong(mDB, Utils.table_phanhoi);
	}
//	public int get_soluong_tieuthu() {
//		return ThtDatabase.get_so_luong(mDB, Utils.table_tieuthu);
//	}

//	public boolean chua_ton_tai_nhap_lieu(Obj_nhap_lieu NL) {
//		int count = 0;
//		Cursor c = null;
//		try {
//			c = mDB.rawQuery("SELECT COUNT (*) FROM " + Table.TABLE_NHAP_LIEU
//					+ " where " + Utils.nhap_lieu + "= '" + NL.nhap_lieu
//					+ "' and " + Utils.loai_nhap_lieu + " ="
//					+ NL.loai_nhap_lieu, null);
//			c.moveToFirst();
//			count = c.getInt(0);
//		} catch (Exception e) {
//		}
//		if (count > 0) {
//			return false;
//		} else {
//			return true;
//		}
//	}

}
