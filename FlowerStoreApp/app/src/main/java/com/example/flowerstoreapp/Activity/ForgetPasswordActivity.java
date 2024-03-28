package com.example.flowerstoreapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flowerstoreapp.Api.APIForgetPassword;
import com.example.flowerstoreapp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordActivity extends AppCompatActivity {
    Button btnSendMail;
    EditText edtMail;
    TextView txtBack1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        AnhXa();
        btnSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendMail();
            }
        });
        txtBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentLogin();
            }
        });
    }
    private void AnhXa(){
        btnSendMail = findViewById(R.id.btnSendMail);
        edtMail = findViewById(R.id.edtMail);
        txtBack1 = findViewById(R.id.txtBack1);
    }
    private void SendMail(){
        Call<String> call = APIForgetPassword.apiService.forgotPass(edtMail.getText().toString());
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        int status = response.code();
                        if(status == 200) {
                            Toast.makeText(getApplicationContext(), "CALL API SUCCESS", Toast.LENGTH_SHORT).show();
                            Intent myIntent = new Intent(ForgetPasswordActivity.this, ChangePassWordActivity.class);
                            String tokens = response.body();
                            myIntent.putExtra("token", tokens);
                            startActivity(myIntent);
                        }
                        else if(status == 500){
                            Toast.makeText(getApplicationContext(), "EMAIL ĐĂNG KÍ KHÔNG ĐÚNG", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"CALL API ERROR",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void IntentLogin(){
        Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}