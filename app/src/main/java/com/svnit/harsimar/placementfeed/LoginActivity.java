package com.svnit.harsimar.placementfeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity {
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    String ACCOUNT_INFO="facebookAccountInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

      //  Log.d("harsimarSingh",Profile.getCurrentProfile().toString());
       /* if(!Profile.getCurrentProfile().getId().isEmpty()){
            Intent intent= new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }*/
        initialise();
        loginWithFb();


    }
    private void initialise(){
        loginButton=(LoginButton)findViewById(R.id.facebook_login_button);
        loginButton.setReadPermissions("email");
        callbackManager=CallbackManager.Factory.create();

    }

    private void loginWithFb(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent= new Intent(LoginActivity.this,MainActivity.class);
                //intent.putExtra(ACCOUNT_INFO,loginResul);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this,"Login failed",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);

    }
}
