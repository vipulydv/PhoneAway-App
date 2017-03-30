package com.pclubproject.phoneaway;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by Anubhav Mittal on 13-06-2016.
 */
public class SendMissCall {
    ContentResolver resolver;
    Cursor cursor=null;
    Context context=null;
    boolean successInd=true;

    private void requestPermission(){

    String[] CallDetails =  {
            android.provider.CallLog.Calls._ID,
            android.provider.CallLog.Calls.CACHED_NAME,
            android.provider.CallLog.Calls.NUMBER,
            android.provider.CallLog.Calls.DATE,
            android.provider.CallLog.Calls.DURATION,
    };}
    SendMissCall(Context context) {
        this.context=context;
        resolver = context.getContentResolver();
        try
        {
            cursor=resolver.query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");
        }
        catch (SecurityException e)
        {
            successInd=false;
            //place request permission notification here.
        }
        this.context=context;
    }

    public String getMissCall()
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences(AttentionWords.CODE_PREFERENCES,0 );
        int maxMiss=sharedPreferences.getInt(AttentionWords.MISSCALL_SEEK,AttentionWords.MISSCALL_SEEK_DEFAULT);
        StringBuffer stringBuffer=new StringBuffer();
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);

        int i=0;
        while (cursor.moveToNext() && i<maxMiss) {
            String phNumber = cursor.getString(number);
            String callType = cursor.getString(type);
            String callDate = cursor.getString(date);
            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = cursor.getString(duration);
            String dir = null;
            int dircode = Integer.parseInt(callType);
            boolean flag=false;
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    flag=true;
                    break;
            }
            if(flag) {
                stringBuffer.append("\nPhone Number:--- " + phNumber //+ " \nCall Type:--- "+ dir
                        + " \nCall Date:--- " + callDayTime
                        //+ " \nCall duration in sec :--- " + callDuration
                        );
                stringBuffer.append("\n----------------------------------");

                i++;
            }
        }
        cursor.close();
        return stringBuffer.toString();
    }

}
