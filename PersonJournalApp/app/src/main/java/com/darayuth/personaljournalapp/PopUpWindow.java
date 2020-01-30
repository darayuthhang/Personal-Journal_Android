package com.darayuth.personaljournalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.darayuth.personaljournalapp.contract.JournalContract;
import com.darayuth.personaljournalapp.exception.InputExeception;
import com.darayuth.personaljournalapp.model.Journal;
import com.darayuth.personaljournalapp.presenter.JournalPresenter;

import java.util.List;
import java.util.regex.Pattern;

/*** PopUpwindow activity ****/
public class PopUpWindow extends AppCompatActivity implements JournalContract.View,CalendarView.OnDateChangeListener, View.OnClickListener {
    private static final String TAG = "PopUpWindow";

    private CalendarView calendarView;
    private Button btnSubmit;
    private String date = "" ;
    private EditText editTextTitle;
    private JournalContract.Presenter mPresenter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_window);
        changeActionBarToHome();
        mPresenter = new JournalPresenter(this, getApplicationContext());
        resizeWindow();
        inits();
        setAllEventListerner();
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

    /**
     * inits inits is method use to get all the id for UI.
     */
    public void inits(){
        calendarView = (CalendarView) findViewById(R.id.calendarViewCalendar);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
    }

    /**
     * setAllEventListerner setAllEventListerner is to use give all the event listener to UI.
     */
    public void setAllEventListerner(){
        calendarView.setOnDateChangeListener(this);
        btnSubmit.setOnClickListener(this);
    }
    /*****
     * resizeWindow resizeWindow is use to create resize the activity into the pop up window.
     */
    public void resizeWindow(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.6));

    }

    /***
     * displayError displayError is used to show of the error of the app.
     */
    @Override
    public void display() {

    }
    /**
     *
     *
     * @param year year is the year of calendar Journal
     * @param month months of the calendar Journal
     * @param dayOfMonth dayofMonth of the calendar Journal
     */
    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth){
            int MONTH = month + 1;
            date = MONTH + "/" + dayOfMonth + "/" + year;

    }

    /***
     * onClick onclick is use to listen to event when the users click.
     * @param v v is use to get id when the user use the event.
      */
    @Override
    public void onClick(View v) {


        int ok = v.getId();
        switch(ok){
            case R.id.btnSubmit:
                /*** verify input if the input is empty or consist of special letters
                 * then , the program will catch the inputExeception .
                 */
                try{
                    String title = verifyInput();
                    //if the date is not clicked by the user
                    // set the date to empty .
                    if(date == null || date.equals("")){
                        date = " ";
                    }
                    //check for duplicate
                    Boolean flag = checkForDuplicate(title);
                    if(flag == false){
                        showCustomToast("Duplicate Title!");
                        //Toast.makeText(getApplicationContext(), "Title Cannot be the same", Toast.LENGTH_LONG).show();
                    }else{
                        switchActivity();
                    }
                }catch (InputExeception e){
                    showCustomToast(e.getMessage());
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }

    public void showCustomToast(String titleText){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_container,
                (ViewGroup) findViewById(R.id.custom_toast));
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(titleText);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }


    /***
     * checkForDuplicate checkForDuplicate check for duplcate and return true when there is no
     * duplcate value inside database.
     * @param title title is used to check for duplcate title if title is duplcate
     * @return false or else return true
     */
    public Boolean checkForDuplicate(String title){
        // select * from journal.
        List<Journal> journalList = mPresenter.getAllProducts();
        //loop through items in databases.
        for(int i=0;i<journalList.size();i++){
            if(title.equalsIgnoreCase(journalList.get(i).getTitle())){
                return false;
            }
        }
        return true;
    }
    /***
     * verifyInput verifyInput is to make sure that the input in editText Title
     * cannot be number;
     */

    public String verifyInput() throws InputExeception {
        String regex = "[a-zA-z0-9\\s,]+";
        Pattern pattern = Pattern.compile(regex);
        //title input
        String input = editTextTitle.getText().toString();
        //if titile is either empty or consist anything beside alphabet
        // throw the exception.
        if(!pattern.matcher(input).matches()){
            throw new InputExeception("No Special Letter!");
        }
        return input;
    }

    /*****
     * switch Activity switchActivity is method swithch activity from main activity
     * and pass title, date argument to JournalActivity.
     */
    @Override
    public void switchActivity() {
        Intent intent = new Intent(this, journalActivity.class);
        intent.putExtra("title", editTextTitle.getText().toString());
        intent.putExtra("date", date);
        startActivity(intent);
    }





}
