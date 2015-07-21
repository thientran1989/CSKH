package evnspc.cskh.vn.cskh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.thtsoftlib.function.Thtcovert;

import java.util.List;

import evnspc.cskh.vn.cskh.Ac_tieuthu;
import evnspc.cskh.vn.cskh.R;
import evnspc.cskh.vn.cskh.object.Obj_hoadon;


public class Adapter_HDON extends
        RecyclerView.Adapter<Adapter_HDON.ContactViewHolder> {

    private List<Obj_hoadon> contactList;
    Context mcon;

    public Adapter_HDON(Context mcon, List<Obj_hoadon> contactList) {
        this.mcon=mcon;
        this.contactList = contactList;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        final Obj_hoadon ci = contactList.get(i);

        contactViewHolder.tv_thang.setText(mcon.getString(R.string.ky)+" "+String.valueOf(ci.getKY())+" - "+
                                            mcon.getString(R.string.thang)+" "+String.valueOf(ci.getTHANG())
                                            +"/"+String.valueOf(ci.getNAM()));
        contactViewHolder.tv_sotien.setText( mcon.getString(R.string.sotien)+" "+Thtcovert.int_format_tien(ci.getSO_TIEN()));
        contactViewHolder.tv_gtgt.setText( mcon.getString(R.string.gtgt)+" "+Thtcovert.int_format_tien(ci.getTIEN_GTGT()));
        contactViewHolder.tv_tongtien.setText( mcon.getString(R.string.tongtien)+" "+ Thtcovert.int_format_tien(ci.getTONG_TIEN()));
        contactViewHolder.tv_sanluong.setText(mcon.getString(R.string.sanluong)+" "+String.valueOf(ci.getDIEN_TTHU()));

        contactViewHolder.setClickListener(new ContactViewHolder.ClickListener() {
            public void onClick(View v, int pos, boolean isLongClick) {
                if (isLongClick) {
//                    Toast.makeText(mcon,"longclick "+ci.getSo_may(),Toast.LENGTH_LONG).show();
                } else {
//                   mcon.get_chitiet(ci);
                }
            }
        });
    }
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.row_tieuthu, viewGroup, false);
        return new ContactViewHolder(itemView);
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        public TextView tv_thang;
        public TextView tv_sotien;
        public TextView tv_sanluong;
        public TextView tv_gtgt;
        public TextView tv_tongtien;

        private ClickListener clickListener;

        public ContactViewHolder(View v) {
            super(v);
            tv_thang = (TextView) v
                    .findViewById(R.id.tv_thang_row_tieuthu);
            tv_sotien = (TextView) v
                    .findViewById(R.id.tv_sotien_row_tieuthu);
            tv_gtgt = (TextView) v
                    .findViewById(R.id.tv_gtgt_row_tieuthu);
            tv_tongtien = (TextView) v
                    .findViewById(R.id.tv_tongtien_row_tieuthu);
            tv_sanluong = (TextView) v
                    .findViewById(R.id.tv_sanluong_row_tieuthu);
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
}
