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

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.thtsoftlib.function.ThtShow;

import evnspc.cskh.vn.cskh.Ac_dangnhap;
import evnspc.cskh.vn.cskh.R;
import evnspc.cskh.vn.cskh.database.DBAdapter;
import tht.library.crouton.Style;


public class Giaodien_Fragment extends Fragment {

    private boolean mSearchCheck;
    private static final String TEXT_FRAGMENT = "TEXT_FRAGMENT";
    DBAdapter mdb;

	public static Giaodien_Fragment newInstance(String text){
		Giaodien_Fragment mFragment = new Giaodien_Fragment();
//		Bundle mBundle = new Bundle();
//		mBundle.putString(TEXT_FRAGMENT, text);
//		mFragment.setArguments(mBundle);
		return mFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		View rootView = inflater.inflate(R.layout.activity_main_save, container, false);

        mdb = new DBAdapter(getActivity());

        View v_thoat = (LinearLayout)rootView.findViewById(R.id.v_thoat);
        v_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_taikhoan();
            }
        });
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT ));
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

           }
           return false;
       }
   };
    public void show_taikhoan() {
        new MaterialDialog.Builder(getActivity())
                .title(R.string.title_xacnhan)
                .content(R.string.thoattaikhoan)
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .callback(new MaterialDialog.ButtonCallback() {
                              @Override
                              public void onPositive(MaterialDialog dialog) {
                                  String sKQ="loi";
                                  try {
                                      mdb.open();
                                      sKQ= mdb.xoa_khachhang();
                                      Intent i = new Intent(getActivity(),Ac_dangnhap.class);
                                      startActivity(i);
                                      getActivity().finish();
                                  } catch (Exception e) {
//                                      ThtShow.show_crouton_toast(MainActivity.this,sKQ, Style.ALERT);
                                  }
                                  ThtShow.show_crouton_toast(getActivity(), sKQ, Style.ALERT);
                              }

                              @Override
                              public void onNegative(MaterialDialog dialog) {

                              }
                          }

                )
                .

                        show();
    }

}
