package com.pclubproject.phoneaway;



import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Bind;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    public static final String SIGNUP_PREFERENCES="signup_Preferences";
    public static final String SECQUE="secque";
    public static final String SECQUE_DEFAULT="secque_default";
    public static final String SECANS="secans";
    public static final String SECANS_DEFAULT="secans_default";
    public static final String PASSWORD="password";
    public static final String PASSWORD_DEFAULT="password_default";
    private int x=1;

    public static SharedPreferences sharedPreferences;
    public static SharedPreferences passcodePreferences;
    public static SharedPreferences.Editor editor;


    @Bind(R.id.input_secque)
    EditText _secqueText;
    @Bind(R.id.input_secans) EditText _secansText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_signup)
    Button _signupButton;
    // @Bind(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
      /*  if(x==1){
            x=2;
            Intent intent = new Intent(this, Splash.class); //create intent with second screen
            startActivity(intent); //call second screen
        }*/



        ButterKnife.bind(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

  /*      _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });*/
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();



        // SignUp
        setAvlChoices();
        onSaveChanges();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();

                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 1000);
    }


    public void setAvlChoices()
    {
        sharedPreferences=getSharedPreferences(SIGNUP_PREFERENCES,0);

        String c1,c2,c3;
        c1=sharedPreferences.getString(SECQUE,SECQUE_DEFAULT);
        c2=sharedPreferences.getString(SECANS,SECANS_DEFAULT);
        c3=sharedPreferences.getString(PASSWORD,PASSWORD_DEFAULT);

        _secqueText=(EditText)findViewById(R.id.input_secque);
        _secansText=(EditText)findViewById(R.id.input_secans);
        _passwordText=(EditText)findViewById(R.id.input_password);

    }

    public void onSaveChanges()
    {
        sharedPreferences=getSharedPreferences(SIGNUP_PREFERENCES,0 );
        editor=sharedPreferences.edit();

        _secqueText=(EditText)findViewById(R.id.input_secque);
        _secansText=(EditText)findViewById(R.id.input_secans);
        _passwordText=(EditText)findViewById(R.id.input_password);



        String c1,c2,c3;

        c1=_secqueText.getText().toString();
        editor.putString(SECQUE,c1);
        c2=_secansText.getText().toString();
        editor.putString(SECANS,c2);
        c3=_passwordText.getText().toString();
        editor.putString(PASSWORD,c3);
        editor.apply();//can also use editor.commit();

        passcodePreferences=getSharedPreferences(Passcode.PASSCODE_PREFERENCES,0 );
        editor=passcodePreferences.edit();
        editor.putString(Passcode.PASSCODE,c3);
        editor.apply();//can also use editor.commit();


    }




    public void onSignupSuccess() {
        String c1;
        c1=_secqueText.getText().toString();

        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        Check.isFirst(this);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class); //create intent with second screen
        startActivity(intent);
        Toast.makeText(getBaseContext(), "Hi !", Toast.LENGTH_LONG).show();

    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

       // String secque = _secqueText.getText().toString();
       // String secans = _secansText.getText().toString();
        String password = _passwordText.getText().toString();

       /* if (name.isEmpty() || name.length() < 3) {
            _secqueText.setError("at least 3 characters");
            valid = false;
        } else {
            _secqueText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _secansText.setError("enter a valid email address");
            valid = false;
        } else {
            _secansText.setError(null);
        }*/

        if (password.isEmpty() || password.length() < 8) {
            _passwordText.setError("Paassword must be at least 8 characters long.");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

}