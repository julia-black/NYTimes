package nytimes.chernousovaya.com.nytimes.controller.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import nytimes.chernousovaya.com.apinytimes.BooksAPI;
import nytimes.chernousovaya.com.apinytimes.model.NameBooks;
import nytimes.chernousovaya.com.nytimes.R;
import nytimes.chernousovaya.com.nytimes.controller.activities.BooksActivity;
import nytimes.chernousovaya.com.nytimes.controller.adapters.SectionItemAdapter;
import nytimes.chernousovaya.com.nytimes.model.Section;


public class SectionsBooksFragment extends Fragment {

    private static final String LOG = "SectionsBooksFragment";
    private static BooksAPI mBooksAPI;
    private static List<NameBooks> mSections;
    private ListView mListView;
    private SectionItemAdapter mSectionItemAdapter;

    public interface Listener {
        void onSectionClicked(NameBooks sectionName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBooksAPI = new BooksAPI();
        View view = inflater.inflate(R.layout.sections_fragment,
                container, false);

        mListView = view.findViewById(R.id.list_sections);
        mSections = BooksActivity.getSections();
        ArrayList<NameBooks> arraylist = new ArrayList<>();
        arraylist.addAll(mSections);

        ArrayList<Section> sections = namesOfBookToSections(mSections);
        mSectionItemAdapter = new SectionItemAdapter(this.getActivity(), sections);

        EditText editText = getActivity().findViewById(R.id.search);
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
                String text = editText.getText().toString().toLowerCase(Locale.getDefault());
                mSectionItemAdapter.filter(text);
            }
        });

        Spinner spinner = getActivity().findViewById(R.id.spinnerSorting);

        ArrayAdapter<?> adapter =
                ArrayAdapter.createFromResource(getActivity(), R.array.sort_sections, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.sort_sections);
                sortSections(choose[selectedItemPosition]);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mListView.setOnItemClickListener((parent, itemClicked, position, id) ->
        {
            SectionsBooksFragment.Listener l = (SectionsBooksFragment.Listener) getActivity();
            l.onSectionClicked(mSections.get(position));
        });

        mListView.setAdapter(mSectionItemAdapter);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void sortSections(String sort) {
        switch (sort) {
            case "By name": {
                mSectionItemAdapter.sortByName();
                break;
            }
            case "By newest date": {
                mSectionItemAdapter.sortByNewestDate();
                break;
            }
            case "By oldest date": {
                mSectionItemAdapter.sortByOldestDate();
            }
            default:
                break;
        }
        mListView.smoothScrollToPosition(0);
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
                e.printStackTrace();
            }
            Section section = new Section(name.getListName(), dateOldest, dateNewest);
            sections.add(section);
        }
        return sections;
    }
}
