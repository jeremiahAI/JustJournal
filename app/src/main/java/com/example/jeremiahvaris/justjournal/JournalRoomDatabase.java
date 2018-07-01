package com.example.jeremiahvaris.justjournal;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {JournalEntry.class}, version = 3)
public abstract class JournalRoomDatabase extends RoomDatabase {

    public abstract EntryDao entryDao();

    private static JournalRoomDatabase INSTANCE;

    public static JournalRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (JournalRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            JournalRoomDatabase.class, "journal_database")
//                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Callback called when database is opened to populate the data with one item.
     * For testing purposes only
     * Todo: delete initial callback
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new ClearDbAsyncTask(INSTANCE).execute();
                }
            };

    private static class ClearDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private final EntryDao mDao;

        ClearDbAsyncTask(JournalRoomDatabase database) {
            mDao = database.entryDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.deleteAll();
            return null;
        }
    }
}
