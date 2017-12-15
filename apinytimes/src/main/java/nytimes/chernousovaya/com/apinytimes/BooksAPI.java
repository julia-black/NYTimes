package nytimes.chernousovaya.com.apinytimes;

import android.os.StrictMode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import nytimes.chernousovaya.com.apinytimes.model.ListBooks;
import nytimes.chernousovaya.com.apinytimes.model.NameBooks;
import nytimes.chernousovaya.com.apinytimes.model.Result;
import nytimes.chernousovaya.com.apinytimes.model.RootObject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BooksAPI {

    private static final String LOG = "BooksAPI";
    private static final String KEY = "ba4175181f1f48fb8205a2148f567c88";
    private static final String URL = "http://api.nytimes.com/svc/books/v3/";

    private Gson mGson = new GsonBuilder().create();

    private Retrofit mRetrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(mGson))
            .baseUrl(URL)
            .build();
    private RootObject mRootObject;

    private NYTimes mNYTimes = mRetrofit.create(NYTimes.class);

    public BooksAPI() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        mRootObject = getRootObject();
    }

    public RootObject getRootObject() {
        Call<RootObject> call = mNYTimes.getNames(KEY);
        Response<RootObject> response;
        RootObject obj = null;
        try {
            response = call.execute();
            obj = response.body();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }

    //Получить наименования групп книг
    public List<NameBooks> getNamesBooks() {
        return mRootObject.getNamesBooks();
    }

    public List<Result> getListBooksByName(String nameOfList) {
        Call<ListBooks> call = mNYTimes.getListBooksByName(KEY, nameOfList);
        Response<ListBooks> response;
        ListBooks obj = null;
        try {
            response = call.execute();
            obj = response.body();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj.getResults();
    }

}
