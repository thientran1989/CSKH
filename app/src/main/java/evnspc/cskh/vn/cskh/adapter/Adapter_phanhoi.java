package evnspc.cskh.vn.cskh.adapter;

import java.util.ArrayList;
import java.util.List;

import com.thtsoftlib.function.Thtcovert;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import evnspc.cskh.vn.cskh.R;
import evnspc.cskh.vn.cskh.object.Obj_phanhoi;


public class Adapter_phanhoi extends BaseAdapter{

	private Activity activity;
	List<Obj_phanhoi> list_tieuthu = new ArrayList<Obj_phanhoi>();

	public Adapter_phanhoi(List<Obj_phanhoi> list_tieuthu, Activity ctx) {
		super();
		this.activity = ctx;
		this.list_tieuthu = list_tieuthu;
	}

	public int getCount() {
		return  list_tieuthu.size();
	}

	public Obj_phanhoi getItem(int pos) {
		return list_tieuthu.get(pos);
	}

	public long getItemId(int pos) {
		return 0;
	}

	public static class ViewHolder {
		public TextView tv_thang;
		public TextView tv_ngayghi;
		public TextView tv_noidung;

	}

	@SuppressLint("InflateParams") public View getView(int pos, View ConvertView, ViewGroup parent) {
		ViewHolder View;
		LayoutInflater inflater = activity.getLayoutInflater();
		if (ConvertView == null) {
			View = new ViewHolder();
			ConvertView = inflater.inflate(R.layout.row_phanhoi, null);
			View.tv_thang = (TextView) ConvertView
					.findViewById(R.id.tv_thang_row_phanhoi);
			View.tv_ngayghi = (TextView) ConvertView
					.findViewById(R.id.tv_ngayghi_row_phanhoi);
			View.tv_noidung = (TextView) ConvertView
					.findViewById(R.id.tv_phanhoi_row_phanhoi);
			ConvertView.setTag(View);
		} else {
			View = (ViewHolder) ConvertView.getTag();
		}
		// khai bao hoa don
		final Obj_phanhoi TT = getItem(pos);
		View.tv_thang.setText(Thtcovert.int_to_String(TT.getThang()));
		View.tv_ngayghi.setText(Thtcovert.int_to_String(TT.getNgay())+"/"
								+Thtcovert.int_to_String(TT.getThang())+"/"
								+Thtcovert.int_to_String(TT.getNam()));
		View.tv_noidung.setText(TT.getNoidung_phanhoi());

		return ConvertView;

	}

}

