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
package evnspc.cskh.vn.cskh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.thtsoftlib.function.ThtShow;

import br.liveo.Model.HelpLiveo;
import br.liveo.interfaces.OnItemClickListener;
import br.liveo.interfaces.OnPrepareOptionsMenuLiveo;
import br.liveo.navigationliveo.NavigationLiveo;
import evnspc.cskh.vn.cskh.Ac_dangnhap;
import evnspc.cskh.vn.cskh.Ac_thongtin;
import evnspc.cskh.vn.cskh.R;
import evnspc.cskh.vn.cskh.database.DBAdapter;
import evnspc.cskh.vn.cskh.fragment.Hoadon_Fragment;
import evnspc.cskh.vn.cskh.fragment.Meo_Fragment;
import evnspc.cskh.vn.cskh.fragment.Sanluong_Fragment;
import evnspc.cskh.vn.cskh.fragment.Giaodien_Fragment;
import evnspc.cskh.vn.cskh.object.Obj_khachhang;
import evnspc.cskh.vn.cskh.utils.DB_COMMAND;
import evnspc.cskh.vn.cskh.utils.Utils;
import tht.library.crouton.Style;

public class MainActivity extends NavigationLiveo implements OnItemClickListener {

    private HelpLiveo mHelpLiveo;
    public static Obj_khachhang oKH =null;
    String lenh ="";

    @Override
    public void onInt(Bundle savedInstanceState) {
        try{
            oKH = (Obj_khachhang)getIntent().getSerializableExtra("KH");
            lenh = getIntent().getStringExtra("lenh");
        }catch(Exception e){
            ThtShow.show_toast(this, "Lỗi " + e.toString());
        }

        // User Information
        this.userName.setText("Thien Tran");
        this.userEmail.setText("thientransale@gmail.com");
        this.userPhoto.setImageResource(R.drawable.ic_rudsonlive);
        this.userBackground.setImageResource(R.drawable.ic_user_background_first);

        // Creating items navigation
        mHelpLiveo = new HelpLiveo();
        mHelpLiveo.add(getString(R.string.hoa_don), R.mipmap.ic_inbox_black_24dp, 7);
        mHelpLiveo.addSubHeader(getString(R.string.tuychon_khac)); //Item subHeader
        mHelpLiveo.add(getString(R.string.phan_hoi), R.mipmap.ic_star_black_24dp);
        mHelpLiveo.add(getString(R.string.bao_hong), R.mipmap.ic_send_black_24dp);
        mHelpLiveo.add(getString(R.string.nhap_chi_so), R.mipmap.ic_drafts_black_24dp);
        mHelpLiveo.addSeparator(); // Item separator
        mHelpLiveo.add(getString(R.string.sanluong), R.mipmap.ic_delete_black_24dp);
        mHelpLiveo.add(getString(R.string.lich_cat_dien), R.mipmap.ic_report_black_24dp, 120);
        mHelpLiveo.add("meo tiet kiem", R.mipmap.ic_report_black_24dp, 120);
        int my_pos=2;
        if(lenh.equals(DB_COMMAND.LENH_CHECKNEW)){
            my_pos=0;
        }
        with(this).startingPosition(my_pos) //Starting position in the list
                .addAllHelpItem(mHelpLiveo.getHelp())

                //{optional} - List Customization "If you remove these methods and the list will take his white standard color"
                //.selectorCheck(R.drawable.selector_check) //Inform the background of the selected item color
                //.colorItemDefault(R.color.nliveo_blue_colorPrimary) //Inform the standard color name, icon and counter
                //.colorItemSelected(R.color.nliveo_purple_colorPrimary) //State the name of the color, icon and meter when it is selected
                //.backgroundList(R.color.nliveo_black_light) //Inform the list of background color
                //.colorLineSeparator(R.color.nliveo_transparent) //Inform the color of the subheader line

                //{optional} - SubHeader Customization
                .colorItemSelected(R.color.nliveo_blue_colorPrimary)
                .colorNameSubHeader(R.color.nliveo_blue_colorPrimary)
                //.colorLineSeparator(R.color.nliveo_blue_colorPrimary)

                .footerItem(R.string.settings, R.mipmap.ic_settings_black_24dp)

                //{optional} - Footer Customization
                .footerNameColor(R.color.nliveo_blue_colorPrimary)
                .footerIconColor(R.color.nliveo_blue_colorPrimary)
                .footerBackground(R.color.nliveo_white)

                .setOnClickUser(onClickPhoto)
                .setOnPrepareOptionsMenu(onPrepare)
                .setOnClickFooter(onClickFooter)
                .build();

        int position = this.getCurrentPosition();
        this.setElevationToolBar(position != 2 ? 15 : 0);
    }

    @Override
    public void onItemClick(int position) {
        Fragment mFragment;
        FragmentManager mFragmentManager = getSupportFragmentManager();

        switch (position){
            case 0:
                mFragment = Hoadon_Fragment.newInstance(mHelpLiveo.get(position).getName());
                break;
            case 2:
                mFragment = Giaodien_Fragment.newInstance(mHelpLiveo.get(position).getName());
                break;
            case 6:
                mFragment = Sanluong_Fragment.newInstance(mHelpLiveo.get(position).getName());
                break;
            case 8:
                mFragment = Meo_Fragment.newInstance(mHelpLiveo.get(position).getName());
                break;

            default:
//                mFragment = MainFragment.newInstance(mHelpLiveo.get(position).getName());
//                mFragment = BaiHat_Fragment.newInstance(mHelpLiveo.get(position).getName());
//                break;
                Toast.makeText(getApplicationContext(), "onClick :D "+position, Toast.LENGTH_SHORT).show();
                mFragment = Giaodien_Fragment.newInstance(mHelpLiveo.get(position).getName());
                break;

        }

        if (mFragment != null){
            mFragmentManager.beginTransaction().replace(R.id.container, mFragment).commit();
        }

        setElevationToolBar(position != 2 ? 15 : 0);
    }

    private OnPrepareOptionsMenuLiveo onPrepare = new OnPrepareOptionsMenuLiveo() {
        @Override
        public void onPrepareOptionsMenu(Menu menu, int position, boolean visible) {
        }
    };

    private View.OnClickListener onClickPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Toast.makeText(getApplicationContext(), "onClickPhoto :D", Toast.LENGTH_SHORT).show();
            to_intent_thongtin();
            closeDrawer();

        }
    };

    private View.OnClickListener onClickFooter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
            closeDrawer();
        }
    };

    public void to_intent_thongtin(){
        Intent i = new Intent(MainActivity.this,Ac_thongtin.class);
        Bundle b = new Bundle();
        b.putString(Utils.PARAM_title, getString(R.string.label_thong_tin));
        i.putExtras(b);
        i.putExtra("KH",oKH);
        startActivity(i);
    }
}

