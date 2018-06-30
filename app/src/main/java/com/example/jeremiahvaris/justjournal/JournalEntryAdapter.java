package com.example.jeremiahvaris.justjournal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class JournalEntryAdapter extends RecyclerView.Adapter<JournalEntryAdapter.EntryViewHolder> {
    ArrayList<JournalEntry> mEntries;
    EntryClickListener mEntryClickListener;

    JournalEntryAdapter(ArrayList<JournalEntry> entries, EntryClickListener EntryClickListener) {
        mEntries = entries;
        mEntryClickListener = EntryClickListener;
    }


    class EntryViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView mTitle, mContentSummary, mDateCreated, mLastModified;


        public EntryViewHolder(View itemView) {
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
        holder.mTitle.setText(mEntries.get(position).getTitle());
        // Todo: change content to content summary
        holder.mContentSummary.setText(mEntries.get(position).getContent());
    }


    @Override
    public int getItemCount() {
        return mEntries.size();
    }

    public interface EntryClickListener {
        void onEntryClicked(int index);
    }
}
