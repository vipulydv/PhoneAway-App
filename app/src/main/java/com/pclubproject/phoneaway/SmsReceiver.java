package com.pclubproject.phoneaway;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.MediaStore.Audio;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Date;


public class SmsReceiver extends BroadcastReceiver {
    private AudioManager am;
    SharedPreferences codePreferences;
    SharedPreferences passwordPreferences;

    History_database history_database;

    public int correctpass(String message, String pass, String codes[], Boolean codesEnabled[])  //Checks for correct password
    {
        try {
            int passl = pass.length();
            if (message.length() >= passl) {

                if ((message.substring(0, passl)).equals(pass)) {
                    if(codesEnabled[2]&& message.substring(passl+1).equals(codes[2]))
                    {
                        return 2;
                    }
                    int i = 0;
                    while (i < codes.length) {
                        if (codesEnabled[i] && (message.substring(passl + 1,passl+1+codes[i].length())).equals(codes[i]))   //needs for message to be exactly as per format.
                        {
                            return i;
                        }
                        i++;
                    }
                }
            }
        } catch (Exception e) {
            return -1;
        }
        return -1;
    }

    public void sendsms(Context context, String reqmsg, String sendernumber) {

        SmsSender smsSender = new SmsSender();
        smsSender.sendsms(context, reqmsg, sendernumber);
        /*try {
            SmsManager smsmanager = SmsManager.getDefault();
            smsmanager.sendTextMessage(sendernumber, null, "Yo Bro", null, null);
            Toast.makeText(context, "Message sent by Phoneaway", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            Toast.makeText(context,"Failed to send message.\nError encountered:"+e,Toast.LENGTH_SHORT); //shows error if request fails.
        }*/
    }

    public void ringerOn(Context context) {
        ProfileChanger profileChanger = new ProfileChanger();
        profileChanger.ringerOn(context);
        /*try {
            am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);      //change to non-silent mode
            am.setStreamVolume(am.STREAM_RING, am.getStreamMaxVolume(AudioManager.STREAM_RING), 0); //change RINGER volume to max.
            am.setStreamVolume(am.STREAM_MUSIC, am.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);//change MUSIC volume to max.
            am.setStreamVolume(am.STREAM_ALARM, am.getStreamMaxVolume(AudioManager.STREAM_ALARM), 0);//change ALARM volume to max.
            Toast.makeText(context, "Ringer Mode ON", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e)
        {
            Toast.makeText(context,"Failed to set Ringer ON.\nError encountered:"+e,Toast.LENGTH_SHORT);    //shows error if request fails.
        }*/
    }

    public void playAlarm(Context context,String sendernumber) {

        Ringer ringer = new Ringer();
        ringer.playAlarm(context,sendernumber);
        /*try {
            am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            Uri tone= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            if(tone==null)
            {
                tone=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                if(tone==null)
                {
                    tone=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                }

            }
            am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);      //change to non-silent mode
            am.setStreamVolume(am.STREAM_RING, am.getStreamMaxVolume(AudioManager.STREAM_RING), 0); //change RINGER volume to max.
            am.setStreamVolume(am.STREAM_MUSIC, am.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);//change MUSIC volume to max.
            am.setStreamVolume(am.STREAM_ALARM, am.getStreamMaxVolume(AudioManager.STREAM_ALARM), 0);//change ALARM volume to max.
           // MediaPlayer mp=MediaPlayer.create(context,tone);        //create media player that will play this music
            //mp.start();                     //play music.
            Ringtone ringtone=RingtoneManager.getRingtone(context,tone);
            ringtone.play();

        }
        catch(Exception e)
        {
            Toast.makeText(context,"Failed to play Alarm.\nError encountered:"+e,Toast.LENGTH_SHORT);    //shows error if request fails.
        }*/
    }

