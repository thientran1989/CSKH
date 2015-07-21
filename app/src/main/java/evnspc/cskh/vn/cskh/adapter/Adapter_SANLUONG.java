package evnspc.cskh.vn.cskh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import evnspc.cskh.vn.cskh.R;
import evnspc.cskh.vn.cskh.object.Obj_sanluong;
import evnspc.cskh.vn.cskh.utils.My_Convert;


public class Adapter_SANLUONG extends
        RecyclerView.Adapter<Adapter_SANLUONG.ContactViewHolder> {
    private List<Obj_sanluong> list_all_sanluong;
    private List<Obj_sanluong> list_sanluong;
    Context mcon;

    public Adapter_SANLUONG(Context mcon, List<Obj_sanluong> list_all_baihat) {
        this.mcon=mcon;
        this.list_all_sanluong = list_all_baihat;
        list_sanluong = new ArrayList<Obj_sanluong>(this.list_all_sanluong);
    }
//    public void get_search(String key){
//        list_sanluong.clear();
//        if (key.length()>0){
//            for (Obj_sanluong oMBA : list_all_sanluong){
//                if (oMBA.getTEN_BH_TK().contains(key)
//                        || oMBA.getCHAR_START().contains(key)
//                        || oMBA.getLOI_RG().contains(key)){
//                    list_sanluong.add(oMBA);
//                }
//            }
//        }else{
//            list_sanluong = new ArrayList<Obj_sanluong>(this.list_all_sanluong);
//        }
//        notifyDataSetChanged();
//
//    }

    @Override
    public int getItemCount() {
        return list_sanluong.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        final Obj_sanluong ci = list_sanluong.get(i);

        contactViewHolder.tv_thoigian.setText("Kỳ " + ci.getKY()+" - "+ci.getTHANG()+"/"+ci.getNAM());
        contactViewHolder.tv_daucuoi.setText(My_Convert.timestamp2string(ci.getNGAY_DKY())+" den "+My_Convert.timestamp2string(ci.getNGAY_CKY()));
        contactViewHolder.tv_sanluong.setText(""+ci.getSAN_LUONG());


        contactViewHolder.setClickListener(new ContactViewHolder.ClickListener() {
            public void onClick(View v, int pos, boolean isLongClick) {
                if (isLongClick) {
//                    Toast.makeText(mcon, "longclick " + ci.getTEN_BH(), Toast.LENGTH_LONG).show();
                } else {
                    String TT = "thong tin ";
//                    TT=TT+ci.getTEN_BH()+"\n";
//                    TT=TT+"MSTS :"+ci.getMSTS()+"\n";
//                    TT=TT+"Thuộc kho :"+ci.getKho()+"\n";
//                    TT=TT+"Nhà sx :"+ci.getNha_sanxuat()+"\n";
//                    TT=TT+"Công suất :"+ci.getCong_suat()+"\n";
//                    TT=TT+"Nấc vận hành :"+ci.getNac_vanhanh()+"\n";
//                    showBasicLongContent(TT);
//                    to_loibaihat(ci);
                }
            }
        });
    }
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.cardview_sanluong, viewGroup, false);
        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        public TextView tv_thoigian;
        public TextView tv_sanluong;
        public TextView tv_daucuoi;

        private ClickListener clickListener;

        public ContactViewHolder(View v) {
            super(v);

            tv_thoigian = (TextView) v
                    .findViewById(R.id.tv_thoigian_cardview_sanluong);
            tv_sanluong = (TextView) v
                    .findViewById(R.id.tv_sanluong_cardview_sanluong);
            tv_daucuoi = (TextView) v
                    .findViewById(R.id.tv_daucuoi_cardview_sanluong);
            v.setOnClickListener(this);
        }
        /* Interface for handling clicks - both normal and long ones. */
        public interface ClickListener {
            public void onClick(View v, int position, boolean isLongClick);

        }

        /* Setter for listener. */
        public void setClickListener(ClickListener clickListener) {
            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View v) {
            // If not long clicked, pass last variable as false.
            clickListener.onClick(v, getPosition(), false);
        }
        @Override
        public boolean onLongClick(View v) {

            // If long clicked, passed last variable as true.
            clickListener.onClick(v, getPosition(), true);
            return true;
        }


    }
//    private void showBasicLongContent(String thongtin) {
//        new MaterialDialog.Builder(mcon)
//                .title(R.string.thongtin)
//                .content(thongtin)
//                    .positiveText(R.string.dong)
//                .negativeText(R.string.sua)
//                .show();
//    }
//    private void to_loibaihat(Obj_sanluong oBH) {
//        Intent i = new Intent(mcon, Activity_Loibaihat.class);
//        i.putExtra("BH",oBH);
//        mcon.startActivity(i);
//    }
}
