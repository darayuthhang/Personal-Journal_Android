package com.darayuth.personaljournalapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.darayuth.personaljournalapp.R;
import com.darayuth.personaljournalapp.adapterActivity;
import com.darayuth.personaljournalapp.contract.JournalContract;
import com.darayuth.personaljournalapp.model.Journal;
import com.darayuth.personaljournalapp.presenter.JournalPresenter;
import com.darayuth.personaljournalapp.viewActivity;

import java.io.Serializable;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Journal> implements JournalContract.View {
    private Context mContext;
    private List<Journal> journals;
    private ViewHolder viewHolder;
    private JournalPresenter mPresenter;
    private static final String TAG = "CustomAdapter";
    private adapterActivity adapterActivity;
    private Journal journal;



    public CustomAdapter(Context context, List<Journal> journals){
        super(context, 0, journals);
        this.mContext = context;
        this.journals = journals;
        mPresenter = new JournalPresenter(this, context);


    }


    @Override
    public void display() {

    }

    @Override
    public void switchActivity() {

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;

        journal = getItem(position);

        if(row == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            row = inflater.inflate(R.layout.lists, parent, false);
            viewHolder = new ViewHolder(row);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) row.getTag();
        }

        // cocat string
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(journal.getTitle());
        stringBuilder.append(":");
        stringBuilder.append(journal.getDate());

        viewHolder.textViewTitle.setText(stringBuilder.toString());
        //delete button
        viewHolder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                journal = getItem(position);
                mPresenter.deleteJournal(journal);
                //remove item from list view
                journals.remove(journal);
                notifyDataSetChanged();
                //delete(pos);
                //get all data from database
            }
        });

        //edit button

        viewHolder.editBtn.setOnClickListener((v) -> {
            journal = getItem(position);
            Intent intent = new Intent(mContext, viewActivity.class);

//            intent.putExtra("text_journal", journal.getTextJournal());
//            intent.putExtra("title", journal.getTitle());
//            intent.putExtra("date", journal.getDate());

            intent.putExtra("journal", journal);
            mContext.startActivity(intent);
        });

        return row;
    }


    private class ViewHolder{
        final TextView textViewTitle, textViewDate;
        final Button delBtn, editBtn;
        ViewHolder(View v){
            textViewTitle = v.findViewById(R.id.textViewTitle);
            textViewDate = v.findViewById(R.id.textViewDate);
            delBtn = v.findViewById(R.id.deleteBtn);
            editBtn = v.findViewById(R.id.editBtn);
        }
    }
}