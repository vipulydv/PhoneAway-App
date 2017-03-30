package com.pclubproject.phoneaway;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class Ring extends AppCompatActivity {
    public static final String RING_PREFERENCES="ring_data";
    public static final String VIBRATE_ENABLE="vibrate";
    public static final boolean VIBRATE_ENABLE_DEFAULT=true;
    public static final String RESPONSE_ENABLE="response_sms";
    public static final boolean RESPONSE_ENABLE_DEFAULT=false;
    public static SharedPreferences sharedPreferences;

    public static CheckBox cb_vib,cb_resp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);
        setAvlChoices();
        OnChange();
    }

    public void setAvlChoices()
    {
        sharedPreferences=this.getSharedPreferences(RING_PREFERENCES,0);

        cb_vib=(CheckBox)findViewById(R.id.cb1);
        cb_resp=(CheckBox)findViewById(R.id.cb2);

        boolean b1,b2;
        b1=sharedPreferences.getBoolean(VIBRATE_ENABLE,VIBRATE_ENABLE_DEFAULT);
        b2=sharedPreferences.getBoolean(RESPONSE_ENABLE,RESPONSE_ENABLE_DEFAULT);

        cb_vib.setChecked(b1);
        cb_resp.setChecked(b2);
    }

    public void OnChange()
    {
        sharedPreferences=this.getSharedPreferences(RING_PREFERENCES,0);

        cb_vib=(CheckBox)findViewById(R.id.cb1);
        cb_resp=(CheckBox)findViewById(R.id.cb2);

        cb_vib.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean(VIBRATE_ENABLE,isChecked);
                editor.commit();
            }
        });
        cb_resp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean(RESPONSE_ENABLE,isChecked);
                editor.commit();
            }
        });

    }
}
