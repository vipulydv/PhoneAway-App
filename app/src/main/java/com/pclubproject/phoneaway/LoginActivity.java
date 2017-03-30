package com.pclubproject.phoneaway;

/**
 * Created by vipul on 20/6/16.
 */
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Bind;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private int x=1;
    SharedPreferences loginPreferences;

    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.forgotpassword) TextView _forgotPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       /* if(x==1){
            x=2;
            Intent intent = new Intent(this, Splash.class); //create intent with second screen
            startActivity(intent); //call second screen
        }*/

       /* Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);*/

        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        /*_forgotPassword.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });*/
        _forgotPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        /*_forgotPassword.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the ForgotPassword activity
                Intent intent = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(intent);
            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }
        String password = _passwordText.getText().toString();

        //authentication logic


        loginPreferences=this.getSharedPreferences(SignupActivity.SIGNUP_PREFERENCES,0);//replaced context by this

        String s1;
        s1=loginPreferences.getString(SignupActivity.PASSWORD,SignupActivity.PASSWORD_DEFAULT);
        if(s1.equals(password)){
        }
        else{
            onLoginFailed();
            return;
        }





        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

      /*  String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        loginPreferences=this.getSharedPreferences(SignupActivity.SIGNUP_PREFERENCES,0);//replaced context by this

        String s1,s2;
        s1=loginPreferences.getString(SignupActivity.EMAIL,SignupActivity.EMAIL_DEFAULT);
        s2=loginPreferences.getString(SignupActivity.PASSWORD,SignupActivity.PASSWORD_DEFAULT);
        if(s1==email && s2==password){

        }
        else{
            onLoginFailed();
            return;
        }*/

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 1000);
    }




    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        Toast.makeText(getBaseContext(), "Hi "+"!", Toast.LENGTH_LONG).show();
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String password = _passwordText.getText().toString();

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }



}

