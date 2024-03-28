package com.example.flowerstoreapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flowerstoreapp.Api.APIForgetPassword;
import com.example.flowerstoreapp.Api.APIResetPassWord;
import com.example.flowerstoreapp.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class ChangePassWordActivity extends AppCompatActivity {
    Button btnConfirm;
    EditText edtNewPass,edtNewPass2;
    String token;
    TextView txtBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass_word);
        AnhXa();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reSetPassWord();
            }
        });
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentLogin();
            }
        });
    }
    private void AnhXa(){
        btnConfirm = findViewById(R.id.btnConfirm);
        edtNewPass = findViewById(R.id.newPass);
        edtNewPass2 = findViewById(R.id.newPass2);
        txtBack = findViewById(R.id.txtBack2);
    }
    private void reSetPassWord(){
        String tokens = getIntent().getStringExtra("token");
        String[] parts = tokens.split("=");
        token = parts[1];
        if(edtNewPass.getText().toString().equals(edtNewPass2.getText().toString())) {
            APIResetPassWord.apiService.resetPass(token, edtNewPass.getText().toString())
                    .enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "RESET PASSWORD SUCCESS", Toast.LENGTH_SHORT).show();
                            IntentLogin();
                        }
                    });
        }
        else
        {
            Toast.makeText(getApplicationContext(), "PASSWORD INCORRECT", Toast.LENGTH_SHORT).show();
        }
    }
    private void IntentLogin(){
        Intent myIntent = new Intent(ChangePassWordActivity.this,LoginActivity.class);
        startActivity(myIntent);
    }
}