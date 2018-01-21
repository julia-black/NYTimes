package nytimes.chernousovaya.com.nytimes.controller.fragments;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;
import java.util.Locale;

import nytimes.chernousovaya.com.nytimes.R;
import nytimes.chernousovaya.com.nytimes.controller.activities.BooksActivity;
import nytimes.chernousovaya.com.nytimes.controller.adapters.BookItemAdapter;
import nytimes.chernousovaya.com.nytimes.model.Book;

public class ListBooksFragment extends Fragment {

    private static final String LOG = "ListBooksFragment";
    private String mNameOfSection;
    BookItemAdapter bookItemAdapter;
    ListView listView;

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
        Log.i(LOG, listBooks.get(0).toString());
        bookItemAdapter = new BookItemAdapter(this.getActivity(), listBooks);

        EditText editText = getActivity().findViewById(R.id.search);

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                String text = editText.getText().toString().toLowerCase(Locale.getDefault());
                bookItemAdapter.filter(text);
            }
        });

        Spinner spinner = getActivity().findViewById(R.id.spinnerSorting);

        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(getActivity(), R.array.sort_books, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                String[] choose = getResources().getStringArray(R.array.sort_books);
                sortListBooks(choose[selectedItemPosition]);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        listView = view.findViewById(R.id.list_books);
        listView.setAdapter(bookItemAdapter);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void sortListBooks(String sort) {

        switch (sort) {
            case "By name": {
                bookItemAdapter.sortByName();
                break;
            }
            case "By author": {
                bookItemAdapter.sortByAuthor();
                break;
            }
            case "By rank": {
                bookItemAdapter.sortByRank();
                break;
            }
            case "By rank of last week": {
                bookItemAdapter.sortByRankOfLastWeek();
                break;
            }
            case "By published date": {
                bookItemAdapter.sortByDate();
                break;
            }
            default:
                break;
        }
        listView.smoothScrollToPosition(0);
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
