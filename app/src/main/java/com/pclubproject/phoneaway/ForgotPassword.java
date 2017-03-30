package com.pclubproject.phoneaway;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ForgotPassword extends AppCompatActivity {
    SharedPreferences forgotpasswordPreferences;

    @Bind(R.id.sec_que) TextView _secqueText;
    @Bind(R.id.input_secans) EditText _secansText;
    @Bind(R.id.btn_ok) Button _okButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ButterKnife.bind(this);

        forgotpasswordPreferences=this.getSharedPreferences(SignupActivity.SIGNUP_PREFERENCES,0);
        String s1,s;
        s1=forgotpasswordPreferences.getString(SignupActivity.SECQUE,SignupActivity.SECQUE_DEFAULT);
        s=" Security Question: "+s1;
        _secqueText.setText(s);

        _okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ok();
            }
        });

    }

    public void ok(){
       // String secque = _secqueText.getText().toString();
        String secans = _secansText.getText().toString();

        forgotpasswordPreferences=this.getSharedPreferences(SignupActivity.SIGNUP_PREFERENCES,0);//replaced context by this



        String s2,s3;

        s2=forgotpasswordPreferences.getString(SignupActivity.SECANS,SignupActivity.SECANS_DEFAULT);
        s3=forgotpasswordPreferences.getString(SignupActivity.PASSWORD,SignupActivity.PASSWORD_DEFAULT);


        if( s2.equals(secans)){
            Toast.makeText(getApplicationContext(),"Your password is " + s3, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"Incorrect Security Answer.", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
