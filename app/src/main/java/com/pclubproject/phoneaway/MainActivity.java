package com.pclubproject.phoneaway;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private static ImageButton r,r1,r2,r3,r4,r5,r6,r7;
    SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences=this.getSharedPreferences(Passcode.PASSCODE_PREFERENCES,0);//replaced context by this

        Boolean b;
        b=mSharedPreferences.getBoolean(Passcode.PASSCODE_ENABLE,Passcode.PASSCODE_ENABLE_DEFAULT);

        if(flag.x==0){
            if(b){
                flag.a=1;
            }
            else{
                flag.a=0;
            }
            flag.x=1;
        }

        if(flag.a==1){
            if( Check.isFirst(this))
            {
                Intent intent = new Intent(this, SignupActivity.class); //create intent with second screen
                startActivity(intent); //call second screen
            }else{

                //call screen before the main - for the first time
                Intent intent = new Intent(this, LoginActivity.class); //create var intent with the screem for first time
                startActivity(intent); //call new screen

                //put the sharedpreference boolean firsttime equal to false
            /*editorValidation= validation.edit();  // edit the sharedpreference
            editorValidation.putBoolean("firsttime",false); // put false to firsttime
            editorValidation.apply(); //apply(); runs in background, no return anything and commit(); return true or false*/
            }
        }

        flag.a=0;



        onClickButtonListener();
        onClickButtonListener1();
        onClickButtonListener2();
        onClickButtonListener3();
        onClickButtonListener4();
        onClickButtonListener5();
        onClickButtonListener6();
        onClickButtonListener7();
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
    }




    public void onClickButtonListener(){

        r = (ImageButton) findViewById(R.id.imageButton);
        r.setOnClickListener(
          new View.OnClickListener(){
              @Override
              public void onClick(View v){
                  Intent intent=new Intent("com.pclubproject.phoneaway.profile");
                  startActivity(intent);
              }

          }
        );
    }
    public void onClickButtonListener1(){
        r1 = (ImageButton)findViewById(R.id.imageButton1);
        r1.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent=new Intent("com.pclubproject.phoneaway.Ring");
                        startActivity(intent);
                    }

                }
        );
    }
    public void onClickButtonListener2(){
        r2 = (ImageButton)findViewById(R.id.imageButton2);
        r2.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent=new Intent("com.pclubproject.phoneaway.Contacts");
                        startActivity(intent);
                    }
                }
        );
    }
    public void onClickButtonListener3(){
        r3 = (ImageButton)findViewById(R.id.imageButton3);
        r3.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent=new Intent("com.pclubproject.phoneaway.Passcode");
                        startActivity(intent);
                    }

                }
        );
    }

    public void onClickButtonListener4(){
        r4 = (ImageButton) findViewById(R.id.imageButton4);
        r4.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent=new Intent("com.pclubproject.phoneaway.Message_log");
                        startActivity(intent);
                    }

                }
        );
    }

    public void onClickButtonListener5(){
        r5 = (ImageButton)findViewById(R.id.imageButton5);
        r5.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent=new Intent("com.pclubproject.phoneaway.AttentionWords");
                        startActivity(intent);
                    }

                }
        );
    }

    public void onClickButtonListener6(){
        r6 = (ImageButton)findViewById(R.id.imageButton6);
        r6.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent=new Intent("com.pclubproject.phoneaway.Help");
                        startActivity(intent);
                    }

                }
        );
    }

    public void onClickButtonListener7(){
        r7 = (ImageButton) findViewById(R.id.imageButton7);
        r7.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent=new Intent("com.pclubproject.phoneaway.About");
                        startActivity(intent);
                    }

                }
        );
    }
@Override
public void onBackPressed() {
    // Disable going back to the MainActivity
    moveTaskToBack(true);
}

}


