package nytimes.chernousovaya.com.nytimes.controller.adapters;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import nytimes.chernousovaya.com.nytimes.R;
import nytimes.chernousovaya.com.nytimes.controller.db.BooksContentProvider;
import nytimes.chernousovaya.com.nytimes.controller.db.ContactDbHelper;
import nytimes.chernousovaya.com.nytimes.model.Book;

public class BookItemAdapter extends BaseAdapter {

    private static final String LOG = "BookItemAdapter";
    private List<Book> mListBooks;
    private ArrayList<Book> mArrayList = new ArrayList<>();
    private final Context mContext;
    private final LayoutInflater mInflater;

    //  public BookItemAdapter(Context context, Cursor c) {
    //      super(context, c);
    //      this.mContext = context;
    //
    //  }

    public interface Listener {
        void onAmazonClicked(String url);

        void addFavourite(Book book);

        void deleteFavourite(Book book);
    }

    public BookItemAdapter(Context context, List<Book> data) {
        this.mContext = context;
        this.mListBooks = data;
        this.mInflater = LayoutInflater.from(context);
        this.mArrayList.addAll(data);
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
        // final boolean[] isFavourite = {false}; //далее тут будет получение данных из бд на то, входит ли книга в фавориты
        Book book = getItem(i);
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_book, null);
            holder = new ViewHolder();
            holder.mTitleView = view.findViewById(R.id.title);
            holder.mAuthorView = view.findViewById(R.id.author);
            holder.mDescriptionView = view.findViewById(R.id.description);
            holder.mUrlView = view.findViewById(R.id.url);
            holder.mRankView = view.findViewById(R.id.rank);
            holder.mDateView = view.findViewById(R.id.date);
            holder.mFavView = view.findViewById(R.id.favouriteButton);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mTitleView.setText(book.getmTitle());
        holder.mDescriptionView.setText(book.getmDescription());
        if (isFavouriteBook(book)) {
            holder.mFavView.setImageResource(R.drawable.ic_star_black_24dp);
        } else {
            holder.mFavView.setImageResource(R.drawable.ic_star_border_black_24dp);
        }

        holder.mFavView.setOnClickListener(view2 -> {
            Listener listener = (Listener) mContext;
            if (isFavouriteBook(book)) {
                listener.deleteFavourite(book);
                holder.mFavView.setImageResource(R.drawable.ic_star_border_black_24dp);
            } else {
                listener.addFavourite(book);
                holder.mFavView.setImageResource(R.drawable.ic_star_black_24dp);
            }
        });

        if (holder.mDescriptionView.getText().length() < 100) {
            holder.mDescriptionView.setLines(1);
        }
        holder.mUrlView.setOnClickListener(view1 -> {
            Listener listener = (Listener) mContext;
            listener.onAmazonClicked(book.getmUrl());
        });
        holder.mAuthorView.setText(book.getmAuthor());
        holder.mRankView.setText("Rank:" + book.getmRank() + " Rank of last week:" + book.getmRankLastWeek());
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        holder.mDateView.setText(format.format(book.getmBestsellerDate()));
        return view;
    }

    private boolean isFavouriteBook(Book book) {
        Cursor cursor = mContext.getContentResolver().query(BooksContentProvider.CONTENT_URI,
                null, null, null, null);
        while (cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndex(ContactDbHelper.TITLE)).equals(book.getmTitle())) {
                return true;
            }
        }
        return false;
    }

    public void filter(String searchText) {
        searchText = searchText.toLowerCase(Locale.getDefault());
        mListBooks.clear();
        if (searchText.length() == 0) {
            mListBooks.addAll(mArrayList);
        } else {
            for (Book book : mArrayList) {
                if (book.getmTitle().toLowerCase(Locale.getDefault()).contains(searchText)
                        || book.getmAuthor().toLowerCase(Locale.getDefault()).contains(searchText))
                    mListBooks.add(book);
            }
        }
        notifyDataSetChanged();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortByName() {
        mListBooks.sort((book, t1) -> book.getmTitle().compareTo(t1.getmTitle()));
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortByRank() {
        mListBooks.sort((book, t1) -> {
            if (book.getmRank() > t1.getmRank()) {
                return 1;
            } else if (book.getmRank() < t1.getmRank()) {
                return -1;
            } else return 0;
        });
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortByRankOfLastWeek() {
        mListBooks.sort((book, t1) -> {
            if (book.getmRankLastWeek() > t1.getmRankLastWeek()) {
                return 1;
            } else if (book.getmRankLastWeek() < t1.getmRankLastWeek()) {
                return -1;
            } else return 0;
        });
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortByAuthor() {
        mListBooks.sort((book, t1) -> book.getmAuthor().compareTo(t1.getmAuthor()));
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortByDate() {
        mListBooks.sort((book, t1) -> book.getmBestsellerDate().compareTo(t1.getmBestsellerDate()));
    }

    static class ViewHolder {
        TextView mTitleView;
        TextView mAuthorView;
        TextView mDescriptionView;
        TextView mUrlView;
        TextView mRankView;
        TextView mDateView;
        ImageButton mFavView;
    }
}
