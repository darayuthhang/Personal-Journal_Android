package com.darayuth.personaljournalapp.contract;

import com.darayuth.personaljournalapp.model.Journal;

import java.util.List;

public interface JournalContract {
    interface View{

        /***
         * displayError displayError is used to show of the error of the app.
         */
        void display();
        /*****
         * switch Activity switchActivity is method swithch activity from main activity
         * to JournalActivity.
         */
        void switchActivity();
    }

    interface Presenter{
        void insertData(Journal journal);

        List<Journal> getAllProducts();

        void deleteJournal(Journal journal);

        void UpdateJournal(Journal journal);
    }
}
