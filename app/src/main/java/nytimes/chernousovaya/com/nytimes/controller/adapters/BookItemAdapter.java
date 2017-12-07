package nytimes.chernousovaya.com.nytimes.controller.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import nytimes.chernousovaya.com.nytimes.R;
import nytimes.chernousovaya.com.nytimes.model.Book;

/**
 *
 */

public class BookItemAdapter extends BaseAdapter {

    private static final String LOG = "BookItemAdapter";
    private List<Book> mListBooks;
    private final Context mContext;
    private final LayoutInflater mInflater;



    public BookItemAdapter(Context context, List<Book> data) {
        this.mContext = context;
        this.mListBooks = data;
        this.mInflater = LayoutInflater.from(context);
        //this.mInflater = (LayoutInflater) context
        //        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mListBooks.size();
    }

    @Override
    public Book getItem(int i) {
        return mListBooks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    Book getData(int position){
        return (Book)(getItem(position));
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Book book = (Book) getItem(i);
        ViewHolder holder;
        //View v;
        //если эта View никогда не использовалась
        if (view == null) {
            view = mInflater.inflate(R.layout.item_book, null);
          //  view = mInflater.inflate(R.layout.item_book, viewGroup, false);
            holder = new ViewHolder();
            holder.mTitleView = view.findViewById(R.id.title);
            holder.mAuthorView = view.findViewById(R.id.author);
            holder.mDescriptionView = view.findViewById(R.id.description);
            holder.mUrlView = view.findViewById(R.id.url);
            view.setTag(holder);
        } else {
           // v = view;
            holder = (ViewHolder) view.getTag();
        }
        //ViewHolder holder = (ViewHolder) v.getTag();
        holder.mTitleView.setText(book.getmTitle());
        holder.mDescriptionView.setText(book.getmDescription());
      //  holder.mUrlView.setText(book.getmUrl());

        holder.mAuthorView.setText(book.getmAuthor());
       // Log.i(LOG, book.getmTitle());

        return view;
    }

    static class ViewHolder {
        TextView mTitleView;
        TextView mAuthorView;
        TextView mDescriptionView;
        TextView mUrlView;
    }
}
