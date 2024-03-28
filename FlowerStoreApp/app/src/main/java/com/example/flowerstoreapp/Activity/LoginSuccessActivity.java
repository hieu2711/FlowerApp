package com.example.flowerstoreapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flowerstoreapp.R;

public class LoginSuccessActivity extends AppCompatActivity {
    TextView signOut,txtUserName,txtPassWord;
    CheckBox checkBox;

    ImageView iconHome,iconSearch,iconCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        Anhxa();
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentLogin();
            }
        });
        GetIntent();
        iconHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentHome();
            }
        });
        iconSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentSearch();
            }
        });
        iconCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentCart();
            }
        });
    }
    private void IntentLogin(){
        Intent intent = new Intent(LoginSuccessActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void IntentHome(){
        Intent intent = new Intent(LoginSuccessActivity.this, MainActivity.class);
        startActivity(intent);
    }
    private void IntentSearch(){
        Intent intent = new Intent(LoginSuccessActivity.this, SearchActivity.class);
        startActivity(intent);
    }
    private void IntentCart(){
        Intent intent = new Intent(LoginSuccessActivity.this, CartActivity.class);
        startActivity(intent);
    }

    private void Anhxa(){
        signOut = findViewById(R.id.signOut);
        txtUserName = findViewById(R.id.txtUsername);
        txtPassWord = findViewById(R.id.txtPassword);
        checkBox = findViewById(R.id.cbPass);
        iconHome = findViewById(R.id.iconHome);
        iconCart = findViewById(R.id.iconCart);
        iconSearch = findViewById(R.id.iconsearch);
    }
    private void GetIntent(){
        String edtUsername = getIntent().getStringExtra("username");
        String edtPassword = getIntent().getStringExtra("password");
        String temp = "*".repeat(edtPassword.length());
        txtUserName.setText("UserName: " + edtUsername);
        txtPassWord.setText("PassWord: " + temp);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox.isChecked()) {
                    txtUserName.setText("UserName: " + edtUsername);
                    txtPassWord.setText("PassWord: " + edtPassword);
                } else {
                    txtPassWord.setText("PassWord: " + temp);
                }
            }
        });

    }
}