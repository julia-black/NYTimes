package nytimes.chernousovaya.com.nytimes.controller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import nytimes.chernousovaya.com.nytimes.R;
import nytimes.chernousovaya.com.nytimes.model.Book;

public class BookItemAdapter extends BaseAdapter {

    private static final String LOG = "BookItemAdapter";
    private List<Book> mListBooks;
    private final Context mContext;
    private final LayoutInflater mInflater;

    public interface Listener {
        void onAmazonClicked(String url);
    }

    public BookItemAdapter(Context context, List<Book> data) {
        this.mContext = context;
        this.mListBooks = data;
        this.mInflater = LayoutInflater.from(context);
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Book book = getItem(i);
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_book, null);
            holder = new ViewHolder();
            holder.mTitleView = view.findViewById(R.id.title);
            holder.mAuthorView = view.findViewById(R.id.author);
            holder.mDescriptionView = view.findViewById(R.id.description);
            holder.mUrlView = view.findViewById(R.id.url);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mTitleView.setText(book.getmTitle());
        holder.mDescriptionView.setText(book.getmDescription());

        holder.mUrlView.setOnClickListener(view1 -> {
            Listener listener = (Listener) mContext;
            listener.onAmazonClicked(book.getmUrl());
        });
        holder.mAuthorView.setText(book.getmAuthor());

        return view;
    }

    static class ViewHolder {
        TextView mTitleView;
        TextView mAuthorView;
        TextView mDescriptionView;
        TextView mUrlView;
    }
}
