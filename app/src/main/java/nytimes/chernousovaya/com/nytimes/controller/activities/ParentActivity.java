package nytimes.chernousovaya.com.nytimes.controller.activities;

import android.app.Activity;
import android.content.Intent;
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
import nytimes.chernousovaya.com.nytimes.controller.drawer.DrawerItem;

public class ParentActivity extends AppCompatActivity {
    private static final String LOG = ParentActivity.class.getSimpleName();
    protected Drawer.Result mDrawerResult;

    public Drawer.Result getmDrawerResult() {
        return mDrawerResult;
    }

    protected void setDrawer(Toolbar toolbar) {
        Log.i(LOG, "setDrawer");
        DrawerItem drawerItemBooks = new DrawerItem(BooksActivity.class);

        DrawerItem drawerItemFav = new DrawerItem(FavouriteBooksActivity.class);

        DrawerItem drawerItemHome = new DrawerItem(HomePageActivity.class);

        mDrawerResult = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(

                        drawerItemHome.withName(R.string.home_page)
                                .withIcon(FontAwesome.Icon.faw_home).withIdentifier(0),
                        drawerItemBooks.withName(R.string.books).withIdentifier(1).withIcon(FontAwesome.Icon.faw_book).withIdentifier(1),
                        drawerItemFav.withName(R.string.favourite_books)
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
                .withOnDrawerItemClickListener((AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) -> {
                    DrawerItem drawerItem1 = (DrawerItem) drawerItem;
                    Intent intent = new Intent(ParentActivity.this, drawerItem1.getmActivityClass());
                    startActivity(intent);
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
}
