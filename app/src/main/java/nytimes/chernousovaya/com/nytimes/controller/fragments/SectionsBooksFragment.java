package nytimes.chernousovaya.com.nytimes.controller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import nytimes.chernousovaya.com.apinytimes.BooksAPI;
import nytimes.chernousovaya.com.nytimes.R;
import nytimes.chernousovaya.com.nytimes.controller.activities.BooksActivity;


public class SectionsBooksFragment extends Fragment {

    private static final String LOG = "SectionsBooksFragment";
    private static BooksAPI mBooksAPI;
    private static List<String> mSections;

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
        ArrayAdapter adapter;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mSections = BooksActivity.getSections();
        adapter = new ArrayAdapter(this.getActivity(), R.layout.section, mSections);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, itemClicked, position, id) ->
        {
            SectionsBooksFragment.Listener l = (SectionsBooksFragment.Listener) getActivity();
            l.onSectionClicked(mSections.get(position));
        });
        return view;
    }
}
