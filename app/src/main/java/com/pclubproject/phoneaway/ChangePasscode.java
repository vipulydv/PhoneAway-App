package com.pclubproject.phoneaway;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasscode extends AppCompatActivity {
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences sSharedPreferences;
    public static SharedPreferences.Editor editor;
    private static EditText tf1,tf2,tf3;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_passcode);
        errorDisplay();
        setPassword();
    }

    public void errorDisplay()
    {
        tf1=(EditText)findViewById(R.id.tf1);
        tf2=(EditText)findViewById(R.id.tf2);
        tf3=(EditText)findViewById(R.id.tf3);
        sharedPreferences=getSharedPreferences(Passcode.PASSCODE_PREFERENCES,0);
        //editor=sharedPreferences.edit();
        tf1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String enteredPasscode=s.toString();
                String currentPasscode=sharedPreferences.getString(Passcode.PASSCODE,Passcode.PASSCODE_DEFAULT);
                String currentPasscode1=sSharedPreferences.getString(SignupActivity.PASSWORD,SignupActivity.PASSWORD_DEFAULT);

                if(!currentPasscode.equals(enteredPasscode) && !currentPasscode1.equals(enteredPasscode) )
                {
                    tf1.setError("Wrong Password entered.");
                }
            }
        });
        tf2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String newPasscode=s.toString();
                if(newPasscode.length()<8)
                {
                    tf2.setError("Password must be atleast 8 characters long.");
                }
                String repeatPasscode=tf3.getText().toString();
                if(!newPasscode.equals(repeatPasscode))
                {
                    tf3.setError("Passwords must match.");
                }
            }
        });
        tf3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String newPasscode=tf2.getText().toString();
                if(newPasscode.length()<8)
                {
                    tf2.setError("Password must be atleast 8 characters long.");
                }
                String repeatPasscode=s.toString();
                if(!newPasscode.equals(repeatPasscode))
                {
                    tf3.setError("Passwords must match.");
                }
            }
        });
    }
    private void setPassword()
    {
        tf1=(EditText)findViewById(R.id.tf1);
        tf2=(EditText)findViewById(R.id.tf2);
        tf3=(EditText)findViewById(R.id.tf3);
        button=(Button)findViewById(R.id.changebtn);
        sharedPreferences=getSharedPreferences(Passcode.PASSCODE_PREFERENCES,0);
        sSharedPreferences=getSharedPreferences(SignupActivity.SIGNUP_PREFERENCES,0);
        editor=sharedPreferences.edit();
        //String currentPass=sharedPreferences.getString(Passcode.PASSCODE,"password");
        //if(tf1.getText().toString().equals(currentPass))
        //{}
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentPass=sharedPreferences.getString(Passcode.PASSCODE,Passcode.PASSCODE_DEFAULT);
                String pass=sSharedPreferences.getString(SignupActivity.PASSWORD,SignupActivity.PASSWORD_DEFAULT);
                if(tf1.getText().toString().equals(currentPass) || tf1.getText().toString().equals(pass) )
                {
                    String newpass=tf2.getText().toString();
                    if(newpass.length()>=8)
                    {
                        String repeatpass=tf3.getText().toString();
                        if(repeatpass.equals(newpass))
                        {
                            editor.putString(Passcode.PASSCODE,newpass);
                            editor.commit();
                            Toast.makeText(getApplicationContext(),"Password changed successfully. New password="+newpass,Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Passwords do not match.",Toast.LENGTH_SHORT).show();

                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"New Password should be atleast 8 characters long.",Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Wrong Login Password entered.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
