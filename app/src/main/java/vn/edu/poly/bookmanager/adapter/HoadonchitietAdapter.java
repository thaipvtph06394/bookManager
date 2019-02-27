package vn.edu.poly.bookmanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import vn.edu.poly.bookmanager.R;
import vn.edu.poly.bookmanager.dao.HoadonchitietDAO;
import vn.edu.poly.bookmanager.model.Hoadonchitiet;

import java.util.List;

public class HoadonchitietAdapter extends RecyclerView.Adapter<HoadonchitietAdapter.HoadonchitietHolder> {

    Context context;
    List<Hoadonchitiet> hoadonchitietList;
    private HoadonchitietDAO hoadonchitietDAO;

    public HoadonchitietAdapter(Context context, List<Hoadonchitiet> hoadonchitietList, HoadonchitietDAO hoadonchitietDAO) {
        this.context = context;
        this.hoadonchitietList = hoadonchitietList;
        this.hoadonchitietDAO = hoadonchitietDAO;
    }

    @NonNull
    @Override
    public HoadonchitietHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hoadonchitiet, parent, false);
        return new  HoadonchitietHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoadonchitietHolder holder, final int position) {
        final Hoadonchitiet hoadonchitiet = hoadonchitietList.get(position);
        holder.imganhhoadonchitiet.setImageResource(R.drawable.hdct);
        holder.tvmahoadonchitiet.setText(hoadonchitiet.getMahoadonchitiet());
        holder.tvmahoadon.setText(hoadonchitiet.getMahoadon());
        holder.tvmasach.setText(hoadonchitiet.getMasach());
        holder.tvsoluong.setText(hoadonchitiet.getSoluong()+"");

        holder.imgdeletehoadonchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hoadonchitietDAO.deleteHoadonchitiet(hoadonchitietList.get(position).getMahoadonchitiet());
                hoadonchitietList.remove(position);
                hoadonchitietDAO.updateHoadonchitiet(hoadonchitiet);
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        if (hoadonchitietList == null){
            return 0;
        }
        return hoadonchitietList.size();
    }

    public class HoadonchitietHolder extends RecyclerView.ViewHolder {

        public final ImageView imganhhoadonchitiet;
        public final TextView tvmahoadonchitiet;
        public final TextView tvmahoadon;
        public final TextView tvsoluong;
        public final TextView tvmasach;
        public final ImageView imgdeletehoadonchitiet;

        public HoadonchitietHolder(View itemView) {
            super(itemView);

            imganhhoadonchitiet =  itemView.findViewById(R.id.imganhhoadonchitiet);
            tvmahoadonchitiet =  itemView.findViewById(R.id.tvmahoadonchitiet);
            tvmahoadon =  itemView.findViewById(R.id.tvmahoadon);
            tvsoluong =  itemView.findViewById(R.id.tvsoluong);
            tvmasach =  itemView.findViewById(R.id.tvmasach);
            imgdeletehoadonchitiet = itemView.findViewById(R.id.imgdeletehoadonchitiet);

        }
    }
}
