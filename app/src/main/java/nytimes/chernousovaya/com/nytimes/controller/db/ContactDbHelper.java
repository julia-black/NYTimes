package nytimes.chernousovaya.com.nytimes.controller.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class ContactDbHelper extends SQLiteOpenHelper
        implements BaseColumns {

    public static final String TABLE_NAME = "fav_books";
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";
    public static final String DESC = "description";
    public static final String URL = "url";
    public static final String RANK = "rank";
    public static final String RANK_LAST_WEEK = "rank_of_last_week";
    public static final String BESTSELLER_DATE = "bestseller_date";

    public ContactDbHelper(Context context) {
        super(context, BooksContentProvider.DB_DATABASE_1, null, BooksContentProvider.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME
                + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE + " TEXT NOT NULL, "
                + AUTHOR + " TEXT NOT NULL, "
                + DESC + " TEXT, "
                + URL + " TEXT NOT NULL, "
                + RANK + " INTEGER, "
                + RANK_LAST_WEEK + " INTEGER, "
                + BESTSELLER_DATE + " DATETIME);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}