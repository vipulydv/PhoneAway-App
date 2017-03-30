package com.pclubproject.phoneaway;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Passcode extends AppCompatActivity {
    private static Button r;
    private static Switch aSwitch;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    public static final String PASSCODE_PREFERENCES="passcodeData";
    public static final String PASSCODE_ENABLE="enablePasscode";
    public static final boolean PASSCODE_ENABLE_DEFAULT=true;
    public static final String PASSCODE="Passcode";
    public static final String PASSCODE_DEFAULT="password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);
        onClickButtonListener();
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getValues();
        setValues();
    }

    public void getValues()
    {
        aSwitch=(Switch)findViewById(R.id.passSwitch);
        sharedPreferences=getSharedPreferences(PASSCODE_PREFERENCES,0);
        boolean enablePasscode=sharedPreferences.getBoolean(PASSCODE_ENABLE,PASSCODE_ENABLE_DEFAULT);
        aSwitch.setChecked(enablePasscode);
    }
    public void setValues()
    {
        aSwitch=(Switch)findViewById(R.id.passSwitch);
        sharedPreferences=getSharedPreferences(PASSCODE_PREFERENCES,0);
        editor=sharedPreferences.edit();
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean(PASSCODE_ENABLE,isChecked);
                editor.commit();
                String msg;
                if(isChecked)msg="Passcode login is now enabled";
                else msg="Passcode login is now disabled";
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickButtonListener(){
        r = (Button)findViewById(R.id.button);
        r.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent=new Intent("com.pclubproject.phoneaway.ChangePasscode");
                        startActivity(intent);
                    }

                }
        );
    }
}
