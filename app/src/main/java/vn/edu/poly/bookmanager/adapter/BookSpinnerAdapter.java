package vn.edu.poly.bookmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import vn.edu.poly.bookmanager.R;
import vn.edu.poly.bookmanager.model.Book;

import java.util.List;

public class BookSpinnerAdapter extends BaseAdapter {



    private Context context;
    private List<Book> books;

    public BookSpinnerAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);

        return null;
    }
}
