package evnspc.cskh.vn.cskh.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import evnspc.cskh.vn.cskh.object.Obj_phanhoi;
import evnspc.cskh.vn.cskh.object.ObjectClient;


public class Function {
	
	// send 2 server
	public static String alldata2server(ObjectClient mOC,
			List<Obj_phanhoi> mL_phanhoi) {
		String KQ = "[]";
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		JsonObject JO = new JsonObject();
		try {
			JO.addProperty(Utils.table_client,gson.toJson(mOC));
			if (mL_phanhoi!=null){
				JO.addProperty(Utils.table_phanhoi,gson.toJson(mL_phanhoi));
			}

			KQ = JO.toString();
		} catch (Exception e) {

		}
		return KQ;
	}
	
	public static String byte_to_String(DataInputStream dis) {
		String s = "";
		int size_json = 0;
		byte[] data = null;
		try {
			size_json = dis.readInt();
			data = new byte[size_json];
			dis.readFully(data);
			s = new String(data, "UTF-8");
		} catch (Exception e) {

		}
		return s;
	}

	public static void write_String_to_byte(DataOutputStream dos, String json) {
		byte[] data;
		try {
			data = json.getBytes("UTF-8");
			try {
				dos.writeInt(data.length);
				dos.write(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
