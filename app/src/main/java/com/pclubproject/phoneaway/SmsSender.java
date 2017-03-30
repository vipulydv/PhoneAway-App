package com.pclubproject.phoneaway;
import android.content.Context;
import android.media.AudioManager;
import android.telephony.SmsManager;
import android.widget.Toast;


public class SmsSender {
    private AudioManager am;
    public void sendsms(Context context, String reqmsg, String sendernumber)
    {
        try {
            SmsManager smsmanager = SmsManager.getDefault();
            smsmanager.sendTextMessage(sendernumber, null, reqmsg, null, null);
            Toast.makeText(context, "Message sent by Phoneaway", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            Toast.makeText(context,"Failed to send message.\nError encountered:"+e,Toast.LENGTH_SHORT); //shows error if request fails.
        }
    }
}
