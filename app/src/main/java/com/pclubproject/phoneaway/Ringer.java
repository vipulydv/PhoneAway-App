package com.pclubproject.phoneaway;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.widget.Toast;

/**
 * Created by Anubhav Mittal on 28-05-2016.
 */
public class Ringer {
    AudioManager am;
    public void playAlarm(Context context,String sendernumber) {
        try {
            am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            Uri tone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            if (tone == null) {
                tone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                if (tone == null) {
                    tone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                }

            }
            am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);      //change to non-silent mode
            am.setStreamVolume(am.STREAM_RING, am.getStreamMaxVolume(AudioManager.STREAM_RING), 0); //change RINGER volume to max.
            am.setStreamVolume(am.STREAM_MUSIC, am.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);//change MUSIC volume to max.
            am.setStreamVolume(am.STREAM_ALARM, am.getStreamMaxVolume(AudioManager.STREAM_ALARM), 0);//change ALARM volume to max.
            // MediaPlayer mp=MediaPlayer.create(context,tone);        //create media player that will play this music
            //mp.start();                     //play music.
            Ringtone ringtone = RingtoneManager.getRingtone(context, tone);
            ringtone.play();


            SharedPreferences sharedPreferences=context.getSharedPreferences(Ring.RING_PREFERENCES,0);
            Boolean b1,b2;
            b1=sharedPreferences.getBoolean(Ring.VIBRATE_ENABLE,Ring.VIBRATE_ENABLE_DEFAULT);
            b2=sharedPreferences.getBoolean(Ring.RESPONSE_ENABLE,Ring.RESPONSE_ENABLE_DEFAULT);
            if(b1)
            {
                Vibrator vibrator;
                vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(10000); // vibrate for 10 seconds (e.g 10000 milliseconds)
            }
            if(b2)
            {
                SmsSender smsSender = new SmsSender();
                smsSender.sendsms(context,"Alarm service has been executed.", sendernumber);
            }
            Toast.makeText(context,"Ringtone playing",Toast.LENGTH_SHORT).show();

        }catch (Exception e)
        {
            Toast.makeText(context,"Failed to play Alarm.\nError encountered:"+e,Toast.LENGTH_SHORT).show();    //shows error if request fails.
        }
    }
}
