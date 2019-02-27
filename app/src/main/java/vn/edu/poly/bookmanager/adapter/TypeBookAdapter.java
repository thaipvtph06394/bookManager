package vn.edu.poly.bookmanager.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.poly.bookmanager.R;
import vn.edu.poly.bookmanager.dao.TypeBookDAO;
import vn.edu.poly.bookmanager.model.TypeBook;
import vn.edu.poly.bookmanager.model.User;

import java.util.List;

public class TypeBookAdapter extends RecyclerView.Adapter<TypeBookAdapter.TheloaiHolder> {

    private Context context;
    private List<TypeBook> typeBookList;
    private TypeBookDAO typeBookDAO;

    public TypeBookAdapter(Context context, List<TypeBook> typeBookList, TypeBookDAO typeBookDAO) {
        this.context = context;
        this.typeBookList = typeBookList;
        this.typeBookDAO = typeBookDAO;
    }

    @NonNull
    @Override
    public TheloaiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_theloai, parent, false);
        return new TheloaiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheloaiHolder holder, final int position) {
        final TypeBook typeBook = typeBookList.get(position);
        holder.imganhtheloai.setImageResource(R.drawable.theloai);
        holder.tvmatheloai.setText(typeBook.getId());
        holder.tvtentheloai.setText(typeBook.getName());

        holder.imgdeletetypebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                typeBookDAO.deleteTypeBook(typeBookList.get(position).getId());
                typeBookList.remove(position);
                typeBookDAO.updateTypeBook(typeBook);
                notifyDataSetChanged();
            }
        });

        holder.imgedittypebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context);
                dialog.setTitle(typeBookList.get(position).getId());
                dialog.setContentView(R.layout.dialog_edit_theloai);

                final EditText edttentheloai;
                final EditText edtmotatheloai;
                final EditText edtvitritheloai;
                final Button btnedittheloai;
                final Button btncanceltheloai;

                edttentheloai = dialog.findViewById(R.id.edttentheloai);
                edtmotatheloai = dialog.findViewById(R.id.edtmotatheloai);
                edtvitritheloai = dialog.findViewById(R.id.edtvitritheloai);
                btnedittheloai = dialog.findViewById(R.id.btnedittheloai);
                btncanceltheloai = dialog.findViewById(R.id.btncanceltheloai);

                edttentheloai.setText(typeBookList.get(position).getName());
                edtmotatheloai.setText(typeBookList.get(position).getDescription());
                edtvitritheloai.setText(typeBookList.get(position).getPosition()+"");

                btnedittheloai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        TypeBook typeBook1 = new TypeBook();
                        typeBook1.setName(typeBookList.get(position).getName());
                        typeBook1.setDescription(typeBookList.get(position).getDescription());
                        typeBook1.setPosition(typeBookList.get(position).getPosition());

                        typeBookDAO.updateTypeBook(typeBook1);

                        // cap nhat thay doi len giao dien
                        typeBookList.get(position).setName(edttentheloai.getText().toString().trim());
                        typeBookList.get(position).setDescription(edtmotatheloai.getText().toString().trim());
                        typeBookList.get(position).setPosition(Integer.parseInt(edtvitritheloai.getText().toString()));

                        notifyItemChanged(position);

                        Toast.makeText(context,context.getString(R.string.notify_save_successful) , Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                });

                btncanceltheloai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });



    }

    @Override
    public int getItemCount() {
        if (typeBookList == null){
            return 0;
        }
        return typeBookList.size();
    }

    public class TheloaiHolder extends RecyclerView.ViewHolder {

        public final ImageView imganhtheloai;
        public final TextView tvmatheloai;
        public final TextView tvtentheloai;
        public final ImageView imgedittypebook;
        public final ImageView imgdeletetypebook;

        public TheloaiHolder(View itemView) {
            super(itemView);

            imganhtheloai =  itemView.findViewById(R.id.imganhtheloai);
            tvmatheloai =  itemView.findViewById(R.id.tvmatheloai);
            tvtentheloai =  itemView.findViewById(R.id.tvtentheloai);
            imgedittypebook = itemView.findViewById(R.id.imgedittypebook);
            imgdeletetypebook = itemView.findViewById(R.id.imgdeletetypebook);

        }
    }
}
