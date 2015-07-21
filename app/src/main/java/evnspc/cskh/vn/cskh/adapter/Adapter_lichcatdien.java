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
import evnspc.cskh.vn.cskh.object.Obj_lichcatdien;


public class Adapter_lichcatdien extends BaseAdapter{

	private Activity activity;
	List<Obj_lichcatdien> list_lichcatdien = new ArrayList<Obj_lichcatdien>();

	public Adapter_lichcatdien(List<Obj_lichcatdien> list_lichcatdien, Activity ctx) {
		super();
		this.activity = ctx;
		this.list_lichcatdien = list_lichcatdien;
	}

	public int getCount() {
		return  list_lichcatdien.size();
	}

	public Obj_lichcatdien getItem(int pos) {
		return list_lichcatdien.get(pos);
	}

	public long getItemId(int pos) {
		return 0;
	}

	public static class ViewHolder {
		public TextView tv_ngay;
		public TextView tv_khuvuc;
		public TextView tv_thoigian;
		public TextView tv_phamvi;
		public TextView tv_pos;

	}

	@SuppressLint("InflateParams") public View getView(int pos, View ConvertView, ViewGroup parent) {
		ViewHolder View;
		LayoutInflater inflater = activity.getLayoutInflater();
		if (ConvertView == null) {
			View = new ViewHolder();
			ConvertView = inflater.inflate(R.layout.row_lichcatdien, null);
			View.tv_ngay = (TextView) ConvertView
					.findViewById(R.id.tv_ngayghi_row_lichcatdien);
			View.tv_khuvuc = (TextView) ConvertView
					.findViewById(R.id.tv_khuvuc_row_lichcatdien);
			View.tv_thoigian = (TextView) ConvertView
					.findViewById(R.id.tv_thoigian_row_lichcatdien);
			View.tv_phamvi = (TextView) ConvertView
					.findViewById(R.id.tv_phamvi_row_lichcatdien);
			View.tv_pos = (TextView) ConvertView
					.findViewById(R.id.tv_pos_row_lichcatdien);
			ConvertView.setTag(View);
		} else {
			View = (ViewHolder) ConvertView.getTag();
		}
		// khai bao hoa don
		final Obj_lichcatdien TT = getItem(pos);
		View.tv_pos.setText(Thtcovert.int_to_String(pos+1));
		View.tv_ngay.setText("- "+activity.getString(R.string.label_ngay_row)+" "+TT.getNgay());
		View.tv_khuvuc.setText("- "+activity.getString(R.string.label_khuvuc_row)+" "+TT.getKhu_vuc());
		View.tv_thoigian.setText("- "+activity.getString(R.string.label_thoigian_row)+" "+TT.getThoi_gian());
		View.tv_phamvi.setText("- "+activity.getString(R.string.label_phamvi_row)+" "+TT.getPham_vi());

		return ConvertView;

	}

}

