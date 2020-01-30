package com.darayuth.personaljournalapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toolbar;

import com.darayuth.personaljournalapp.Adapter.CustomAdapter;
import com.darayuth.personaljournalapp.connection.JournalDatabase;
import com.darayuth.personaljournalapp.contract.JournalContract;
import com.darayuth.personaljournalapp.model.Journal;
import com.darayuth.personaljournalapp.presenter.JournalPresenter;
import com.darayuth.personaljournalapp.respository.JournalRepository;

import java.util.List;

public class adapterActivity extends AppCompatActivity implements JournalContract.View{

    private JournalDatabase journalDatabase;
    private JournalContract.Presenter journalPresnter;
    private static final String TAG = "adapterActivity";
    private JournalRepository journalRepository;
    private JournalContract.Presenter mPresenter;
    private ListView listView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter);
        changeActionBarToHome();
        mPresenter = new JournalPresenter(this, getApplicationContext());

        //get all data from database
        final List<Journal> list = mPresenter.getAllProducts();

        CustomAdapter adapter = new CustomAdapter(this, list);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
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




    @Override
    public void display() {

    }

    @Override
    public void switchActivity() {

    }
}
