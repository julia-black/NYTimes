package nytimes.chernousovaya.com.nytimes.controller.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nytimes.chernousovaya.com.nytimes.R;
import nytimes.chernousovaya.com.nytimes.controller.db.BooksContentProvider;
import nytimes.chernousovaya.com.nytimes.controller.db.ContactDbHelper;
import nytimes.chernousovaya.com.nytimes.model.Book;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private static final String LOG = SectionRecyclerAdapter.class.getSimpleName();
    private List<Book> mListBooks;

    private final Context mContext;

    public interface Listener {
        void onAmazonClicked(String url);

        void addFavourite(Book book);

        void deleteFavourite(Book book);
    }

    public BookAdapter(Context context, List<Book> listBooks) {
        this.mListBooks = listBooks;
        this.mContext = context;

    }

    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_book, viewGroup, false);
        return new ViewHolder(view);
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

    @Override
    public void onBindViewHolder(BookAdapter.ViewHolder holder, int position) {
        holder.mTitleView.setText(mListBooks.get(position).getmTitle());

        holder.mDescriptionView.setText(mListBooks.get(position).getmDescription());
        if (isFavouriteBook(mListBooks.get(position))) {
            holder.mFavView.setImageResource(R.drawable.ic_star_black_24dp);
        } else {
            holder.mFavView.setImageResource(R.drawable.ic_star_border_black_24dp);
        }

        View.OnClickListener favouriteClickListener = v -> {
            BookAdapter.Listener listener = (BookAdapter.Listener) mContext;
            if (isFavouriteBook(mListBooks.get(position))) {
                listener.deleteFavourite(mListBooks.get(position));
                holder.mFavView.setImageResource(R.drawable.ic_star_border_black_24dp);
            } else {
                listener.addFavourite(mListBooks.get(position));
                holder.mFavView.setImageResource(R.drawable.ic_star_black_24dp);
            }
        };

        holder.mFavView.setOnClickListener(favouriteClickListener);

        if (holder.mDescriptionView.getText().length() < 100) {
            holder.mDescriptionView.setLines(1);
        }
        holder.mUrlView.setOnClickListener(view1 -> {
            BookAdapter.Listener listener = (BookAdapter.Listener) mContext;
            listener.onAmazonClicked(mListBooks.get(position).getmUrl());
        });
        holder.mAuthorView.setText(mListBooks.get(position).getmAuthor());
        holder.mRankView.setText("Rank:" + mListBooks.get(position).getmRank() + " Rank of last week:" + mListBooks.get(position).getmRankLastWeek());
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        holder.mDateView.setText(format.format(mListBooks.get(position).getmBestsellerDate()));
    }

    @Override
    public int getItemCount() {
        return mListBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView mTitleView;

        @BindView(R.id.author)
        TextView mAuthorView;

        @BindView(R.id.description)
        TextView mDescriptionView;

        @BindView(R.id.url)
        Button mUrlView;

        @BindView(R.id.rank)
        TextView mRankView;

        @BindView(R.id.date)
        TextView mDateView;

        @BindView(R.id.favouriteButton)
        ImageButton mFavView;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}