package com.example.jeremiahvaris.justjournal;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import java.util.List;

@Dao
public interface EntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insert(JournalEntry... entries);

    @Delete
    int deleteEntries(JournalEntry... entries);


    @Query("SELECT * from entries_table ORDER BY `Date Modified` ASC")
    LiveData<List<JournalEntry>> getAllEntries();

    @Query("DELETE FROM entries_table")
    void deleteAll();


}
