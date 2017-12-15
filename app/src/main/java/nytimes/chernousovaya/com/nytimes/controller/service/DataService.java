package nytimes.chernousovaya.com.nytimes.controller.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nytimes.chernousovaya.com.apinytimes.BooksAPI;
import nytimes.chernousovaya.com.apinytimes.model.BookDetail;
import nytimes.chernousovaya.com.apinytimes.model.NameBooks;
import nytimes.chernousovaya.com.apinytimes.model.Result;
import nytimes.chernousovaya.com.nytimes.model.Book;

public class DataService extends Service {

    private static final String LOG = "DataService";

    private BooksAPI mBooksAPI;
    private List<NameBooks> sections = new ArrayList<>();
    private DataBinder binder = new DataBinder();

    public List<Book> downloadBooks(String nameOfSections) {
        List<Result> listResults = mBooksAPI.getListBooksByName(nameOfSections);
        List<Book> listBooks = new ArrayList<>();

        for (Result result : listResults) {
            Book newBook = new Book();
            newBook.setmUrl(result.getAmazonProductUrl());

            BookDetail bookDetail = result.getBookDetails().get(0);
            newBook.setmTitle(bookDetail.getTitle());
            newBook.setmAuthor(bookDetail.getAuthor());
            newBook.setmContributor(bookDetail.getContributor());
            newBook.setmDescription(bookDetail.getDescription());
            newBook.setmPublisher(bookDetail.getPublisher());

            listBooks.add(newBook);
        }
        return listBooks;
    }

    public class DataBinder extends Binder {
        public DataService getService() {
            return DataService.this;
        }
    }

    public ArrayList<String> downloadSections() {
        ArrayList<String> sectionsString = new ArrayList<>();
        sections = mBooksAPI.getNamesBooks();
        for (NameBooks nameBooks : sections) {
            sectionsString.add(nameBooks.getListName());
        }
        return sectionsString;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mBooksAPI = new BooksAPI();
        Log.d(LOG, "Service create");
    }


    public IBinder onBind(Intent arg0) {
        Log.d(LOG, "Service onBind");
        return binder;
    }

    public boolean onUnbind(Intent intent) {
        Log.d(LOG, "Service onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG, "Service start");
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
