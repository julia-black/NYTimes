package nytimes.chernousovaya.com.nytimes.controller;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nytimes.chernousovaya.com.nytimes.model.Link;
import nytimes.chernousovaya.com.nytimes.R;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BooksActivity extends Activity {

    private static final String KEY = "ba4175181f1f48fb8205a2148f567c88";
    private final String URL = "http://api.nytimes.com/svc/books/v3/lists/";

  private Gson gson = new GsonBuilder().create();

  private Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create(gson))
          .baseUrl(URL)
          .build();

  private Link intf = retrofit.create(Link.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Map<String, String> mapJson = new HashMap<String, String>();
        mapJson.put("api-key", KEY);

        Call<Object> call = intf.getNames(KEY);
        try {
            Response<Object> response = call.execute();
         //   List<Object> listObj = gson.fromJson(response.body().toString(), List.class);
          //  Map<String, String> map = gson.fromJson(response.body().toString(), Map.class);



            String str = "";

            Log.i("BooksActivity", str);

            Log.i("BooksActivity", response.body().toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
