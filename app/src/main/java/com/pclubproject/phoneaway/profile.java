package com.pclubproject.phoneaway;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class profile extends AppCompatActivity {
    public static final String PROFILE_PREFERENCES="profile_data";
    public static final String SET_PROFILE="setProfile";
    public static final String SET_PROFILE_DEFAULT="General";

    public static final String GENERAL_PROFILE="General";
    public static final String VIBRATE_PROFILE="Vibrate";

    public static SharedPreferences sharedPreferences;

    private static RadioGroup rg;
    private static RadioButton rb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setAvlChoices();
        onClick();
    }

    private void setAvlChoices()
    {
        sharedPreferences=this.getSharedPreferences(PROFILE_PREFERENCES,0);
        String pro=sharedPreferences.getString(SET_PROFILE,SET_PROFILE_DEFAULT);

        if(pro.equals(GENERAL_PROFILE))
        {
            rb=(RadioButton)findViewById(R.id.rb1);
            rb.setChecked(true);
        }
        else
        {
            rb=(RadioButton)findViewById(R.id.rb2);
            rb.setChecked(true);
        }
    }

    private void onClick()
    {
        sharedPreferences=this.getSharedPreferences(PROFILE_PREFERENCES,0);
        rg=(RadioGroup)findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int selected_id=rg.getCheckedRadioButtonId();
                rb=(RadioButton)findViewById(selected_id);

                SharedPreferences.Editor editor=sharedPreferences.edit();

                if(rb.getText().toString().equals("Ring"))
                {
                    editor.putString(SET_PROFILE,GENERAL_PROFILE);
                    editor.commit();
                    //Toast.makeText(getApplicationContext(),"General",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    editor.putString(SET_PROFILE,VIBRATE_PROFILE);
                    editor.commit();
                    //Toast.makeText(getApplicationContext(),"Vibrate",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
