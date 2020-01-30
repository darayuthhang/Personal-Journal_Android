package com.darayuth.personaljournalapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import com.darayuth.personaljournalapp.contract.JournalContract;
import com.darayuth.personaljournalapp.model.Journal;
import com.darayuth.personaljournalapp.presenter.JournalPresenter;

public class journalActivity extends AppCompatActivity implements JournalContract.View, View.OnClickListener {

    private static final String TAG = "journalActivity";
    private Toolbar toolbar;
    private EditText textArea;
    private Button saveBtn;
    private Journal journal;
    private JournalContract.Presenter mPrsenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        changeActionBarToHome();
        inits();
        setAllEventListerner();
        //create the instance of presenter, and pass current state context, and jounra
        //database instance as the arugment.
        mPrsenter = new JournalPresenter(this, getApplicationContext());
    }


    public void changeActionBarToHome(){
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_action_name);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    private void setSupportActionBar(Toolbar toolbar) {
    }
    /****
     * inits inits is the method to assign the id to ui.
     */
    public void inits(){
        textArea = (EditText) findViewById(R.id.editTextTextArea);
        saveBtn = (Button) findViewById(R.id.saveBtn);
    }


    /**
     * setAllEventListerner setAllEventListerner is to use give all the event listener to UI.
     */
    public void setAllEventListerner(){
        saveBtn.setOnClickListener(this);
    }
    @Override
    public void display() {

    }
    /***
     * onClick onclick is use to listen to event when the users click.
     * @param v v is use to get id when the user use the event.
     */
    @Override
    public void onClick(View v) {
        int ok = v.getId();
        switch(ok){
            case R.id.saveBtn:
                String textAreaJournal = textArea.getText().toString();
                getAllInput(textAreaJournal);
                switchActivity();
                break;
        }
    }

    public void getAllInput(String textAreaJournal){
        //get data from PopUpWinDow class
        Intent getIntent = getIntent();
        /***
         * get the three param from PopUpWinDowClass
         * @param title
         * @param date
         */
        String title = getIntent.getStringExtra("title");
        String date = getIntent.getStringExtra("date");
        Log.d(TAG, "getDataFromJournalClass: "+ textAreaJournal);
        //create the journal instance.
        journal = new Journal(date, title, textAreaJournal);

        //insert data into database using presenter
        mPrsenter.insertData(journal);
    }
    /*
     * switchActivity switchActivity will switch the activity from jounalActivity to
     * JournalPresenter and pass three arguement (title, date, textAreaJournal).
     */
    @Override
    public void switchActivity() {
        Intent intent = new Intent(this, adapterActivity.class);
        startActivity(intent);
    }

}
