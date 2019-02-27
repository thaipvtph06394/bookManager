package vn.edu.poly.bookmanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.poly.bookmanager.R;
import vn.edu.poly.bookmanager.dao.BillDAO;
import vn.edu.poly.bookmanager.model.Bill;
import vn.edu.poly.bookmanager.ui.BillActivity;
import vn.edu.poly.bookmanager.ui.HoadonchitietActivity;
import vn.edu.poly.bookmanager.ui.MainActivity;

import java.text.SimpleDateFormat;
import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.HoadonHolder> {

    private Context context;
    private List<Bill> billList;
    private BillDAO billDAO;

    public BillAdapter(Context context, List<Bill> billList, BillDAO billDAO) {
        this.context = context;
        this.billList = billList;
        this.billDAO = billDAO;
    }

    @NonNull
    @Override
    public HoadonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bill, parent, false);
        return new HoadonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoadonHolder holder, final int position) {
        final Bill bill = billList.get(position);
        holder.imganhhoadon.setImageResource(R.drawable.hoadon);
        holder.tvmahoadon.setText(bill.getIdhoadon());
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        holder.tvngaymua.setText(dateFormat.format(bill.getDatehoadon()));

        holder.imgdeletebill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billDAO.deleteBill(billList.get(position).getIdhoadon());
                billList.remove(position);
                billDAO.updateBill(bill);
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        if (billList == null){
            return 0;
        }
        return billList.size();
    }

    public class HoadonHolder extends RecyclerView.ViewHolder {

        public final ImageView imganhhoadon;
        public final TextView tvmahoadon;
        public final TextView tvngaymua;
        private ImageView imgdeletebill;


        public HoadonHolder(View itemView) {
            super(itemView);

            imganhhoadon =  itemView.findViewById(R.id.imganhhoadon);
            tvmahoadon =  itemView.findViewById(R.id.tvmahoadon);
            tvngaymua =  itemView.findViewById(R.id.tvngaymua);
            imgdeletebill = itemView.findViewById(R.id.imgdeletebill);

        }
    }
}
