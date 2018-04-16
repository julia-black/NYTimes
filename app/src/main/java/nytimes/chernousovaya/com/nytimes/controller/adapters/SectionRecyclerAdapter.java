package nytimes.chernousovaya.com.nytimes.controller.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nytimes.chernousovaya.com.nytimes.R;
import nytimes.chernousovaya.com.nytimes.model.Section;

public class SectionRecyclerAdapter extends RecyclerView.Adapter<SectionRecyclerAdapter.ViewHolder> {

    private static final String LOG = SectionRecyclerAdapter.class.getSimpleName();
    private List<Section> mListSection;

    public SectionRecyclerAdapter(List<Section> listSection) {
        this.mListSection = listSection;
    }

    @Override
    public SectionRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_section, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mNameOfSection.setText(mListSection.get(position).getmName());
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        holder.mNewestDateView.setText("Newest published date: " + format.format(mListSection.get(position).getmNewestDate()));
        holder.mOldestDateView.setText("Oldest published date: " + format.format(mListSection.get(position).getmOldestDate()));
    }

    @Override
    public int getItemCount() {
        return mListSection.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView mNameOfSection;

        @BindView(R.id.newestDate)
        TextView mNewestDateView;

        @BindView(R.id.oldestDate)
        TextView mOldestDateView;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}