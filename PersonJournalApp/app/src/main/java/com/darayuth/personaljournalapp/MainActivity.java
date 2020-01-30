package com.darayuth.personaljournalapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import com.darayuth.personaljournalapp.contract.JournalContract;
import com.darayuth.personaljournalapp.presenter.JournalPresenter;

/****
 * Main activity of personal Journal App
 */
public class MainActivity extends AppCompatActivity implements  View.OnClickListener {
    private Button createBtn, viewBtn;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeActionBarToHome();
        inits();
    }

    public void changeActionBarToHome(){
        //get the id of the toolbar
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
        createBtn = (Button) findViewById(R.id.btnCreate);
        viewBtn = (Button) findViewById(R.id.btnView);
        //assign event click listener to createbutton
        createBtn.setOnClickListener(this);
        viewBtn.setOnClickListener(this);
    }

    /***
     * Onclick onclick is the method event listening for view.
     * @param v  v is to get the id of event when the user press click.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnCreate:
                switchActivity();
                break;
            case R.id.btnView:
                swithchToView();
                break;
        }
    }

    public void swithchToView(){
        Intent intent = new Intent(this, adapterActivity.class);
        startActivity(intent);
    }
    /*****
     * switch Activity switchActivity is method swithch activity from main activity
     * to JournalActivity.
     */
    public void switchActivity() {
        Intent intent = new Intent(this, PopUpWindow.class);
        startActivity(intent);
    }
}
