package com.darayuth.personaljournalapp.connection;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.darayuth.personaljournalapp.dao.JournalDao;
import com.darayuth.personaljournalapp.model.Journal;


@Database(entities = Journal.class, exportSchema = false, version = 1)
public abstract class JournalDatabase extends RoomDatabase {

    private static final String DB_NAME = "journal_db";
    public static JournalDatabase instance;

    //create singleton for database;
    public static synchronized JournalDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), JournalDatabase.class,
                    DB_NAME).fallbackToDestructiveMigration().build();
        }
        return instance;
    }
    public abstract JournalDao userDao();
}
