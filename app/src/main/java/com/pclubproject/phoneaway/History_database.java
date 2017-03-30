package com.pclubproject.phoneaway;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

/**
 * Created by Anubhav Mittal on 25-06-2016.
 */
public class History_database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="list_database";
    public static final String TABLE_NAME="message_log";
    public static final String COLUMN_INDEX="_id";
    public static final String COLUMN_TITLE="title";
    public static final String COLUMN_RECEIVED_MESSAGE="received_message";
    public static final String COLUMN_SENT_MESSAGE="sent_message";
    public History_database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+
                "( "+COLUMN_INDEX+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    COLUMN_TITLE+" TEXT,"+
                    COLUMN_RECEIVED_MESSAGE+ " TEXT,"+
                    COLUMN_SENT_MESSAGE+" TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+";");
        onCreate(db);
    }

    public long insert(String title,String recmessage,String sentmessage)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_TITLE,title);
        contentValues.put(COLUMN_RECEIVED_MESSAGE,recmessage);
        contentValues.put(COLUMN_SENT_MESSAGE,sentmessage);
        long result=db.insert(TABLE_NAME,null,contentValues);
        return result;
    }

    public Cursor getData()
    {
        SQLiteDatabase db=this.getWritableDatabase();

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLE_NAME);
        String orderby=COLUMN_INDEX+" desc";
        String columns[]={COLUMN_INDEX,COLUMN_TITLE,COLUMN_RECEIVED_MESSAGE,COLUMN_SENT_MESSAGE};

        Cursor cursor=qb.query(db,columns,null,null,null,null,orderby);

        // Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE_NAME+";",null);
        return cursor;
    }
}