    public String getIMEI(String sendernumber, Context context) {
        try {
            TelephonyManager mTelephonyMgr;
            mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imei = mTelephonyMgr.getDeviceId();
            sendsms(context, imei, sendernumber);
            return imei;
        } catch (Exception e) {
            Toast.makeText(context, "Failed to get IMEI no.\nError encountered:" + e, Toast.LENGTH_SHORT);    //shows error if request fails.
            return "";
        }

    }

    public void notify(String message,Context context)
    {
        try {
            Intent intent = new Intent("com.pclubproject.phoneaway.LoginActivity");
            PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
            Notification notification = new Notification.Builder(context)
                    .setTicker("PhoneAway")
                    .setContentTitle("PhoneAway Triggered")
                    .setContentText(message)
                    .setSmallIcon(R.drawable.icon)
                    .setContentIntent(pIntent).getNotification();

            notification.flags = Notification.FLAG_AUTO_CANCEL;

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notification);
        }
        catch (Exception e)
        {
            Toast.makeText(context,"Notification could not be created.",Toast.LENGTH_SHORT).show();
        }
    }

    public void updateHistory(Context context,String title,String recmessage,String sentmessage) {
        //Toast.makeText(context,"Entering",Toast.LENGTH_LONG).show();
        try {
            history_database = new History_database(context);
            long result = history_database.insert(title, recmessage, sentmessage);
            if (result == -1) {
                //Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show();
            } else {
                //Toast.makeText(context, "Successful", Toast.LENGTH_LONG).show();
            }
        }catch (Exception e)
        {
            Toast.makeText(context,e.toString(), Toast.LENGTH_LONG).show();

        }
    }

    /*   public char correctpass2(String message)  //Checks for correct password
       {
           try {
               String pass="password";
               String code="status";
               int passl=pass.length();
               if (message.length() >=passl)
               {

                   if((message.substring(0,passl)).equals(pass))
                   {
                       if((message.substring(passl+1)).equals(code))   //needs for message to be exactly as per format.
                       {
                           return 'd';
                       }
                       else return 'e';

                   }
                   else
                   {
                       return 'c';
                   }
               }
               else
               {
                   return 'b';
               }
           }
           catch(Exception e)
           {
               return 'a';
           }
       }*/
    public void performTask(Context context, String message, String sendernumber, String password, String[] codes, Boolean[] codesEnabled) {

        try {
            int i = correctpass(message, password, codes, codesEnabled);

            if (i == 0) {
                playAlarm(context,sendernumber);
                notify("Alarm was trigerred by a message from "+sendernumber,context);
                updateHistory(context,"Alarm was trigerred by a message from "+sendernumber,message,"");
            } else if (i == 1) {
                ringerOn(context);
                notify("Profile was changed by a message from "+sendernumber,context);
                updateHistory(context,"Profile was changed by a message from "+sendernumber,message,"");
            } else if (i == 2) {
                Toast.makeText(context,"Entered!!",Toast.LENGTH_LONG).show();
                String imei=getIMEI(sendernumber, context);
                notify("The phones's IMEI no. was sent to "+sendernumber+" by PhoneAway.",context);
                updateHistory(context,"The phones's IMEI no. was sent to "+sendernumber,message,imei);
            } else if (i == 3) {

                SendMissCall sendMissCall=new SendMissCall(context);
                String reqsms = sendMissCall.getMissCall();
                Boolean successInd=sendMissCall.successInd;
                if(successInd) {
                    //sendsms(context, reqsms, sendernumber);
                    //Toast.makeText(context,reqsms, Toast.LENGTH_LONG).show();
                    SmsManager sms = SmsManager.getDefault();
                    ArrayList<String> parts = sms.divideMessage(reqsms);
                    sms.sendMultipartTextMessage(sendernumber, null, parts, null, null);

                    Toast.makeText(context,"A missed call list was sent by PhoneAway",Toast.LENGTH_SHORT).show();

                    notify("A missed call list was sent by PhoneAway to "+sendernumber,context);
                    updateHistory(context,"A missed call list was sent by PhoneAway to "+sendernumber,message,reqsms);
                }
                else
                {
                    Toast.makeText(context, "Fetching failed", Toast.LENGTH_LONG).show();
                }
            } else if(i == 4)
            {
                Toast.makeText(context,"Entered!!!",Toast.LENGTH_LONG).show();
                if(message.length() > (password.length()+codes[i].length()+2)) {
                    String reqContact = message.substring(password.length() + codes[i].length() + 2);
                    RetrieveContacts retrieveContacts = new RetrieveContacts();
                    String sentMessage = retrieveContacts.getContact(context, reqContact, sendernumber);
                    notify("A contact list was sent by PhoneAway to " + sendernumber, context);

                    updateHistory(context,"A contact list was sent by PhoneAway to " + sendernumber,message,sentMessage);
                }
            }
        } catch (Exception e) {
            return;
        }
        //Toast.makeText(context,"Message Received",Toast.LENGTH_SHORT).show();//showing a toast for confirmation of receive message.

        return;
    }


    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            Wakelocker.acquire(context);


            codePreferences=context.getSharedPreferences(AttentionWords.CODE_PREFERENCES,0);
            passwordPreferences=context.getSharedPreferences(Passcode.PASSCODE_PREFERENCES,0);

            String s1,s2,s3,s4,s5;
            s1=codePreferences.getString(AttentionWords.CODE_RINGER,AttentionWords.RINGER_DEFAULT);
            s2=codePreferences.getString(AttentionWords.CODE_PROFILE,AttentionWords.PROFILE_DEFAULT);
            s3=codePreferences.getString(AttentionWords.CODE_IMEI,AttentionWords.IMEI_DEFAULT);
            s4=codePreferences.getString(AttentionWords.CODE_MISSCALL,AttentionWords.MISSCALL_DEFAULT);
            s5=codePreferences.getString(AttentionWords.CODE_CONTACTS,AttentionWords.CONTACTS_DEFAULT);
            String codes[]={s1,s2,s3,s4,s5};

            Boolean b1,b2,b3,b4,b5;
            b1=codePreferences.getBoolean(AttentionWords.ENABLE_RINGER,AttentionWords.ENABLE_RINGER_DEFAULT);
            b2=codePreferences.getBoolean(AttentionWords.ENABLE_PROFILE,AttentionWords.ENABLE_PROFILE_DEFAULT);
            b3=codePreferences.getBoolean(AttentionWords.ENABLE_IMEI,AttentionWords.ENABLE_IMEI_DEFAULT);
            b4=codePreferences.getBoolean(AttentionWords.ENABLE_MISSCALL,AttentionWords.ENABLE_MISSCALL_DEFAULT);
            b5=codePreferences.getBoolean(AttentionWords.ENABLE_CONTACTS,AttentionWords.ENABLE_CONTACTS_DEFAULT);
            Boolean codesEnabled[]={b1,b2,b3,b4,b5};

            String password=passwordPreferences.getString(Passcode.PASSCODE,Passcode.PASSCODE_DEFAULT);

            Bundle bundle=intent.getExtras();//get bundles
            if (bundle != null)                //if bundles(ie here sms) are present,proceed further.
            {
                SmsMessage sms[] = null;

                Object pdus[] = (Object[]) bundle.get("pdus");     //array is initialised because for characters greater than threshold value,
                //it will be stored in multiple pdus.
                sms = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    sms[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    String message = sms[i].getMessageBody();
                    String sendernumber = sms[i].getOriginatingAddress();
                    //Intent alarmService=new Intent(context,AlarmService.class);
                    //startWakefulService(context,alarmService);

                    performTask(context, message, sendernumber,password,codes,codesEnabled);

                }
            }
            Wakelocker.release();
        }
        catch(Exception e)
        {
            Toast.makeText(context,"Error encountered:"+e,Toast.LENGTH_LONG);
        }

    }
}