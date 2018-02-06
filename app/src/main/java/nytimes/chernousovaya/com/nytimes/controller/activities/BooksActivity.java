package nytimes.chernousovaya.com.nytimes.controller.activities;

import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nytimes.chernousovaya.com.apinytimes.model.NameBooks;
import nytimes.chernousovaya.com.nytimes.R;
import nytimes.chernousovaya.com.nytimes.controller.adapters.BookAdapter;
import nytimes.chernousovaya.com.nytimes.controller.db.BooksContentProvider;
import nytimes.chernousovaya.com.nytimes.controller.db.ContactDbHelper;
import nytimes.chernousovaya.com.nytimes.controller.fragments.ListBooksFragment;
import nytimes.chernousovaya.com.nytimes.controller.fragments.SectionsBooksFragment;
import nytimes.chernousovaya.com.nytimes.controller.service.DataService;
import nytimes.chernousovaya.com.nytimes.model.Book;

public class BooksActivity extends ParentActivity implements
        SectionsBooksFragment.Listener, ListBooksFragment.Listener, BookAdapter.Listener {

    private static final String LOG = BooksActivity.class.getSimpleName();

    private List<NameBooks> mSections = new ArrayList<>();
    private List<Book> mBooksInCurrentSection = new ArrayList<>();
    private Intent mIntent;
    private ServiceConnection mServiceConnection;
    private DataService mService;
    private boolean mBound = false;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.search)
    EditText editText;

    @BindView(R.id.spinnerSorting)
    Spinner spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(LOG, "onCreate");
        super.onCreate(savedInstanceState);

        try {
            if (!hasConnection()) {
                throw new NullPointerException("Error connections");
            } else {
                mIntent = new Intent(this, DataService.class);
                mServiceConnection = new ServiceConnection() {
                    public void onServiceConnected(ComponentName name, IBinder binder) {

                        Log.d(LOG, "MainActivity onServiceConnected");
                        mService = ((DataService.DataBinder) binder).getService();
                        mSections = mService.downloadNameOfBooks();
                        Log.d(LOG, "" + mSections.size());

                        //если перенести в onCreate, то не успевают получиться данные
                        SectionsBooksFragment sectionsBooksFragment = new SectionsBooksFragment();
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .add(R.id.books_container, sectionsBooksFragment)
                                .show(sectionsBooksFragment)
                                .commit();
                        mBound = true;
                    }

                    public void onServiceDisconnected(ComponentName name) {
                        Log.d(LOG, "MainActivity onServiceDisconnected");
                        mBound = false;
                    }
                };
                startService(mIntent);
                bindService(mIntent, mServiceConnection, 0);
            }
        } catch (NullPointerException e) {
            Log.e(LOG, e.getMessage());
            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
        } finally {
            mSections = new ArrayList<>();
        }

        setContentView(R.layout.activity_books);
        ButterKnife.bind(this);

        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.books);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawer(toolbar);
    }

    public boolean hasConnection() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBound) {
            unbindService(mServiceConnection);
            mBound = false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        super.getmDrawerResult().setSelection(1);
    }

    @Override
    public void onSectionClicked(NameBooks nameOfSection) {
        bindService(mIntent, mServiceConnection, 0);
        mBooksInCurrentSection = mService.downloadBooks(nameOfSection.getListName());
        ListBooksFragment listBooksFragment = ListBooksFragment.newInstance(nameOfSection.getListName());
        Log.i(LOG, nameOfSection.getListName());

        getFragmentManager().beginTransaction()
                .replace(R.id.books_container, listBooksFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onAmazonClicked(String url) {
        Uri address = Uri.parse(url);
        Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);
        startActivity(openLinkIntent);
    }

    @Override
    public void addFavourite(Book book) {
        Log.i(LOG, "Add to favourite " + book.getmTitle());

        ContentValues values = new ContentValues();
        values.put(ContactDbHelper.TITLE, book.getmTitle());
        values.put(ContactDbHelper.AUTHOR, book.getmAuthor());
        values.put(ContactDbHelper.DESC, book.getmDescription());
        values.put(ContactDbHelper.URL, book.getmUrl());
        values.put(ContactDbHelper.RANK, book.getmRank());
        values.put(ContactDbHelper.RANK_LAST_WEEK, book.getmRankLastWeek());
        values.put(ContactDbHelper.BESTSELLER_DATE, book.getmBestsellerDate().toString());

        getContentResolver().insert(BooksContentProvider.CONTENT_URI, values);

    }

    @Override
    public void deleteFavourite(Book book) {
        Log.i(LOG, "Delete of favourite " + book.getmTitle());
        getContentResolver().delete(BooksContentProvider.CONTENT_URI, "title='" + book.getmTitle() + "'", null);
    }

    @Override
    public List<NameBooks> getNameBooksInActivity() {
        return mSections;
    }

    @Override
    public List<Book> getCurrentBooksInActivity(String nameOfSection) {
        return mBooksInCurrentSection;
    }

    @Override
    public Spinner getSpinnerSorting() {
        return spinner;
    }

    @Override
    public EditText getEditText() {
        return editText;
    }

}
