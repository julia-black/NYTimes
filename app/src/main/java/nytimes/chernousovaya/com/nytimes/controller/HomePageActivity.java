package nytimes.chernousovaya.com.nytimes.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import nytimes.chernousovaya.com.nytimes.R;


public class HomePageActivity extends AppCompatActivity {

    private Drawer.Result mDrawerResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageSpan imageSpan = new ImageSpan(this, R.drawable.ic_book_24dp);
        SpannableString text = new SpannableString("     Books");
        text.setSpan(imageSpan, 0, 1, 0);
        TextView v = (TextView) findViewById(R.id.books);
        v.setText(text);
        v.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                toBooksActivity();

            }
        });

        imageSpan = new ImageSpan(this, R.drawable.ic_story_24dp);
        text = new SpannableString("     Top Stories");
        text.setSpan(imageSpan, 0, 1, 0);
        v = (TextView) findViewById(R.id.top_stories);
        v.setText(text);

        new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.books).withIcon(FontAwesome.Icon.faw_book).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.top_stories).withIcon(FontAwesome.Icon.faw_bookmark).withIdentifier(2),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem().withName(R.string.setting).withIcon(FontAwesome.Icon.faw_cog).withIdentifier(3)

                )
            .withOnDrawerListener(new Drawer.OnDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                InputMethodManager inputMethodManager = (InputMethodManager)HomePageActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(HomePageActivity.this.getCurrentFocus().getWindowToken(), 0);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
            }
        })
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        if (drawerItem instanceof Nameable) {
                            Toast.makeText(HomePageActivity.this, HomePageActivity.this.getString(((Nameable) drawerItem).getNameRes()), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
               // .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
               //     @Override
               //     public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
               //       // if (drawerItem instanceof SecondaryDrawerItem) {
               //       //     Toast.makeText(HomePageActivity.this, HomePageActivity.this.getString(((SecondaryDrawerItem) drawerItem).getNameRes()), Toast.LENGTH_SHORT).show();
               //       // }
               //       // return false;
               //     }
               // })
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    //    getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
       // if (id == R.id.action_settings) {
       //     return true;
       // }
        return super.onOptionsItemSelected(item);
    }

    private void toBooksActivity(){
        Intent intent = new Intent(HomePageActivity.this, BooksActivity.class);
        startActivity(intent);
    }
}
