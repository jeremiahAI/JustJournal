package com.example.jeremiahvaris.justjournal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class JournalEntryActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "REPLY";

    Toolbar toolbar;
    EditText mEntryContentView;
    FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entry);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_quill_drawing_a_line);

        mEntryContentView = findViewById(R.id.entry_content);
        actionButton = (FloatingActionButton) findViewById(R.id.entry_page_action_button);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEntryContentView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String entryContent = mEntryContentView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, entryContent);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });


        // Set title
//        if (getIntent().hasExtra(JournalEntry.TITLE_TAG)) {
//            TextView title = (TextView) toolbar.findViewById(R.id.title);
//            toolbar.setTitle(getIntent().getStringExtra(JournalEntry.TITLE_TAG));
//        } else toolbar.setTitle("Untitled");

    }
}
