package nytimes.chernousovaya.com.nytimes.controller.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

import nytimes.chernousovaya.com.nytimes.R;

public class ParentActivity extends AppCompatActivity {
    private static final String LOG = "ParentActivity";
    protected Drawer.Result mDrawerResult;

    public Drawer.Result getmDrawerResult() {
        return mDrawerResult;
    }


    protected void setDrawer(Toolbar toolbar) {
        Log.i(LOG, "setDrawer");
        mDrawerResult = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.home_page)
                                .withIcon(FontAwesome.Icon.faw_home).withIdentifier(0),
                        new PrimaryDrawerItem().withName(R.string.books)
                                .withIcon(FontAwesome.Icon.faw_book).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.favourite_books)
                                .withIcon(FontAwesome.Icon.faw_star).withIdentifier(4),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.setting)
                                .withIcon(FontAwesome.Icon.faw_cog).withIdentifier(3)
                )
                .withOnDrawerListener(new Drawer.OnDrawerListener() {
                    @Override
                    public void onDrawerOpened(View drawerView) {
                        InputMethodManager inputMethodManager = (InputMethodManager)
                                ParentActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow
                                (ParentActivity.this.getCurrentFocus().getWindowToken(), 0);
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                    }
                })
                .withOnDrawerItemClickListener((parent, view, position, id, drawerItem) -> {
                    if (drawerItem.getIdentifier() == 1) {
                        toBooksActivity();
                    } else if (drawerItem.getIdentifier() == 0) {
                        toHomePageActivity();
                    } else if (drawerItem.getIdentifier() == 4) {
                        toFavouriteBooksActivity();
                    }
                })
                .build();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerResult.isDrawerOpen()) {
            mDrawerResult.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    protected void toBooksActivity() {
        Intent intent = new Intent(ParentActivity.this, BooksActivity.class);
        startActivity(intent);
    }

    protected void toFavouriteBooksActivity() {
        Intent intent = new Intent(ParentActivity.this, FavouriteBooksActivity.class);
        startActivity(intent);
    }

    protected void toHomePageActivity() {
        Intent mIntent = new Intent(ParentActivity.this, HomePageActivity.class);
        startActivity(mIntent);
    }
}
