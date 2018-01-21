package nytimes.chernousovaya.com.nytimes.controller.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import nytimes.chernousovaya.com.nytimes.R;


public class HomePageActivity extends ParentActivity {

    private static final String LOG = "HomePageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setDrawer(toolbar);
        setClickListeners();
        super.getmDrawerResult().setSelection(0);
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

    private void setClickListeners() {
        TextView v = (TextView) findViewById(R.id.books);
        v.setOnClickListener(view -> toBooksActivity());
    }
}
