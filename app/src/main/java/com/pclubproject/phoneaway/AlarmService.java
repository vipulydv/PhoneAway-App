package com.pclubproject.phoneaway;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by Anubhav Mittal on 03-06-2016.
 */
public class AlarmService extends IntentService{
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    int startId;
    AudioManager am;
    public AlarmService() {
        super("Alarm Service Started");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.startId=startId;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Context context=this;
        long TotalTime=5000;
        long startingTime=System.currentTimeMillis();
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
            Toast.makeText(context,"Ringtone playing",Toast.LENGTH_SHORT).show();
            while((System.currentTimeMillis()-startingTime)<=TotalTime)
            {

            }
            stopSelf(this.startId);

        }catch (Exception e)
        {
            Toast.makeText(context,"Failed to play Alarm.\nError encountered:"+e,Toast.LENGTH_SHORT).show();    //shows error if request fails.
        }

    }
}
