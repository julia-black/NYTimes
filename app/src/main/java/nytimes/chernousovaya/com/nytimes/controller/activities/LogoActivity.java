package nytimes.chernousovaya.com.nytimes.controller.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import nytimes.chernousovaya.com.nytimes.R;

public class LogoActivity extends Activity {
    private static final String LOG = LogoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        final Thread logoTimer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                    startActivity(new Intent(LogoActivity.this, HomePageActivity.class));
                } catch (InterruptedException e) {
                    Log.e(LOG, "Error in logoThread");
                } finally {
                    finish();
                }
            }
        };
        logoTimer.start();
    }
}
