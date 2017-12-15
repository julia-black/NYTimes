package nytimes.chernousovaya.com.nytimes.controller.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

import java.util.ArrayList;
import java.util.List;

import nytimes.chernousovaya.com.nytimes.R;
import nytimes.chernousovaya.com.nytimes.controller.adapters.BookItemAdapter;
import nytimes.chernousovaya.com.nytimes.controller.fragments.ListBooksFragment;
import nytimes.chernousovaya.com.nytimes.controller.fragments.SectionsBooksFragment;
import nytimes.chernousovaya.com.nytimes.controller.service.DataService;
import nytimes.chernousovaya.com.nytimes.model.Book;

public class BooksActivity extends AppCompatActivity implements SectionsBooksFragment.Listener, BookItemAdapter.Listener {

    private static final String LOG = "BooksActivity";

    private Drawer.Result mDrawerResult;
    private static List<String> mSections = new ArrayList<>();
    private static List<Book> mBooksInCurrentSection = new ArrayList<>();
    private Intent mIntent;
    private ServiceConnection mServiceConnection;
    private DataService mService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIntent = new Intent(this, DataService.class);
        mServiceConnection = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                Log.d(LOG, "MainActivity onServiceConnected");
                mService = ((DataService.DataBinder) binder).getService();
                mSections = mService.downloadSections();
                unbindService(this);
                SectionsBooksFragment sectionsBooksFragment = new SectionsBooksFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.books_container, sectionsBooksFragment)
                        .show(sectionsBooksFragment)
                        .commit();
            }

            public void onServiceDisconnected(ComponentName name) {
                Log.d(LOG, "MainActivity onServiceDisconnected");
            }
        };
        startService(mIntent);
        bindService(mIntent, mServiceConnection, 0);
        setContentView(R.layout.activity_books);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.books);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerResult = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.home_page)
                                .withIcon(FontAwesome.Icon.faw_home).withIdentifier(0),
                        new PrimaryDrawerItem().withName(R.string.books)
                                .withIcon(FontAwesome.Icon.faw_book).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.top_stories)
                                .withIcon(FontAwesome.Icon.faw_bookmark).withIdentifier(2),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.setting)
                                .withIcon(FontAwesome.Icon.faw_cog).withIdentifier(3)

                )
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        InputMethodManager inputMethodManager =
                                (InputMethodManager) BooksActivity.this.getSystemService
                                        (Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(BooksActivity.this.
                                getCurrentFocus().getWindowToken(), 0);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }
                })
                .withOnDrawerItemClickListener((parent, view, position, id, drawerItem) -> {
                    if (drawerItem.getIdentifier() == 0) {
                        toHomePageActivity();
                    }
                })
                .build();
    }


    private void toHomePageActivity() {
        Intent mIntent = new Intent(BooksActivity.this, HomePageActivity.class);
        startActivity(mIntent);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerResult.isDrawerOpen()) {
            mDrawerResult.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onSectionClicked(String nameOfSection) {
        bindService(mIntent, mServiceConnection, 0);
        mBooksInCurrentSection = mService.downloadBooks(nameOfSection);
        unbindService(mServiceConnection);
        ListBooksFragment listBooksFragment = ListBooksFragment.newInstance(nameOfSection);
        Log.i(LOG, nameOfSection);

        getFragmentManager().beginTransaction()
                .replace(R.id.books_container, listBooksFragment)
                .commit();

    }

    @Override
    public void onAmazonClicked(String url) {
        Uri address = Uri.parse(url);
        Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);
        startActivity(openLinkIntent);
    }

    public static List<String> getSections() {
        return mSections;
    }

    public static List<Book> getBooksInCurrentSection() {
        return mBooksInCurrentSection;
    }

}
