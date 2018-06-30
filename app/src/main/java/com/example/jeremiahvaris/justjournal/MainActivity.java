package com.example.jeremiahvaris.justjournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements JournalEntryAdapter.EntryClickListener {

    RecyclerView mEntriesList;
    JournalEntryAdapter mAdapter;
    ArrayList<JournalEntry> mEntries;


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
        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));
        mEntries.add(new JournalEntry("Untitled", JournalEntry.LOREM_IPSUM));


        mAdapter = new JournalEntryAdapter(mEntries, this);
        mEntriesList.setAdapter(mAdapter);


    }

    @Override
    public void onEntryClicked(int index) {
        // Todo: Go to Edit Activity
        Toast.makeText(this, "Item " + index + 1 + " clicked", Toast.LENGTH_SHORT).show();
    }
}
