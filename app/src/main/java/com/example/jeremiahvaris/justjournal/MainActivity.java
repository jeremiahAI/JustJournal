package com.example.jeremiahvaris.justjournal;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements JournalEntryAdapter.EntryClickListener {

    RecyclerView mEntriesList;
    JournalEntryAdapter mAdapter;
    List<JournalEntry> mEntries;
    FloatingActionButton newEntryButton;
    JournalEntryViewModel mJournalEntryViewModel;
    static final int JOURNAL_ENTRY_ACTIVITY_REQUEST_CODE = 1;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == JOURNAL_ENTRY_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            JournalEntry entry =
                    new JournalEntry("Untitled",
                            data.getStringExtra(JournalEntryActivity.EXTRA_REPLY));
            Toast.makeText(this, entry.getContentSummary(), Toast.LENGTH_LONG).show();
            mJournalEntryViewModel.insert(entry);
        } else {
            Toast.makeText(getApplicationContext(), "Entry empty, not saved", Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEntriesList = (RecyclerView) findViewById(R.id.entries_rv);
        mEntriesList.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mEntriesList.setLayoutManager(layoutManager);

        mEntries = new ArrayList<>();
        // Todo: get entries from database
//        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
//        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
//        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
//        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
//        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
//        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
//        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
//        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
//        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
//        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
//        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
//        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));

        // Todo: Add view for empty state
        mAdapter = new JournalEntryAdapter(this);
        mEntriesList.setAdapter(mAdapter);

        //Todo: credit authors
        /**Quill icon made by Freepik (https://www.flaticon.com/authors/freepik)
         *
         */

        mJournalEntryViewModel = ViewModelProviders.of(this)
                .get(JournalEntryViewModel.class);

        // Observe changes to the Journal Database
        mJournalEntryViewModel.getAllEntries().observe(this, new Observer<List<JournalEntry>>() {
            @Override
            public void onChanged(@Nullable List<JournalEntry> journalEntries) {
                mEntries = mJournalEntryViewModel.getAllEntries().getValue();
                mAdapter.setEntries(mEntries);
                Toast.makeText(MainActivity.this, "Entries changed", Toast.LENGTH_LONG).show();
            }
        });


        newEntryButton = (FloatingActionButton) findViewById(R.id.new_entry_button);
        newEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newEntry = new Intent(MainActivity.this, JournalEntryActivity.class);
                startActivityForResult(newEntry, JOURNAL_ENTRY_ACTIVITY_REQUEST_CODE);
            }
        });


    }

    @Override
    public void onEntryClicked(int index) {
        // Todo: Go to Edit Activity
        Toast.makeText(this, "Item " + (index + 1) + " clicked", Toast.LENGTH_SHORT).show();
    }
}
