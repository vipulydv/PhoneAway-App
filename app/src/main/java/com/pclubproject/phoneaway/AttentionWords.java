package com.pclubproject.phoneaway;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class AttentionWords extends AppCompatActivity {
    public static final String CODE_PREFERENCES="code_Preferences";
    public static final String CODE_IMEI="code_imei";
    public static final String IMEI_DEFAULT="imei";
    public static final String ENABLE_IMEI="enableIMEI";
    public static final boolean ENABLE_IMEI_DEFAULT=true;
    public static final String CODE_RINGER="code_ringer";
    public static final String RINGER_DEFAULT="ringer";
    public static final String ENABLE_RINGER="enableRinger";
    public static final boolean ENABLE_RINGER_DEFAULT=true;
    public static final String CODE_PROFILE="code_profile";
    public static final String PROFILE_DEFAULT="profile";
    public static final String ENABLE_PROFILE="enableProfile";
    public static final boolean ENABLE_PROFILE_DEFAULT=true;
    public static final String CODE_CONTACTS="code_contacts";
    public static final String CONTACTS_DEFAULT="contact";
    public static final String ENABLE_CONTACTS="enableContacts";
    public static final boolean ENABLE_CONTACTS_DEFAULT=true;
    public static final String CODE_MISSCALL="code_miscall";
    public static final String MISSCALL_DEFAULT="misscall";
    public static final String ENABLE_MISSCALL="enableMisscall";
    public static final boolean ENABLE_MISSCALL_DEFAULT=true;
    public static final String MISSCALL_SEEK ="misscall_seek";
    public static final int MISSCALL_SEEK_DEFAULT =5;

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;

    private static EditText tf1,tf2,tf3,tf4,tf5;
    private static TextView l1,l2,l3,l4;
    private static Switch s1,s2,s3,s4,s5;
    private static Button button;
    private static SeekBar seekbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention_words);
        setAvlChoices();
        onSaveChanges();
        onSwitchClick();
    }
    public void setAvlChoices()
    {
        sharedPreferences=getSharedPreferences(CODE_PREFERENCES,0);
        boolean b1,b2,b3,b4,b5;
        b1=sharedPreferences.getBoolean(ENABLE_RINGER,ENABLE_RINGER_DEFAULT);
        b2=sharedPreferences.getBoolean(ENABLE_PROFILE,ENABLE_PROFILE_DEFAULT);
        b3=sharedPreferences.getBoolean(ENABLE_IMEI,ENABLE_IMEI_DEFAULT);
        b4=sharedPreferences.getBoolean(ENABLE_MISSCALL,ENABLE_MISSCALL_DEFAULT);
        b5=sharedPreferences.getBoolean(ENABLE_CONTACTS,ENABLE_CONTACTS_DEFAULT);

        String c1,c2,c3,c4,c5;
        c1=sharedPreferences.getString(CODE_RINGER,RINGER_DEFAULT);
        c2=sharedPreferences.getString(CODE_PROFILE,PROFILE_DEFAULT);
        c3=sharedPreferences.getString(CODE_IMEI,IMEI_DEFAULT);
        c4=sharedPreferences.getString(CODE_MISSCALL,MISSCALL_DEFAULT);
        c5=sharedPreferences.getString(CODE_CONTACTS,CONTACTS_DEFAULT);

        int seekVal;
        seekVal=sharedPreferences.getInt(MISSCALL_SEEK,MISSCALL_SEEK_DEFAULT);

        s1=(Switch)findViewById(R.id.sw_ringer);
        s2=(Switch)findViewById(R.id.sw_profile);
        s3=(Switch)findViewById(R.id.sw_imei);
        s4=(Switch)findViewById(R.id.sw_miscall);
        s5=(Switch)findViewById(R.id.sw_contacts);

        tf1=(EditText)findViewById(R.id.tf1);
        tf2=(EditText)findViewById(R.id.tf2);
        tf3=(EditText)findViewById(R.id.tf3);
        tf4=(EditText)findViewById(R.id.tf4);
        tf5=(EditText)findViewById(R.id.tf5);

        l1=(TextView)findViewById(R.id.l1);
        l2=(TextView)findViewById(R.id.l2);
        l3=(TextView)findViewById(R.id.l3);
        l4=(TextView)findViewById(R.id.l4);

        seekbar=(SeekBar)findViewById(R.id.seek);
        seekbar.setProgress(seekVal);
        seekbar.setEnabled(b4);

        s1.setChecked(b1);
        tf1.setText(c1);
        tf1.setFocusable(b1);
        tf1.setFocusableInTouchMode(b1);
        tf1.setClickable(b1);

        s2.setChecked(b2);
        tf2.setText(c2);
        tf2.setFocusableInTouchMode(b2);
        tf2.setFocusable(b2);
        tf2.setClickable(b2);

        s3.setChecked(b3);
        tf3.setText(c3);
        tf3.setFocusableInTouchMode(b3);
        tf3.setFocusable(b3);
        tf3.setClickable(b3);

        s4.setChecked(b4);
        tf4.setText(c4);
        tf4.setFocusableInTouchMode(b4);
        tf4.setFocusable(b4);
        tf4.setClickable(b4);

        s5.setChecked(b5);
        tf5.setText(c5);
        tf5.setFocusableInTouchMode(b5);
        tf5.setFocusable(b5);
        tf5.setClickable(b5);
    }
    public void onSaveChanges()
    {
        sharedPreferences=getSharedPreferences(CODE_PREFERENCES,0 );
        editor=sharedPreferences.edit();
        s1=(Switch)findViewById(R.id.sw_ringer);
        s2=(Switch)findViewById(R.id.sw_profile);
        s3=(Switch)findViewById(R.id.sw_imei);
        s4=(Switch)findViewById(R.id.sw_miscall);
        s5=(Switch)findViewById(R.id.sw_contacts);

        tf1=(EditText)findViewById(R.id.tf1);
        tf2=(EditText)findViewById(R.id.tf2);
        tf3=(EditText)findViewById(R.id.tf3);
        tf4=(EditText)findViewById(R.id.tf4);
        tf5=(EditText)findViewById(R.id.tf5);

        button=(Button)findViewById(R.id.savebutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c1,c2,c3,c4,c5;

                c1=tf1.getText().toString();
                c2=tf2.getText().toString();
                c3=tf3.getText().toString();
                c4=tf4.getText().toString();
                c5=tf5.getText().toString();

                boolean b1,b2,b3,b4,b5;
                b1=s1.isChecked();
                b2=s2.isChecked();
                b3=s3.isChecked();
                b4=s4.isChecked();
                b5=s5.isChecked();

                int seekVal= seekbar.getProgress();

                int flag=0;
                if(b1)
                {
                    if(c1.equals(""))
                    {
                        tf1.setError("Please enter a code for this field or disable it.");
                        flag=1;
                    }
                    else{
                        if(b2 && c1.equals(c2))
                        {
                            tf1.setError("Same code for more than one service.");
                            tf2.setError("Same code for more than one service.");
                            flag=1;
                        }
                        if(b3 && c1.equals(c3))
                        {
                            tf1.setError("Same code for more than one service.");
                            tf3.setError("Same code for more than one service.");
                            flag=1;
                        }
                        if(b4 && c1.equals(c4))
                        {
                            tf1.setError("Same code for more than one service.");
                            tf4.setError("Same code for more than one service.");
                            flag=1;
                        }
                        if(b5 && c1.equals(c5))
                        {
                            tf1.setError("Same code for more than one service.");
                            tf5.setError("Same code for more than one service.");
                            flag=1;
                        }
                    }
                }
                if(b2)
                {
                    if(c2.equals(""))
                    {
                        tf2.setError("Please enter a code for this field or disable it.");
                        flag=1;
                    }
                    else{
                        if(b3 && c2.equals(c3))
                        {
                            tf2.setError("Same code for more than one service.");
                            tf3.setError("Same code for more than one service.");
                            flag=1;
                        }
                        if(b4 && c2.equals(c4))
                        {
                            tf2.setError("Same code for more than one service.");
                            tf4.setError("Same code for more than one service.");
                            flag=1;
                        }
                        if(b5 && c2.equals(c5))
                        {
                            tf2.setError("Same code for more than one service.");
                            tf5.setError("Same code for more than one service.");
                            flag=1;
                        }
                    }
                }
                if(b3)
                {
                    if(c3.equals(""))
                    {
                        tf3.setError("Please enter a code for this field or disable it.");
                        flag=1;
                    }
                    else
                    {
                        if(b4 && c3.equals(c4))
                        {
                            tf3.setError("Same code for more than one service.");
                            tf4.setError("Same code for more than one service.");
                            flag=1;
                        }
                        if(b5 && c3.equals(c5))
                        {
                            tf3.setError("Same code for more than one service.");
                            tf5.setError("Same code for more than one service.");
                            flag=1;
                        }
                    }
                }
                if(b4)
                {
                    if(c4.equals(""))
                    {
                        tf4.setError("Please enter a code for this field or disable it.");
                        flag=1;
                    }
                    else
                    {
                        if(b5 && c4.equals(c5))
                        {
                            tf4.setError("Same code for more than one service.");
                            tf5.setError("Same code for more than one service.");
                            flag=1;
                        }
                    }
                }
                if(b5)
                {
                    if(c5.equals(""))
                    {
                        tf5.setError("Please enter a code for this field or disable it.");
                        flag=1;
                    }
                }


                if(flag==0)
                {
                    editor.putBoolean(ENABLE_RINGER,b1);
                    editor.putBoolean(ENABLE_PROFILE,b2);
                    editor.putBoolean(ENABLE_IMEI,b3);
                    editor.putBoolean(ENABLE_MISSCALL,b4);
                    editor.putBoolean(ENABLE_CONTACTS,b5);
                    if(b1)
                    {
                        editor.putString(CODE_RINGER,c1);
                    }
                    if(b2)
                    {
                        editor.putString(CODE_PROFILE,c2);
                    }
                    if(b3)
                    {
                        editor.putString(CODE_IMEI,c3);
                    }
                    if(b4)
                    {
                        editor.putString(CODE_MISSCALL,c4);
                        editor.putInt(MISSCALL_SEEK,seekVal);
                    }
                    if(b5)
                    {
                        editor.putString(CODE_CONTACTS,c5);
                    }
                    editor.commit();
                    Toast.makeText(getApplicationContext(),"Changes Saved Successfully.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void onSwitchClick()
    {
        s1=(Switch)findViewById(R.id.sw_ringer);
        s2=(Switch)findViewById(R.id.sw_profile);
        s3=(Switch)findViewById(R.id.sw_imei);
        s4=(Switch)findViewById(R.id.sw_miscall);
        s5=(Switch)findViewById(R.id.sw_contacts);

        tf1=(EditText)findViewById(R.id.tf1);
        tf2=(EditText)findViewById(R.id.tf2);
        tf3=(EditText)findViewById(R.id.tf3);
        tf4=(EditText)findViewById(R.id.tf4);
        tf5=(EditText)findViewById(R.id.tf5);

        seekbar=(SeekBar)findViewById(R.id.seek);

        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tf1.setFocusableInTouchMode(isChecked);
                tf1.setFocusable(isChecked);
                tf1.setClickable(isChecked);
            }
        });

        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tf2.setFocusableInTouchMode(isChecked);
                tf2.setFocusable(isChecked);
                tf2.setClickable(isChecked);
            }
        });

        s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tf3.setFocusableInTouchMode(isChecked);
                tf3.setFocusable(isChecked);
                tf3.setClickable(isChecked);
            }
        });

        s4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tf4.setFocusableInTouchMode(isChecked);
                tf4.setFocusable(isChecked);
                tf4.setClickable(isChecked);

                seekbar.setEnabled(isChecked);

            }
        });

        s5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tf5.setFocusableInTouchMode(isChecked);
                tf5.setFocusable(isChecked);
                tf5.setClickable(isChecked);
            }
        });
    }

}
