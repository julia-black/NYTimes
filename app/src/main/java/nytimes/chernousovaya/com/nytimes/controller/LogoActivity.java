package nytimes.chernousovaya.com.nytimes.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import nytimes.chernousovaya.com.nytimes.R;

public class LogoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        final Thread logoTimer = new Thread(){
            public void run(){
                try {
                    int logoTimer = 0;
                    while(logoTimer < 5000){
                        sleep(100);
                        logoTimer = logoTimer + 100;
                    }
                    toNewsListActivity();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    finish();
                }
            }
        };
        logoTimer.start();
    }
    public void toNewsListActivity(){
        Intent intent = new Intent(LogoActivity.this, HomePageActivity.class);
        startActivity(intent);
    }
}