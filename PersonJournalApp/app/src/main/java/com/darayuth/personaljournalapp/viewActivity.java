package com.darayuth.personaljournalapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.darayuth.personaljournalapp.contract.JournalContract;
import com.darayuth.personaljournalapp.model.Journal;
import com.darayuth.personaljournalapp.presenter.JournalPresenter;

public class viewActivity extends AppCompatActivity implements JournalContract.View, View.OnClickListener {

    private static final String TAG = "viewActivity";

    private EditText textArea;
    private Button updateBtn;
    private EditText textViewTitle;
    private JournalContract.Presenter mPresenter;
    private Journal journal;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        changeActionBarToHome();

        mPresenter = new JournalPresenter(this, this);

        inits();
        setEventListenerToButtons();
        display();

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
        textArea = (EditText) findViewById(R.id.editTextArea);
        textViewTitle = (EditText) findViewById(R.id.textViewTitle);
        updateBtn = (Button) findViewById(R.id.UpdateBtn);
    }

    public void setEventListenerToButtons(){
        updateBtn.setOnClickListener(this);
    }

    @Override
    public void display() {
        Intent intent = getIntent();

        journal = (Journal) intent.getSerializableExtra("journal");
        textViewTitle.append(journal.getTitle());
        //cocate string
        textArea.append(journal.getTextJournal());
    }

    /***
     *
     * @param v listen to when update button is clicked
     *
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id) {
            case R.id.UpdateBtn:
                //update text_journal, and title to database when the user press update button.
                journal.setTextJournal(textArea.getText().toString());
                journal.setTitle(textArea.getText().toString());
                mPresenter.UpdateJournal(journal);
                switchActivity();
                break;
        }


    }
    @Override
    public void switchActivity() {
        Intent intent = new Intent(this, adapterActivity.class);
        startActivity(intent);
    }
}
