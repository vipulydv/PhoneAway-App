package com.pclubproject.phoneaway;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class Message_log extends ListActivity {

    History_database history_database;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_log);

        initList();
    }

    public void initList()
    {
        //listView=(ListView)findViewById(R.id.message_list);

        history_database=new History_database(this);
        Cursor cursor=history_database.getData();

        String columns[]={History_database.COLUMN_TITLE};
        int to[]={R.id.message_title};

        SimpleCursorAdapter adapter;
        adapter=new SimpleCursorAdapter(this,R.layout.list_view_layout,cursor,columns,to,0);

        //listView.setAdapter(adapter);

        this.setListAdapter(adapter);
    }
}
