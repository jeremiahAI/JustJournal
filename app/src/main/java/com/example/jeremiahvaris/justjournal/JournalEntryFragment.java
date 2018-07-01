package com.example.jeremiahvaris.justjournal;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass for displaying and editing a Journal Entry
 */
public class JournalEntryFragment extends Fragment {

    EditText mEntryContentView;
    FloatingActionButton actionButton;
    private boolean mEditState;
    // Todo: implement title setting;
    private String mTitle = "Untitled";
    public static final String ENTRY = "Entry details";
    JournalEntriesViewModel mJournalEntriesViewModel;
    private JournalEntry journalEntry = null;


    public JournalEntryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            if (args.containsKey(ENTRY)) {
                mEditState = args.containsKey(ENTRY);
                journalEntry = (JournalEntry) args.getSerializable(ENTRY);
            }

        } else mEditState = false;


        mJournalEntriesViewModel = ViewModelProviders.of(getActivity())
                .get(JournalEntriesViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_journal_entry, container, false);
        mEntryContentView = view.findViewById(R.id.entry_content);

        actionButton = view.findViewById(R.id.entry_page_action_button);
        if (mEditState) {
            // Set the button to the edit option
            actionButton.setImageResource(R.drawable.ic_quill_drawing_a_line);
            actionButton.setOnClickListener(editClickListener);
            mEntryContentView.setText(journalEntry.getContent());

        } else {// Set the button to the save option
            actionButton.setImageResource(R.drawable.ic_done_white_24dp);
            actionButton.setOnClickListener(saveClickListener);
        }


        return view;
    }

    View.OnClickListener saveClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (journalEntry == null) { // Create new entry
                journalEntry = new JournalEntry(mTitle,
                        mEntryContentView.getText().toString());
            } else {
                journalEntry.setTitle(mTitle);
                journalEntry.setContent(mEntryContentView.getText().toString());
            }
            mJournalEntriesViewModel.insert(journalEntry);
            Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
        }
    };

    View.OnClickListener editClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mEntryContentView.requestFocus();
            actionButton.setImageResource(R.drawable.ic_done_white_24dp);
            actionButton.setOnClickListener(saveClickListener);
        }
    };

}
