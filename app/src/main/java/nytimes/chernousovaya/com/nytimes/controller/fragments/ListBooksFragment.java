package nytimes.chernousovaya.com.nytimes.controller.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import nytimes.chernousovaya.com.nytimes.R;
import nytimes.chernousovaya.com.nytimes.controller.adapters.BookAdapter;
import nytimes.chernousovaya.com.nytimes.model.Book;

public class ListBooksFragment extends Fragment {

    private static final String LOG = ListBooksFragment.class.getSimpleName();

    @BindView(R.id.card_recycler_view_books)
    RecyclerView mRecyclerView;

    private String mNameOfSection;
    private Listener mListener;
    private List<Book> mListBooks;
    private ArrayList<Book> mArrayList = new ArrayList<>();

    private Spinner spinner;
    private EditText editText;
    private String currentSort;


    public interface Listener {
        List<Book> getCurrentBooksInActivity(String nameOfSection);

        EditText getEditText();

        Spinner getSpinnerSorting();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (ListBooksFragment.Listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement Listener");
        }
        mNameOfSection = getArguments().getString("nameOfSection", "");
        mListBooks = mListener.getCurrentBooksInActivity(mNameOfSection);
        editText = mListener.getEditText();
        spinner = mListener.getSpinnerSorting();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mArrayList.addAll(mListBooks);

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
                searchText(text);
            }
        });

        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(getActivity(), R.array.sort_books, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                String[] choose = getResources().getStringArray(R.array.sort_books);
                sortListBooks(choose[selectedItemPosition]);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        initRecycleView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_books_fragment,
                container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private void searchText(String searchText) {
        searchText = searchText.toLowerCase(Locale.getDefault());
        mListBooks.clear();
        if (searchText.length() == 0) {
            mListBooks.addAll(mArrayList);
        } else {
            for (Book book : mArrayList) {
                if (book.getmTitle().toLowerCase(Locale.getDefault()).contains(searchText)
                        || book.getmAuthor().toLowerCase(Locale.getDefault()).contains(searchText))
                    mListBooks.add(book);
            }
        }
        sortListBooks(currentSort);
        updateRecycleView();
    }

    private void sortListBooks(String sort) {

        currentSort = sort;
        switch (sort) {
            case "By name": {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mListBooks.sort((section, t1) -> section.getmTitle().compareTo(t1.getmTitle()));
                } else {
                    sortOld(sort);
                }
                break;
            }
            case "By author": {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mListBooks.sort((book, t1) -> book.getmAuthor().compareTo(t1.getmAuthor()));
                } else {
                    sortOld("By author");
                }
                break;
            }
            case "By rank": {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mListBooks.sort((book, t1) -> {
                        if (book.getmRank() > t1.getmRank()) {
                            return 1;
                        } else if (book.getmRank() < t1.getmRank()) {
                            return -1;
                        } else return 0;
                    });
                } else {
                    sortOld("By rank");
                }
                break;
            }
            case "By rank of last week": {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mListBooks.sort((book, t1) -> {
                        if (book.getmRankLastWeek() > t1.getmRankLastWeek()) {
                            return 1;
                        } else if (book.getmRankLastWeek() < t1.getmRankLastWeek()) {
                            return -1;
                        } else return 0;
                    });
                } else {
                    sortOld("By rank of last week");
                }
                break;
            }
            case "By bestseller date": {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mListBooks.sort((book, t1) -> book.getmBestsellerDate().compareTo(t1.getmBestsellerDate()));
                } else {
                    sortOld("By bestseller date");
                }
                break;
            }
            default:
                break;
        }
        updateRecycleView();
        //  mRecyclerView.smoothScrollToPosition(0);
    }

    private void sortOld(String sort) {
        for (int i = mListBooks.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                boolean resultCompare = false;
                switch (sort) {
                    case "By name": {
                        if (mListBooks.get(j).getmTitle().compareTo(mListBooks.get(j + 1).getmTitle()) > 0) {
                            resultCompare = true;
                        }
                        break;
                    }
                    case "By author": {
                        if (mListBooks.get(j).getmAuthor().compareTo(mListBooks.get(j + 1).getmAuthor()) > 0) {
                            resultCompare = true;
                        }
                        break;
                    }
                    case "By rank": {
                        if (mListBooks.get(j).getmRank() > mListBooks.get(j + 1).getmRank()) {
                            resultCompare = true;
                        }
                        break;
                    }
                    case "By rank of last week": {
                        if (mListBooks.get(j).getmRankLastWeek() > mListBooks.get(j + 1).getmRankLastWeek()) {
                            resultCompare = true;
                        }
                        break;
                    }
                    case "By bestseller date": {
                        if (mListBooks.get(j).getmBestsellerDate().compareTo(mListBooks.get(j + 1).getmBestsellerDate()) > 0) {
                            resultCompare = true;
                        }
                        break;
                    }
                    default:
                        break;
                }
                if (resultCompare) {
                    Book temp = mListBooks.get(j);
                    mListBooks.set(j, mListBooks.get(j + 1));
                    mListBooks.set(j + 1, temp);
                }
            }
        }
    }

    private void initRecycleView() {
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new BookAdapter(this.getActivity(), mListBooks);
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }


    private void updateRecycleView() {
        RecyclerView.Adapter adapter = new BookAdapter(this.getActivity(), mListBooks);
        mRecyclerView.setAdapter(adapter);
    }


    public static ListBooksFragment newInstance(String nameOfSection) {
        ListBooksFragment listBooksFragment = new ListBooksFragment();
        Bundle args = new Bundle();
        args.putString("nameOfSection", nameOfSection);
        Log.i(LOG, "new Instance," + nameOfSection);
        listBooksFragment.setArguments(args);
        return listBooksFragment;
    }
}
