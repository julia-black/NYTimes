package nytimes.chernousovaya.com.nytimes.controller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import nytimes.chernousovaya.com.apinytimes.BooksAPI;
import nytimes.chernousovaya.com.apinytimes.model.NameBooks;
import nytimes.chernousovaya.com.nytimes.R;


import java.util.ArrayList;
import java.util.List;

public class SectionsBooksFragment extends Fragment {


    private BooksAPI mBooksAPI;

    public interface Listener {
        void onSectionClicked(String sectionName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBooksAPI = new BooksAPI();

        View view = inflater.inflate(R.layout.sections_fragment,
                container, false);

        ListView listView = view.findViewById(R.id.list_sections);

        final ArrayList<String> sections = getSections();
        ArrayAdapter adapter = new ArrayAdapter(this.getActivity(),R.layout.section, sections);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, itemClicked, position, id) ->
                {
                    SectionsBooksFragment.Listener l = (SectionsBooksFragment.Listener) getActivity();
                    l.onSectionClicked(getSections().get(position));
                });


        return view;
    }

    private ArrayList<String> getSections(){
        ArrayList<String> sections = new ArrayList<>();
        List<NameBooks> nameBooksList = mBooksAPI.getNamesBooks();
        for(NameBooks nameBooks : nameBooksList){
            sections.add(nameBooks.getListName());
        }
        return  sections;

    }
}
