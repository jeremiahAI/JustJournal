package com.example.jeremiahvaris.justjournal;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class JournalEntryRepository {
    private EntryDao mEntryDao;
    private LiveData<List<JournalEntry>> mAllEntries;

    JournalEntryRepository(Application application) {
        JournalRoomDatabase database = JournalRoomDatabase.getDatabase(application);
        mEntryDao = database.entryDao();
        mAllEntries = mEntryDao.getAllEntries();
    }

    LiveData<List<JournalEntry>> getAllEntries() {
        return mAllEntries;
    }

    public void insert(JournalEntry... entries) {
        new insertAsyncTask(mEntryDao).execute(entries);
    }

    private static class insertAsyncTask extends AsyncTask<JournalEntry, Void, Void> {

        private EntryDao mAsyncTaskDao;

        insertAsyncTask(EntryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(JournalEntry... journalEntries) {
            mAsyncTaskDao.insert(journalEntries);
            return null;
        }
    }
}
