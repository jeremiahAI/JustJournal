package com.example.jeremiahvaris.justjournal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class JournalEntryAdapter extends RecyclerView.Adapter<JournalEntryAdapter.EntryViewHolder> {
    private List<JournalEntry> mEntries;
    private EntryClickListener mEntryClickListener;


    JournalEntryAdapter(EntryClickListener EntryClickListener) {
        mEntryClickListener = EntryClickListener;
    }


    class EntryViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView mTitle, mContentSummary, mDateCreated, mLastModified;


        EntryViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.entry_title);
            mContentSummary = itemView.findViewById(R.id.entry_summary);
            mDateCreated = itemView.findViewById(R.id.created_date);
            mLastModified = itemView.findViewById(R.id.last_modified_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mEntryClickListener.onEntryClicked(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.journal_entry, parent, false);

        return new EntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntryViewHolder holder, int position) {
        if (mEntries != null) {
            holder.mTitle.setText(mEntries.get(position).getTitle());
            holder.mContentSummary.setText(mEntries.get(position).getContentSummary());
            holder.mLastModified.setText(mEntries.get(position).getDateLastModifiedString());
            holder.mDateCreated.setText(mEntries.get(position).getDateCreatedString());
        } else {
            holder.mTitle.setText("");
            holder.mContentSummary.setText("Add new entry...");
            holder.mLastModified.setText("");
            holder.mDateCreated.setText("");
        }
    }

    public void setEntries(List<JournalEntry> entries) {
        this.mEntries = entries;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mEntries != null) return mEntries.size();
        else return 0;
    }

    public interface EntryClickListener {
        void onEntryClicked(int index);
    }
}
