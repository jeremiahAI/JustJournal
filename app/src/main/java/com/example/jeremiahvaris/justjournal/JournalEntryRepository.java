package com.example.jeremiahvaris.justjournal;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class JournalEntryRepository {
    private EntryDao mEntryDao;
    private LiveData<List<JournalEntry>> mAllEntries;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mEntriesDatabaseReference;
    private ChildEventListener mChildEventListener;

    JournalEntryRepository(Application application) {
        JournalRoomDatabase database = JournalRoomDatabase.getDatabase(application);
        mEntryDao = database.entryDao();
        mAllEntries = mEntryDao.getAllEntries();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        // Todo: Sync cloud data with local storage
        mEntriesDatabaseReference = mFirebaseDatabase.getReference().child("entries");

        // Read from the FirebaseDatabase
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mEntriesDatabaseReference.addChildEventListener(mChildEventListener);
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
