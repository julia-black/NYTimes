package nytimes.chernousovaya.com.nytimes.controller.adapters;


import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import nytimes.chernousovaya.com.nytimes.R;
import nytimes.chernousovaya.com.nytimes.model.Section;

public class SectionItemAdapter extends BaseAdapter {

    private List<Section> mListSection;
    private ArrayList<Section> mArrayList = new ArrayList<>();
    private final Context mContext;
    private final LayoutInflater mInflater;

    public SectionItemAdapter(Context context, List<Section> data) {
        this.mContext = context;
        this.mListSection = data;
        this.mInflater = LayoutInflater.from(context);
        this.mArrayList.addAll(data);
    }

    @Override
    public int getCount() {
        return mListSection.size();
    }

    @Override
    public Object getItem(int i) {
        return mListSection.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Section section = (Section) getItem(i);
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_section, null);
            holder = new SectionItemAdapter.ViewHolder();
            holder.mNameView = view.findViewById(R.id.name);
            holder.mNewestDateView = view.findViewById(R.id.newestDate);
            holder.mOldestDateView = view.findViewById(R.id.oldestDate);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mNameView.setText(section.getmName());

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        holder.mNewestDateView.setText("Newest published date: " + format.format(section.getmNewestDate()));
        holder.mOldestDateView.setText("Oldest published date: " + format.format(section.getmOldestDate()));

        return view;
    }

    public void filter(String searchText) {
        searchText = searchText.toLowerCase(Locale.getDefault());
        mListSection.clear();
        if (searchText.length() == 0) {
            mListSection.addAll(mArrayList);
        } else {
            for (Section section : mArrayList) {
                if (section.getmName().toLowerCase(Locale.getDefault()).contains(searchText))
                    mListSection.add(section);
            }
        }
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortByName() {
        mListSection.sort((section, t1) -> section.getmName().compareTo(t1.getmName()));
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortByNewestDate() {
        mListSection.sort((section, t1) -> section.getmNewestDate().compareTo(t1.getmNewestDate()));
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortByOldestDate() {
        mListSection.sort((section, t1) -> section.getmOldestDate().compareTo(t1.getmOldestDate()));
        notifyDataSetChanged();
    }

    static class ViewHolder {
        TextView mNameView;
        TextView mOldestDateView;
        TextView mNewestDateView;
    }
}
