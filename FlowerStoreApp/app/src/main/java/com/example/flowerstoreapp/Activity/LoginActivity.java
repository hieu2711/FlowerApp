package com.example.flowerstoreapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flowerstoreapp.Api.ApiLogin;
import com.example.flowerstoreapp.R;
import com.example.flowerstoreapp.model.Login;
import com.example.flowerstoreapp.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;
    TextView txtBack;
    TextView txtSingUp,txtForgotPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anhXa();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendLogin();
            }
        });
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentMain();
            }
        });
        txtSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentSignUp();
            }
        });
        txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentForgotPass();
            }
        });
    }

    private void sendLogin(){
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        Login login = new Login(username,password);
        ApiLogin.apiService.login(login)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        int statusCode = response.code();
                        if (statusCode == 200) {
                            Toast.makeText(getApplicationContext(), "LOGIN SUCCESS", Toast.LENGTH_LONG).show();
                            Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                            myIntent.putExtra("flag", statusCode);
                            myIntent.putExtra("username", edtUsername.getText().toString());
                            myIntent.putExtra("password", edtPassword.getText().toString());
                            startActivity(myIntent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Username or password is invalid!", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "LOGIN FAILED", Toast.LENGTH_LONG).show();
                    }
                });

    }

    private void IntentMain(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }

    private void IntentSignUp(){
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
    private void IntentForgotPass(){
        Intent intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);
    }
    private  void anhXa(){
       edtPassword = findViewById(R.id.edtPassword);
       edtUsername = findViewById(R.id.edtUsername);
       btnLogin = findViewById(R.id.btnlogin);
       txtBack = findViewById(R.id.txtBack);
       txtSingUp = findViewById(R.id.txtSignup);
       txtForgotPass = findViewById(R.id.txtForgotPass);
    }

}