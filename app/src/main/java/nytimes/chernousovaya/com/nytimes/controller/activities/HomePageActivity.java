package nytimes.chernousovaya.com.nytimes.controller.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

import nytimes.chernousovaya.com.nytimes.R;


public class HomePageActivity extends AppCompatActivity {

    private static final String LOG = "HomePageActivity";
    private Drawer.Result mDrawerResult;
    //private ArrayList<String> nameBooksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        setDrawer();
        setClickListeners();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    private void toBooksActivity() {
        Intent intent = new Intent(HomePageActivity.this, BooksActivity.class);
        startActivity(intent);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    private void setClickListeners() {
        TextView v = (TextView) findViewById(R.id.books);
        v.setOnClickListener(view -> toBooksActivity());
    }

    private void setDrawer() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
                        InputMethodManager inputMethodManager = (InputMethodManager)
                                HomePageActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow
                                (HomePageActivity.this.getCurrentFocus().getWindowToken(), 0);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }
                })
                .withOnDrawerItemClickListener((parent, view, position, id, drawerItem) -> {

                    if (drawerItem.getIdentifier() == 1) {
                        toBooksActivity();
                    }
                })
                .build();
    }

}
