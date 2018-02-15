package nytimes.chernousovaya.com.nytimes.controller.fragments;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import nytimes.chernousovaya.com.apinytimes.model.NameBooks;
import nytimes.chernousovaya.com.nytimes.R;
import nytimes.chernousovaya.com.nytimes.controller.activities.BooksActivity;
import nytimes.chernousovaya.com.nytimes.controller.adapters.SectionRecyclerAdapter;
import nytimes.chernousovaya.com.nytimes.model.Section;

public class SectionsBooksFragment extends Fragment {

    private static final String LOG = SectionsBooksFragment.class.getSimpleName();

    @BindView(R.id.card_recycler_view)
    RecyclerView mRecyclerView;

    private List<NameBooks> mSections;
    private List<NameBooks> mArrayList = new ArrayList<>();

    private Listener mListener;
    private EditText editText;
    private Spinner spinner;
    private String currentSort;

    public interface Listener {
        void onSectionClicked(NameBooks sectionName);

        List<NameBooks> getNameBooksInActivity();

        EditText getEditText();

        Spinner getSpinnerSorting();
    }

   @Override
   public void onAttach(Context context) {
       super.onAttach(context);

       try {
           mListener = (Listener) context;
       } catch (ClassCastException e) {
           throw new ClassCastException(context.toString()
                   + " must implement Listener");
       }
       mSections = mListener.getNameBooksInActivity();
       Log.i(LOG, mSections.size() + "");
       editText = mListener.getEditText();
       spinner = mListener.getSpinnerSorting();
   }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            mListener = (Listener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement Listener");
        }
        mSections = mListener.getNameBooksInActivity();
        Log.i(LOG, mSections.size() + "");
        editText = mListener.getEditText();
        spinner = mListener.getSpinnerSorting();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mArrayList.addAll(mSections);
        currentSort = "By name";
        editText.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_ATOP);
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
                Log.i(LOG, arg0.toString());
                String text = editText.getText().toString().toLowerCase(Locale.getDefault());
                searchText(text);
            }
        });

        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(getActivity(), R.array.sort_sections, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                String[] choose = getResources().getStringArray(R.array.sort_sections);
                sortSections(choose[selectedItemPosition]);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        initRecycleView();
    }

    private void searchText(String searchText) {
        searchText = searchText.toLowerCase(Locale.getDefault());
        mSections.clear();
        if (searchText.length() == 0) {
            mSections.addAll(mArrayList);
        } else {
            for (NameBooks nameBooks : mArrayList) {
                if (nameBooks.getListName().toLowerCase(Locale.getDefault()).contains(searchText))
                    mSections.add(nameBooks);
            }
        }
        sortSections(currentSort);
        updateRecycleView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
            mSections =  mListener.getNameBooksInActivity();
        }

        View view = null;
        try {
            view = inflater.inflate(R.layout.sections_fragment,
                    container, false);
            ButterKnife.bind(this, view);
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private void updateRecycleView() {
        RecyclerView.Adapter adapter = new SectionRecyclerAdapter(namesOfBookToSections(mSections));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(LOG, "onStop");
        searchText("");
    }

    private void sortSections(String sort) {
        currentSort = sort;
       switch (sort) {
           case "By name": {
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                   mSections.sort((section, t1) -> section.getListName().compareTo(t1.getListName()));
               } else {
                   sortOld(sort);
               }
               updateRecycleView();
               break;
           }
           case "By newest date": {
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                   mSections.sort((section, t1) -> section.getNewestPublishedDate().compareTo(t1.getNewestPublishedDate()));
               } else {
                   sortOld(sort);
               }
               updateRecycleView();
               break;
           }
           case "By oldest date": {
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                   mSections.sort((section, t1) -> section.getOldestPublishedDate().compareTo(t1.getOldestPublishedDate()));
               } else {
                   sortOld(sort);
               }
               updateRecycleView();
               break;
           }
           default:
               break;
       }
       mRecyclerView.smoothScrollToPosition(0);
    }

    private void sortOld(String sort) {
        for (int i = mSections.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                boolean resultCompare = false;
                switch (sort) {
                    case "By name": {
                        if (mSections.get(j).getListName().compareTo(mSections.get(j + 1).getListName()) > 0) {
                            resultCompare = true;
                        }
                        break;
                    }
                    case "By oldest date": {
                        if (mSections.get(j).getOldestPublishedDate().compareTo(mSections.get(j + 1).getOldestPublishedDate()) > 0) {
                            resultCompare = true;
                        }
                        break;
                    }
                    case "By newest date": {
                        if (mSections.get(j).getNewestPublishedDate().compareTo(mSections.get(j + 1).getNewestPublishedDate()) > 0) {
                            resultCompare = true;
                        }
                        break;
                    }
                    default:
                        break;
                }
                if (resultCompare) {
                    NameBooks temp = mSections.get(j);
                    mSections.set(j, mSections.get(j + 1));
                    mSections.set(j + 1, temp);
                }
            }
        }
    }

    private void initRecycleView() {
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new SectionRecyclerAdapter(namesOfBookToSections(mSections));
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
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    SectionsBooksFragment.Listener l = (SectionsBooksFragment.Listener) getActivity();
                    l.onSectionClicked(mSections.get(position));
                }
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

    private ArrayList<Section> namesOfBookToSections(List<NameBooks> nameBooks) {
        ArrayList<Section> sections = new ArrayList<>();
        for (NameBooks name : nameBooks) {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd");

            Date dateNewest = null;
            Date dateOldest = null;
            try {
                dateNewest = format.parse(name.getNewestPublishedDate());
                dateOldest = format.parse(name.getOldestPublishedDate());
            } catch (ParseException e) {
                Log.e(LOG, "Error parsing date");
            }
            Section section = new Section(name.getListName(), dateOldest, dateNewest);
            sections.add(section);
        }
        return sections;
    }
}
