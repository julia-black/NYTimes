package nytimes.chernousovaya.com.nytimes.controller.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import nytimes.chernousovaya.com.nytimes.R;
import nytimes.chernousovaya.com.nytimes.controller.activities.BooksActivity;
import nytimes.chernousovaya.com.nytimes.controller.adapters.BookItemAdapter;
import nytimes.chernousovaya.com.nytimes.model.Book;

public class ListBooksFragment extends Fragment {

    private static final String LOG = "ListBooksFragment";
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

        mNameOfSection = getArguments().getString("nameOfSection", "");

        List<Book> listBooks = getListBooks();
        BookItemAdapter bookItemAdapter = new BookItemAdapter(this.getActivity(), listBooks);

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

    private List<Book> getListBooks() {
        return BooksActivity.getBooksInCurrentSection();
    }
}
