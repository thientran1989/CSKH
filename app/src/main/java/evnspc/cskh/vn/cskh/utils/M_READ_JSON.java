package evnspc.cskh.vn.cskh.utils;

import java.lang.reflect.Type;
import java.util.List;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import evnspc.cskh.vn.cskh.object.CallbackResult;
import evnspc.cskh.vn.cskh.object.Obj_khachhang;


public class M_READ_JSON {

	public static CallbackResult read_callback(JsonObject mJO) {
		CallbackResult mCB = null;
		try {
			Gson gson = new Gson();
			mCB = gson.fromJson(mJO.get(Utils.table_callback).getAsString(),CallbackResult.class);
		} catch (Exception e) {

		}
		return mCB;
	}

	// read list
	public static List<Obj_khachhang> read_list_canvas(JsonObject mJO) {
		List<Obj_khachhang> yourList = null;
		try {
			Type listType = new TypeToken<List<Obj_khachhang>>() {
			}.getType();
			yourList = new Gson().fromJson(mJO.get(Obj_khachhang.tag_TABLE_KHANG).getAsString(), listType);
		} catch (Exception e) {

		}
		return yourList;
	}

	public static boolean is_accepted(Context c, CallbackResult mCB) {
		boolean accepted = false;
		try {
			if (mCB != null) {
				if (mCB.getResultString().equals("OK")) {
					accepted = true;
				}
			}
		} catch (Exception e) {

		}

		return accepted;
	}

}
