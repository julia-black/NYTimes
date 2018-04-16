package nytimes.chernousovaya.com.nytimes.controller.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import nytimes.chernousovaya.com.nytimes.R;


public class HomePageActivity extends ParentActivity {

    private static final String LOG = HomePageActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.view_all)
    Button buttonViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG, "onCreate");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawer(toolbar);
        buttonViewAll.setOnClickListener(view -> toBooksActivity());
    }

    @Override
    protected void onStart() {
        super.onStart();
        super.getmDrawerResult().setSelection(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

   // private void setClickListeners() {
   //     TextView v = (TextView) findViewById(R.id.books);
   //     v.setOnClickListener(view -> toBooksActivity());
   // }

    protected void toBooksActivity() {
        Intent intent = new Intent(HomePageActivity.this, BooksActivity.class);
        startActivity(intent);
    }
}
