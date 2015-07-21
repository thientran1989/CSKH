/*
 * Copyright 2015 Rudson Lima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package evnspc.cskh.vn.cskh.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.thtsoftlib.function.ThtShow;
import com.thtsoftlib.function.Thtcovert;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import evnspc.cskh.vn.cskh.R;
import evnspc.cskh.vn.cskh.activity.MainActivity;
import evnspc.cskh.vn.cskh.adapter.Adapter_HDON;
import evnspc.cskh.vn.cskh.adapter.Adapter_SANLUONG;
import evnspc.cskh.vn.cskh.broadcast.Conect_server_sign_async;
import evnspc.cskh.vn.cskh.database.DBAdapter;
import evnspc.cskh.vn.cskh.object.CallbackResult;
import evnspc.cskh.vn.cskh.object.Obj_hdonctiet;
import evnspc.cskh.vn.cskh.object.Obj_hoadon;
import evnspc.cskh.vn.cskh.object.Obj_khachhang;
import evnspc.cskh.vn.cskh.object.Obj_sanluong;
import evnspc.cskh.vn.cskh.object.ObjectClient;
import evnspc.cskh.vn.cskh.utils.CONFIG_LINK;
import evnspc.cskh.vn.cskh.utils.DB_COMMAND;
import evnspc.cskh.vn.cskh.utils.Function;
import evnspc.cskh.vn.cskh.utils.M_READ_JSON;
import tht.library.crouton.Style;


public class Hoadon_Fragment extends Fragment {

    private boolean mSearchCheck;
    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";
    List<Obj_hoadon> list_TT;
    DBAdapter mdb;
    Obj_khachhang oKH =null;
    ProgressWheel prov;
    String my_url="";

    SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mReAdapter;
    Adapter_HDON mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    RecyclerView recList;

	public static Hoadon_Fragment newInstance(String text){
		Hoadon_Fragment mFragment = new Hoadon_Fragment();
//		Bundle mBundle = new Bundle();
//		mBundle.putString(TEXT_FRAGMENT, text);
//		mFragment.setArguments(mBundle);
		return mFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.activity_tieuthu, container, false);
        mdb = new DBAdapter(getActivity());
        mdb.open();
        try {
            prov = (ProgressWheel) rootView.findViewById(R.id.progress_wheel);
            TextView tv_title = (TextView)rootView.findViewById(R.id.tv_title_tieuthu);
//        	Bundle b = new Bundle();
//        	b = getIntent().getExtras();
//        	tv_title.setText(b.getString(Utils.PARAM_title));
        } catch (Exception e) {
            ThtShow.show_crouton_toast(getActivity(),
                    getString(R.string.loi_khoi_tao_giao_dien), Style.ALERT);
        }
        try{
            mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
            recList = (RecyclerView) rootView.findViewById(R.id.cardList);
            recList.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recList.setLayoutManager(llm);
        }catch(Exception e){
            Toast.makeText(getActivity().getApplicationContext(), "loi giao dien :\n"+e.toString(), Toast.LENGTH_LONG).show();
        }
        try{
            oKH = (Obj_khachhang)getActivity().getIntent().getSerializableExtra("KH");
        }catch(Exception e){
            ThtShow.show_toast(getActivity(), "Lỗi "+e.toString());
        }
        my_url = CONFIG_LINK.mURL_server;
        ObjectClient mOC = new ObjectClient();
        mOC.setCommand(DB_COMMAND.LENH_GETHDON);
        mOC.setoKH(oKH);
        if(c(getActivity())){
            new Conect_server_sign_async(getActivity(),my_url,mOC,prov,mdb,recList,mAdapter).execute();
        }else{
            Toast.makeText(getActivity().getApplicationContext(),"no network",Toast.LENGTH_LONG).show();

         List<Obj_hoadon> list = mdb.get_list_tieuthu();
            set_list(list);
        }
		return rootView;		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu, menu);
        
        //Select search item
        final MenuItem menuItem = menu.findItem(R.id.menu_search);
        menuItem.setVisible(true);

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint(this.getString(R.string.search));

        ((EditText) searchView.findViewById(R.id.search_src_text))
                .setHintTextColor(getResources().getColor(R.color.nliveo_white));
        searchView.setOnQueryTextListener(onQuerySearchView);

		menu.findItem(R.id.menu_add).setVisible(true);

		mSearchCheck = false;	
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {

		case R.id.menu_add:
//            Toast.makeText(getActivity(), R.string.add, Toast.LENGTH_SHORT).show();
			break;

		case R.id.menu_search:
			mSearchCheck = true;
//            Toast.makeText(getActivity(), R.string.search, Toast.LENGTH_SHORT).show();
			break;
		}
		return true;
	}	

   private SearchView.OnQueryTextListener onQuerySearchView = new SearchView.OnQueryTextListener() {
       @Override
       public boolean onQueryTextSubmit(String s) {
           return false;
       }

       @Override
       public boolean onQueryTextChange(String s) {
           if (mSearchCheck){
               // implement your search here
//               mAdapter.get_search(s);
           }
           return false;
       }
   };

    public void set_list(List<Obj_hoadon> list){
        mAdapter = new Adapter_HDON(getActivity(),list);
        recList.setAdapter(mAdapter);
    }
    public void get_chitiet(Obj_hoadon oHD){
        ObjectClient mOC = new ObjectClient();
        mOC.setCommand(DB_COMMAND.LENH_GETHDONCTIET);
        mOC.setoKH(oKH);
        mOC.setoHD(oHD);
//        new Conect_server_sign_async(mOC).execute();
    }
    public void show_chitiet(String tilte,String thongtin){
        try {
            new MaterialDialog.Builder(getActivity())
                    .title(tilte)
                    .content(thongtin)
                    .positiveText(R.string.dong)
//                .negativeText(R.string.sua)
                    .show();
        }catch(Exception e){

        }

    }
    private boolean c(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        //
//		ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo.State wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
//        NetworkInfo.State mobileInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
//		if (wifiInfo == NetworkInfo.State.CONNECTED || mobileInfo == NetworkInfo.State.CONNECTED) {
//            ((Activity) mContext).startDownloadService();
//        } else if (wifiInfo == NetworkInfo.State.DISCONNECTED || mobileInfo == NetworkInfo.State.DISCONNECTED) {
//            ((Activity) mContext).stopDownloadService();
//        }
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
    public void load_data_ok(CallbackResult mCB){
        if(mCB!=null) {
            if (mCB.getResultString().equals("OK")) {
                List<Obj_hoadon> my_HD = new ArrayList<Obj_hoadon>();
                try {
                    my_HD = mCB.getList_HD();
                    if (my_HD != null) {
                        if (mdb != null) {
                            for (Obj_hoadon OHD : my_HD) {
                                mdb.insert_hoadon(OHD);
                            }
                        } else {
                            Toast.makeText(getActivity().getApplicationContext(), "mdb null", Toast.LENGTH_LONG).show();
                        }
                        set_list(my_HD);
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }}

}
