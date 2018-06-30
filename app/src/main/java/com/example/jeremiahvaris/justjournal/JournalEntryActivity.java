package com.example.jeremiahvaris.justjournal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.Set;

public class JournalEntryActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entry);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_quill_drawing_a_line);


        // Set title
        if (getIntent().hasExtra(JournalEntry.TITLE_TAG)) {
            TextView title = (TextView) toolbar.findViewById(R.id.title);
            toolbar.setTitle(getIntent().getStringExtra(JournalEntry.TITLE_TAG));
        } else toolbar.setTitle("Untitled");
    }
}
