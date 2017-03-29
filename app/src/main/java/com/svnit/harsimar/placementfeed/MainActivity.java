package com.svnit.harsimar.placementfeed;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Boolean logStatus=isLoggedIn();

        if(logStatus.toString()=="false"){
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }


        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/236930879671269/feed",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback(){
                    @Override
                    public void onCompleted(GraphResponse response) {

                        JSONObject json=response.getJSONObject();
                        JSONArray jsonArray=new JSONArray();
                        try {
                            jsonArray=json.getJSONArray("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String data1="harsimar";
                        try {
                            data1 = jsonArray.getJSONObject(1).getString("message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.d("harsimarSingh","started");
                        Log.d("harsimarSingh",data1.toString());
                    }
                }
        ).executeAsync();


    }
    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }
    public void generateKeyHash(){
        try {

            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.svnit.harsimar.placementfeed",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("harsimarSigh", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
