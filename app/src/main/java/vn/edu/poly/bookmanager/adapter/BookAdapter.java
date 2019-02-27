package vn.edu.poly.bookmanager.adapter;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import vn.edu.poly.bookmanager.Constant;
import vn.edu.poly.bookmanager.R;
import vn.edu.poly.bookmanager.dao.BookDAO;
import vn.edu.poly.bookmanager.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> implements Constant{

    private Context context;
    private List<Book> bookList;
    private BookDAO bookDAO;

    public BookAdapter(Context context, List<Book> bookList, BookDAO bookDAO) {
        this.context = context;
        this.bookList = bookList;
        this.bookDAO = bookDAO;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, final int position) {
        final Book book = bookList.get(position);
        holder.imganhbook.setImageResource(R.drawable.sach);
        holder.tvbook.setText(book.getTenSach());
        holder.tvbookprice.setText(book.getGiaBan()+"");

        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               bookDAO.deleteBook(bookList.get(position).getMaSach());
               bookList.remove(position);
               bookDAO.updateBook(book);
               notifyDataSetChanged();
            }
        });

        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setTitle(bookList.get(position).getMaSach());
                dialog.setContentView(R.layout.dialog_edit_book);

                final Spinner spmatheloaisach;
                final EditText edttensach;
                final EditText edttacgiasach;
                final EditText edtnhaxuatbansach;
                final EditText edtgiabansach;
                final EditText edtsoluongsach;
                final Button btneditsach;
                final Button btncancelsach;

                spmatheloaisach = dialog.findViewById(R.id.spmatheloaisach);
                edttensach = dialog.findViewById(R.id.edttensach);
                edttacgiasach = dialog.findViewById(R.id.edttacgiasach);
                edtnhaxuatbansach = dialog.findViewById(R.id.edtnhaxuatbansach);
                edtgiabansach = dialog.findViewById(R.id.edtgiabansach);
                edtsoluongsach = dialog.findViewById(R.id.edtsoluongsach);
                btneditsach = dialog.findViewById(R.id.btneditsach);
                btncancelsach = dialog.findViewById(R.id.btncancelsach);


                btneditsach.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Book book1 = new Book();
                        book1.setMaTheLoai(bookList.get(position).getMaTheLoai());
                        book1.setTenSach(bookList.get(position).getTenSach());
                        book1.setTacGia(bookList.get(position).getTacGia());
                        book1.setNXB(bookList.get(position).getNXB());
                        book1.setGiaBan(bookList.get(position).getGiaBan());
                        book1.setSoLuong(bookList.get(position).getSoLuong());

                        bookDAO.updateBook(book1);

                        // cap nhat thay doi len giao dien
                        bookList.get(position).setTenSach(edttensach.getText().toString().trim());
                        bookList.get(position).setTacGia(edttacgiasach.getText().toString().trim());
                        bookList.get(position).setNXB(edtnhaxuatbansach.getText().toString().trim());
                        bookList.get(position).setGiaBan(Long.parseLong(edtgiabansach.getText().toString().trim()));
                        bookList.get(position).setSoLuong(Integer.parseInt(edtsoluongsach.getText().toString().trim()));

                        notifyItemChanged(position);
                        notifyDataSetChanged();

                        Toast.makeText(context,context.getString(R.string.notify_save_successful) , Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                btncancelsach.setOnClickListener(new View.OnClickListener() {
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
        if (bookList == null){
            return 0;
        }
        return bookList.size();
    }

    public class BookHolder extends RecyclerView.ViewHolder {

        public final ImageView imganhbook;
        public final TextView tvbook;
        public final TextView tvbookprice;
        public final ImageView imgedit;
        public final ImageView imgdelete;

        public BookHolder(View itemView) {
            super(itemView);

            imganhbook =  itemView.findViewById(R.id.imganhbook);
            tvbook =  itemView.findViewById(R.id.tvbook);
            tvbookprice =  itemView.findViewById(R.id.tvbookprice);
            imgedit = itemView.findViewById(R.id.imgedit);
            imgdelete = itemView.findViewById(R.id.imgdelete);

        }
    }
}
