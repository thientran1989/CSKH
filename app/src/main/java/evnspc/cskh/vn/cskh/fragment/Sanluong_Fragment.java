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
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pnikosis.materialishprogress.ProgressWheel;
import com.thtsoftlib.function.ThtShow;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import evnspc.cskh.vn.cskh.R;
import evnspc.cskh.vn.cskh.activity.MainActivity;
import evnspc.cskh.vn.cskh.adapter.Adapter_SANLUONG;
import evnspc.cskh.vn.cskh.broadcast.Conect_server_sign_async;
import evnspc.cskh.vn.cskh.database.DBAdapter;
import evnspc.cskh.vn.cskh.object.CallbackResult;
import evnspc.cskh.vn.cskh.object.Obj_khachhang;
import evnspc.cskh.vn.cskh.object.Obj_sanluong;
import evnspc.cskh.vn.cskh.object.ObjectClient;
import evnspc.cskh.vn.cskh.utils.CONFIG_LINK;
import evnspc.cskh.vn.cskh.utils.DB_COMMAND;
import evnspc.cskh.vn.cskh.utils.Function;
import evnspc.cskh.vn.cskh.utils.M_READ_JSON;


public class Sanluong_Fragment extends Fragment {

    private boolean mSearchCheck;
    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";
    Adapter_SANLUONG mAdapter=null;
    RecyclerView recList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mReAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Obj_sanluong> list_baihat =null;
    List<Obj_sanluong> list_baihat_search =null;

    private ProgressDialog pDialog;
    CountDownTimer mcountdown;
    final int time_connnect = 180000;
    String mURL = "http://10.179.0.22:8080/suco/getds";
    CallbackResult mCB=null;
    ProgressWheel prov;
    ObjectClient mOC =null;
    Obj_khachhang oKH =null;
    DBAdapter mdb;

	public static Sanluong_Fragment newInstance(String text){
		Sanluong_Fragment mFragment = new Sanluong_Fragment();
//		Bundle mBundle = new Bundle();
//		mBundle.putString(TEXT_FRAGMENT, text);
//		mFragment.setArguments(mBundle);
		return mFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.fragment_sanluong, container, false);
        mdb = new DBAdapter(getActivity());
        mdb.open();
        try{
            prov = (ProgressWheel) rootView.findViewById(R.id.progress_wheel);
            recList = (RecyclerView) rootView.findViewById(R.id.cardList);
            recList.setHasFixedSize(true);
            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recList.setLayoutManager(llm);
        }catch(Exception e){
            Toast.makeText(getActivity().getApplicationContext(), "loi giao dien :\n"+e.toString(), Toast.LENGTH_LONG).show();
        }
//        TextView mTxtTitle = (TextView) rootView.findViewById(R.id.txtTitle);
//        mTxtTitle.setText(getArguments().getString(TEXT_FRAGMENT));
//        if(list_baihat==null){
//            if(FlashScreenAc.my_list!=null){
//                try {
//                    list_baihat = new ArrayList<Obj_bai_hat>(FlashScreenAc.my_list);
//                } catch(Exception e){
//                    Toast.makeText(getActivity().getApplicationContext(), "loi "+e.toString(),Toast.LENGTH_LONG).show();
//                }
//            }
//        }

		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));
        try{
            oKH = (Obj_khachhang)getActivity().getIntent().getSerializableExtra("KH");
        }catch(Exception e){
            ThtShow.show_toast(getActivity(), "Lỗi " + e.toString());
        }
        try {
            mOC = new ObjectClient();
            mOC.setCommand(DB_COMMAND.LENH_GETSANLUONG);
            mOC.setoKH(oKH);
            mURL = CONFIG_LINK.mURL_server;
            new Conect_server_sign_async(getActivity(),mURL,mOC,prov,mdb,recList,mAdapter).execute();
        }catch(Exception e){
            Toast.makeText(getActivity().getApplicationContext(), "loi load :\n"+e.toString(), Toast.LENGTH_LONG).show();
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

}
