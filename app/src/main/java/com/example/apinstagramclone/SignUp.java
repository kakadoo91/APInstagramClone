package com.example.apinstagramclone;

import androidx.appcompat.app.AppCompatActivity ;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

private EditText edtEmail, edtUserName,edtPassword;
private Button btnSignUp,btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
            setTitle("Sign Up");
            edtEmail = findViewById(R.id.edtEnterEmail);
            edtPassword = findViewById(R.id.edtEnterPassword);
            edtPassword.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {

                        onClick(btnSignUp);
                    }

                    return false;
                }
            });
            edtUserName = findViewById(R.id.edtUserName);
            btnSignUp = findViewById(R.id.btnSignUp);
            btnLogIn = findViewById(R.id.btnLogIn);

            btnSignUp.setOnClickListener(this);
            btnLogIn.setOnClickListener(this);

            if (ParseUser.getCurrentUser() != null){
           //      ParseUser.getCurrentUser().logOut();
                transitionToSocialMediaActivity();

            }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnSignUp:

                if (edtEmail.getText().toString().equals("") || edtUserName.getText().toString().equals("") || edtPassword.getText().toString().equals("")){

                    FancyToast.makeText(SignUp.this, "Please fill in all the fields! " ,Toast.LENGTH_SHORT,FancyToast.INFO,true).show();


                } else {
                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setUsername(edtUserName.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing Up " + edtUserName.getText().toString());
                    progressDialog.show();
                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignUp.this, appUser.getUsername() + " is signed up", Toast.LENGTH_SHORT, FancyToast.SUCCESS, true).show();
                                transitionToSocialMediaActivity();
                            } else {

                                FancyToast.makeText(SignUp.this, "There was an error " + e.getMessage(), Toast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                            }
                            progressDialog.dismiss();
                        }
                    });
                }

                break;
            case R.id.btnLogIn:
                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);

                break;
        }
    }
    public void rootLayoutTapped(View view){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);



    }

    private void transitionToSocialMediaActivity (){
    Intent intent = new Intent(SignUp.this, SocialMediaActivity.class);
    startActivity(intent);

    }


}
