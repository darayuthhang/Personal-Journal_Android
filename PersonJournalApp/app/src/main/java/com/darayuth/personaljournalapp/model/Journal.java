package com.darayuth.personaljournalapp.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Journal implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "text_journal")
    private String textJournal;
    @ColumnInfo(name = "date")
    private String date;


    public Journal(){};
    @Ignore
    public Journal( String date, String title, String textJournal) {
        this.title = title;
        this.textJournal = textJournal;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextJournal() {
        return textJournal;
    }

    public void setTextJournal(String textJournal) {
        this.textJournal = textJournal;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
}
