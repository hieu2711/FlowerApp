package com.example.flowerstoreapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.flowerstoreapp.Api.ApiSignUp;
import com.example.flowerstoreapp.R;
import com.example.flowerstoreapp.model.Register;
import com.example.flowerstoreapp.model.SignUp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    TextView txtSignIn;
    EditText edtName;
    EditText edtEmail;
    EditText edtUserName;
    EditText edtPassWord;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        anhXa();
        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentLogin();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSignUp();
            }
        });

    }

        private void sendSignUp(){
        String username = edtUserName.getText().toString();
        String password = edtPassWord.getText().toString();
        String name = edtName.getText().toString();
        String email = edtEmail.getText().toString();
        SignUp signUp = new SignUp(name,username,email,password);
        ApiSignUp.apiService.SignUp(signUp)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(getApplicationContext(),"REGISTER ERROR",Toast.LENGTH_SHORT).show();
                        String message = response.errorBody().source().toString();
                        if(message.contains("Email")){
                            Toast.makeText(getApplicationContext(),"EMAIL NOT VALID",Toast.LENGTH_SHORT).show();
                        }else if(message.contains("Username")) {
                            Toast.makeText(getApplicationContext(), "USERNAME NOT VALID", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"REGISTER SUCCESS",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void IntentLogin() {
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void anhXa() {
        txtSignIn = findViewById(R.id.txtSignIn);
        edtEmail = findViewById(R.id.email);
        edtName = findViewById(R.id.name);
        edtUserName = findViewById(R.id.userName);
        edtPassWord = findViewById(R.id.passWord);
        btnSignUp = findViewById(R.id.btnSignUp);
    }
}