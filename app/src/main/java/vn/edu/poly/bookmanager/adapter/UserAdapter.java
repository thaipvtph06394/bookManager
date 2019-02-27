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
import vn.edu.poly.bookmanager.dao.UserDAO;
import vn.edu.poly.bookmanager.model.User;
import vn.edu.poly.bookmanager.sqlite.DatabaseHelper;
import vn.edu.poly.bookmanager.ui.MainActivity;
import vn.edu.poly.bookmanager.ui.UserActivity;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private Context context;
    private List<User> userList;
    private UserDAO userDAO;

    public UserAdapter(Context context, List<User> userList, UserDAO userDAO) {
        this.context = context;
        this.userList = userList;
        this.userDAO = userDAO;
    }


    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nguoidung, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserHolder holder, final int position) {
        final User user = userList.get(position);
        holder.imganh.setImageResource(R.drawable.nguoidung);
        holder.tvusername.setText(user.getUsername());
        holder.tvphone.setText(user.getPhone());

        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDAO.deleteUser(userList.get(position).getUsername());
                userList.remove(position);
                userDAO.UpdateUser(user);
                notifyDataSetChanged();
            }
        });

        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context);
                dialog.setTitle(userList.get(position).getUsername());
                dialog.setContentView(R.layout.dialog_edit_user);

                final EditText edtpassword;
                final EditText edtname;
                final EditText edtphone;
                final Button btnedituser;
                final Button btncancel;

                edtpassword = dialog.findViewById(R.id.edtpassword);
                edtname = dialog.findViewById(R.id.edtname);
                edtphone = dialog.findViewById(R.id.edtphone);
                btnedituser = dialog.findViewById(R.id.btnedituser);
                btncancel = dialog.findViewById(R.id.btncancel);

                edtpassword.setText(userList.get(position).getPasswork());
                edtname.setText(userList.get(position).getName());
                edtphone.setText(userList.get(position).getPhone());

                btnedituser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        User user1 = new User();
                        user1.setPasswork(userList.get(position).getPasswork());
                        user1.setName(userList.get(position).getName());
                        user1.setPhone(userList.get(position).getPhone());

                        userDAO.UpdateUser(user1);

                        // cap nhat thay doi len giao dien
                        userList.get(position).setPasswork(edtpassword.getText().toString().trim());
                        userList.get(position).setName(edtname.getText().toString().trim());
                        userList.get(position).setPhone(edtphone.getText().toString().trim());

                        notifyItemChanged(position);

                        Toast.makeText(context,context.getString(R.string.notify_save_successful) , Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                });

                btncancel.setOnClickListener(new View.OnClickListener() {
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
        if (userList == null) {
            return 0;
        }
        return userList.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        public final ImageView imganh;
        public final TextView tvusername;
        public final TextView tvphone;
        public final ImageView imgedit;
        public final ImageView imgdelete;

        public UserHolder(View itemView) {
            super(itemView);

            imganh = itemView.findViewById(R.id.imganh);
            tvusername = itemView.findViewById(R.id.tvusername);
            tvphone = itemView.findViewById(R.id.tvphone);
            imgedit = itemView.findViewById(R.id.imgedit);
            imgdelete = itemView.findViewById(R.id.imgdelete);


        }

    }
}
