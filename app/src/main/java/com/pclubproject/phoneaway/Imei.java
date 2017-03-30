package com.pclubproject.phoneaway;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Imei extends AppCompatActivity {
    public static Switch imeiSwitch;
    public String IMEI_PREFERENCES="IMEIdata";
    public String IMEI_ENABLE="enableIMEI";
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imei);
        getValues();
        setValues();
    }
    public void getValues()
    {
        sharedPreferences=getSharedPreferences(IMEI_PREFERENCES,0);
        boolean enableSwitch=sharedPreferences.getBoolean(IMEI_ENABLE,true);
        imeiSwitch=(Switch)findViewById(R.id.switch1);
        imeiSwitch.setChecked(enableSwitch);
    }
    public void setValues()
    {
        imeiSwitch=(Switch)findViewById(R.id.switch1);
        sharedPreferences=getSharedPreferences(IMEI_PREFERENCES,0);
        editor=sharedPreferences.edit();
        imeiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    editor.putBoolean(IMEI_ENABLE,isChecked);
                    editor.commit();
                    String msg;
                    if(isChecked)msg="IMEI feature is now enabled";
                    else msg="IMEI feature is now disabled";
                    Toast.makeText(getApplicationContext(),"IMEI feature="+isChecked,Toast.LENGTH_LONG).show();
            }
        });
    }
}
