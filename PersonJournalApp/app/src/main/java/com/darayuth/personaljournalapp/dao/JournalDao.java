package com.darayuth.personaljournalapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.darayuth.personaljournalapp.model.Journal;

import java.util.List;

@Dao
public interface JournalDao {

    @Delete
    void deleteJournal(Journal journal);
    @Query("select * from journal")
     List<Journal> getAllJournal();
    @Insert
    void insert(Journal journal);

    @Update
    void updateJournal(Journal journal);


}
