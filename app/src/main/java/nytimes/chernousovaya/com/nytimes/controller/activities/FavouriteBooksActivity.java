package nytimes.chernousovaya.com.nytimes.controller.activities;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nytimes.chernousovaya.com.nytimes.R;
import nytimes.chernousovaya.com.nytimes.controller.adapters.BookAdapter;
import nytimes.chernousovaya.com.nytimes.controller.db.BooksContentProvider;
import nytimes.chernousovaya.com.nytimes.controller.db.ContactDbHelper;
import nytimes.chernousovaya.com.nytimes.model.Book;

public class FavouriteBooksActivity extends ParentActivity implements BookAdapter.Listener {
    private static final String LOG = FavouriteBooksActivity.class.getSimpleName();


    @BindView(R.id.card_recycler_view_favourites)
    RecyclerView mRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private List<Book> mFavouriteBooks;

    private Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_books);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.favourite_books);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawer(toolbar);

        mCursor = getContentResolver().query(BooksContentProvider.CONTENT_URI, null, null,
                null, null);
        mFavouriteBooks = getFavouriteBooksInDB();
        initRecycleView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        super.getmDrawerResult().setSelection(2);
    }

    private List<Book> getFavouriteBooksInDB() {
        List<Book> listBooks = new ArrayList<>();
        Book book;
        String title;
        String author;
        String url;
        int rank;
        int rankOfLastWeek;
        String description;
        String bestDate;
        while (mCursor.moveToNext()) {
            title = mCursor.getString(mCursor.getColumnIndex(ContactDbHelper.TITLE));
            author = mCursor.getString(mCursor.getColumnIndex(ContactDbHelper.AUTHOR));
            url = mCursor.getString(mCursor.getColumnIndex(ContactDbHelper.URL));
            rank = mCursor.getInt(mCursor.getColumnIndex(ContactDbHelper.RANK));
            rankOfLastWeek = mCursor.getInt(mCursor.getColumnIndex(ContactDbHelper.RANK_LAST_WEEK));
            bestDate = mCursor.getString(mCursor.getColumnIndex(ContactDbHelper.BESTSELLER_DATE));
            description = mCursor.getString(mCursor.getColumnIndex(ContactDbHelper.DESC));
            book = new Book(title, description, "", author, "", url, rank, rankOfLastWeek, new Date(bestDate));
            listBooks.add(book);
        }
        return listBooks;
    }

    @Override
    public void onAmazonClicked(String url) {
        Log.i(LOG, "to amazon" + url);
        Uri address = Uri.parse(url);
        Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);
        startActivity(openLinkIntent);
    }

    @Override
    public void addFavourite(Book book) {
        Log.i(LOG, "Add to favourite " + book.getmTitle());
    }

    @Override
    public void deleteFavourite(Book book) {
        Log.i(LOG, "Delete of favourite " + book.getmTitle());
        getContentResolver().delete(BooksContentProvider.CONTENT_URI, "title='" + book.getmTitle() + "'", null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    private void initRecycleView() {
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new BookAdapter(this, mFavouriteBooks);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

}


