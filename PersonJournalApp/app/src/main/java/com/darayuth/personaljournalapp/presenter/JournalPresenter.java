package com.darayuth.personaljournalapp.presenter;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.darayuth.personaljournalapp.contract.JournalContract;
import com.darayuth.personaljournalapp.dao.JournalDao;
import com.darayuth.personaljournalapp.model.Journal;
import com.darayuth.personaljournalapp.respository.JournalRepository;

import java.util.List;

/*****concrete implementation class ****/
public class JournalPresenter implements JournalContract.Presenter {
    private static final String TAG = "JournalPresenter";
    private JournalContract.View journalView;
    private JournalRepository journalRepository;
    private JournalDao journalDao;
    private List<Journal> journalList;
    ;
    /**** create instance of journal presenter ***/
    public JournalPresenter(JournalContract.View journalView, Context context) {
        this.journalView = journalView;
        //create the instance of the JounalRepositroy
        // so that we can perform crudd.
        journalRepository = new JournalRepository(context);
    }

    // insert into database ;
    @Override
    public void insertData(Journal journal) {
        journalRepository.InsertJournal(journal);
    }

    //select all from database;
    @Override
    public List<Journal> getAllProducts() {
        List<Journal> list = journalRepository.getAllJournal();
        return list;
    }
    //delete the row

    @Override
    public void deleteJournal(Journal journal) {
        journalRepository.deleteJournal(journal);
    }

    //update jourma;
    @Override
    public void UpdateJournal(Journal journal) {
        journalRepository.UpdateJournal(journal);
    }
}
