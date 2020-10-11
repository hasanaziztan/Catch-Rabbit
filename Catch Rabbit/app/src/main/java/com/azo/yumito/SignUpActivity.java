package com.azo.yumito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    EditText usernameText,passwordText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameText = findViewById(R.id.sign_up_activity_name_text);
        passwordText = findViewById(R.id.sign_up_activity_passaword_text);

        ParseUser parseUser = ParseUser.getCurrentUser();

        if ( parseUser != null){
            Intent intent = new Intent(getApplicationContext(),FeedActivity.class);
            startActivity(intent);
        }

    }

    public void logIn (View view){

        ParseUser.logInInBackground(usernameText.getText().toString(), passwordText.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Welcome!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),FeedActivity.class);
                    startActivity(intent);
                }
            }
        });



    }
    public void signUp (View view){

        ParseUser parseUser = new ParseUser();
        parseUser.setUsername(usernameText.getText().toString());
        parseUser.setPassword(passwordText.getText().toString());

        parseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e !=  null){
                    Toast.makeText(getApplicationContext(),e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Kullanıcı oluşturuldu!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),FeedActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
