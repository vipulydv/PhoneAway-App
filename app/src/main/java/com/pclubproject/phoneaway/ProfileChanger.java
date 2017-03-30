package com.pclubproject.phoneaway;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.widget.Toast;

/**
 * Created by Anubhav Mittal on 28-05-2016.
 */
public class ProfileChanger {
    AudioManager am;
    public void ringerOn(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences(profile.PROFILE_PREFERENCES,0);
        String getProfile=sharedPreferences.getString(profile.SET_PROFILE,profile.SET_PROFILE_DEFAULT);

        try {
            am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            if(getProfile .equals(profile.GENERAL_PROFILE) ) {
                am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);      //change to non-silent mode
                am.setStreamVolume(am.STREAM_RING, am.getStreamMaxVolume(AudioManager.STREAM_RING), 0); //change RINGER volume to max.
                am.setStreamVolume(am.STREAM_MUSIC, am.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);//change MUSIC volume to max.
                am.setStreamVolume(am.STREAM_ALARM, am.getStreamMaxVolume(AudioManager.STREAM_ALARM), 0);//change ALARM volume to max.
                Toast.makeText(context, "Ringer Mode ON", Toast.LENGTH_SHORT).show();
            }
            else
            {
                am.setStreamVolume(am.STREAM_RING, am.getStreamMaxVolume(AudioManager.STREAM_RING), 0); //change RINGER volume to max.
                am.setStreamVolume(am.STREAM_MUSIC, am.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);//change MUSIC volume to max.
                am.setStreamVolume(am.STREAM_ALARM, am.getStreamMaxVolume(AudioManager.STREAM_ALARM), 0);//change ALARM volume to max.
                am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Toast.makeText(context, "Vibrate Mode ON", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(context,"Failed to change profile.\nError encountered:"+e,Toast.LENGTH_SHORT);    //shows error if request fails.
        }
    }

}
