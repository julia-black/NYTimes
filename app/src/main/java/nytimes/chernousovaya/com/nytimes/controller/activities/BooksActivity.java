package nytimes.chernousovaya.com.nytimes.controller.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import nytimes.chernousovaya.com.nytimes.R;
import nytimes.chernousovaya.com.nytimes.controller.adapters.BookItemAdapter;
import nytimes.chernousovaya.com.nytimes.controller.fragments.ListBooksFragment;
import nytimes.chernousovaya.com.nytimes.controller.fragments.SectionsBooksFragment;

public class BooksActivity extends AppCompatActivity implements SectionsBooksFragment.Listener, BookItemAdapter.Listener{

    private Drawer.Result mDrawerResult;

    private static final String LOG = "BooksActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                                (InputMethodManager)BooksActivity.this.getSystemService
                                        (Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(BooksActivity.this.
                                getCurrentFocus().getWindowToken(), 0);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }
                })
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        if(drawerItem.getIdentifier() == 0){
                            toHomePageActivity();
                        }
                    }
                })
                .build();

        SectionsBooksFragment sectionsBooksFragment = new SectionsBooksFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.books_container, sectionsBooksFragment)
                .show(sectionsBooksFragment)
                .commit();
    }

    private void toHomePageActivity() {
        Intent intent = new Intent(BooksActivity.this, HomePageActivity.class);
        startActivity(intent);
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
         ListBooksFragment listBooksFragment = ListBooksFragment.newInstance(nameOfSection);
         Log.i(LOG, nameOfSection);

         getFragmentManager().beginTransaction()
                 .replace(R.id.books_container,listBooksFragment)
                 .commit();

     }

    @Override
    public void onAmazonClicked(String url) {
        Uri address = Uri.parse(url);
        Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
        startActivity(openlinkIntent);
    }
}
