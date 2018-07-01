package com.example.jeremiahvaris.justjournal;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.util.List;

public class JournalEntriesViewModel extends AndroidViewModel {
    private JournalEntryRepository mEntryRepository;
    private LiveData<List<JournalEntry>> mAllEntries;

    public JournalEntriesViewModel(@NonNull Application application) {
        super(application);
        mEntryRepository = new JournalEntryRepository(application);
        mAllEntries = mEntryRepository.getAllEntries();
    }

    public LiveData<List<JournalEntry>> getAllEntries() {
        return mAllEntries;
    }

    // Wrapper for the repository insert function.
    // Effectively hides the implementation of the insert operation from the UI
    public void insert(JournalEntry... entries) {
        mEntryRepository.insert(entries);
    }
}
