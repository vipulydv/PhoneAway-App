package com.pclubproject.phoneaway;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by vipul on 21/6/16.
 */
public class Check {
    private static final String MY_PREFERENCES = "my_preferences";

    public static boolean isFirst(Context context){
        final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        final boolean first = reader.getBoolean("is_first", true);
        if(first && context instanceof SignupActivity){
            final SharedPreferences.Editor editor = reader.edit();
            editor.putBoolean("is_first", false);
            editor.commit();
        }
        return first;
    }
}
