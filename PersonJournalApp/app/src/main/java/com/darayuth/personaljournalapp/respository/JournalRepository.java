package com.darayuth.personaljournalapp.respository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.darayuth.personaljournalapp.connection.JournalDatabase;
import com.darayuth.personaljournalapp.dao.JournalDao;
import com.darayuth.personaljournalapp.model.Journal;

import java.util.List;

/*** repository class store crudd operation ****/
public class JournalRepository {
    private JournalDatabase journalDatabase;
    private Context context;
    private JournalDao journalDao;

    private static final String TAG = "JournalRepository";

    ///create JournalRepository Instance ///
    public JournalRepository(Context context){
        journalDatabase = JournalDatabase.getInstance(context);
        journalDao = journalDatabase.userDao();
    }

    public JournalDao getJournalDao() {
        return journalDao;
    }

    //*** class implementation of InsertTask ********/
    private static class InsertTask extends AsyncTask<Journal, Void, Void> {

        private JournalDao insertDao;

        InsertTask(JournalDao journalDao){
            this.insertDao = journalDao;
        }
        @Override
        protected Void doInBackground(Journal... journals) {
            this.insertDao.insert(journals[0]);
            return null;
        }
    }
    //*** class implementation of select * all task ********/
    private class getAllTask extends AsyncTask<Void, Void, List<Journal>>{

        @Override
        protected List<Journal> doInBackground(Void... voids) {
            return journalDatabase.userDao().getAllJournal();
        }
    }
    //*** class implementation of delete task ********/
    private class DeleteTask extends AsyncTask<Journal, Void, Void>{
        private JournalDao JournalDao;
        DeleteTask(JournalDao journalDao){
            this.JournalDao = journalDao;
        }

        @Override
        protected Void doInBackground(Journal... journals) {
            this.JournalDao.deleteJournal(journals[0]);
            return null;
        }
    }
    //*** class implementation of update task ********/
    private class UpdateTask extends AsyncTask<Journal, Void, Void>{
        private JournalDao journalDao;

        UpdateTask(JournalDao journalDao){
            this.journalDao = journalDao;
        }

        @Override
        protected Void doInBackground(Journal... journals) {
            this.journalDao.updateJournal(journals[0]);
            return null;
        }
    }

    public void UpdateJournal(Journal journal){
        UpdateTask updateTask = new UpdateTask(journalDao);
        updateTask.execute(journal);
    }

    public void InsertJournal(Journal journal){
        InsertTask insertTask = new InsertTask(journalDao);
        insertTask.execute(journal);
    }

    public void deleteJournal(Journal journal){
        DeleteTask deleteTask = new DeleteTask(journalDao);
        deleteTask.execute(journal);
    }

    public List<Journal> getAllJournal(){
        try{
            return new getAllTask().execute().get();
        }catch (Exception e){ }
        return null;

    }
    public void closeDb(){
        journalDatabase.close();
    }
}
