package nytimes.chernousovaya.com.nytimes.controller.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import nytimes.chernousovaya.com.apinytimes.BooksAPI;
import nytimes.chernousovaya.com.apinytimes.model.BookDetail;
import nytimes.chernousovaya.com.apinytimes.model.Result;
import nytimes.chernousovaya.com.nytimes.R;
import nytimes.chernousovaya.com.nytimes.controller.adapters.BookItemAdapter;
import nytimes.chernousovaya.com.nytimes.model.Book;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class ListBooksFragment extends Fragment {

    private static final String LOG = "ListBooksFragment";
    private BooksAPI mBooksAPI;
   // private BookItemAdapter mBookItemAdapter;
    private String mNameOfSection;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_books_fragment,
                container, false);
        mBooksAPI = new BooksAPI();
        Log.i(LOG, "Hi!");

        mNameOfSection = getArguments().getString("nameOfSection", "");

        List<Book> listBooks = getListBooksfromAPI();
        BookItemAdapter bookItemAdapter = new BookItemAdapter(this.getActivity(),listBooks);

        ListView listView = view.findViewById(R.id.list_books);
        listView.setAdapter(bookItemAdapter);

        return view;

    }

    public static ListBooksFragment newInstance(String nameOfSection) {
        ListBooksFragment listBooksFragment = new ListBooksFragment();
        Bundle args = new Bundle();
        args.putString("nameOfSection", nameOfSection);
        Log.i(LOG, "new Instance," + nameOfSection);
        listBooksFragment.setArguments(args);
        return listBooksFragment;
    }

    private List<Book> getListBooksfromAPI(){

        List<Result> listResults = mBooksAPI.getListBooksByName(mNameOfSection);

        List<Book> listBooks = new ArrayList<>();

        for (Result result : listResults){
            Book newBook = new Book();
            newBook.setmUrl(result.getAmazon_product_url());

            BookDetail bookDetail = result.getBook_details().get(0);
            newBook.setmTitle(bookDetail.getTitle());
            newBook.setmAuthor(bookDetail.getAuthor());
            newBook.setmContributor(bookDetail.getContributor());
            newBook.setmDescription(bookDetail.getDescription());
            newBook.setmPublisher(bookDetail.getPublisher());

            Log.i(LOG,newBook.toString());
            listBooks.add(newBook);
        }

        return listBooks;
    }
}
