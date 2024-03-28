package com.example.flowerstoreapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flowerstoreapp.Adapter.ProductAdapter;
import com.example.flowerstoreapp.R;
import com.example.flowerstoreapp.model.Products;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    TextView txtProduct,txtPrice,txtAmount;
    ImageView imgProducts,imgHome,imgSearch,imgLogin,imgDecrease,imgAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        anhXa();
        DataTransfer();
        imgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentLogin();
            }
        });
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentHome();
            }
        });
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentSearch();
            }
        });
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Amount = Integer.parseInt(txtAmount.getText().toString());
                Amount = Amount + 1;
                txtAmount.setText(String.valueOf(Amount));
            }
        });
        imgDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int Amount = Integer.parseInt(txtAmount.getText().toString());
                if(Amount > 1) {
                    Amount = Amount - 1;
                    txtAmount.setText(String.valueOf(Amount));
                }
                else {
                    txtAmount.setText("0");
                }
            }
        });
    }

    private void DataTransfer() {
        Intent intent = getIntent();
        if (intent != null) {
            String flowerName = intent.getStringExtra("flower_name");
            double flowerPrice = intent.getDoubleExtra("flower_price", 0.0);
            String flowerImg = intent.getStringExtra("flower_img");
            txtProduct.setText(flowerName);
            txtPrice.setText(String.valueOf(flowerPrice) + "VND");
            Picasso.with(this).load(flowerImg).into(imgProducts);
        }
    }
    private void IntentHome(){
        Intent intent = new Intent(CartActivity.this, MainActivity.class);
        startActivity(intent);
    }
    private void IntentLogin(){
        Intent intent = new Intent(CartActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    private void IntentSearch(){
        Intent intent = new Intent(CartActivity.this, SearchActivity.class);
        startActivity(intent);
    }
    private  void anhXa(){
        txtPrice = findViewById(R.id.txtPriceProduct);
        txtProduct = findViewById(R.id.txtNameProduct);
        imgProducts = findViewById(R.id.imgProduct);
        imgHome = findViewById(R.id.imgHome);
        imgSearch = findViewById(R.id.imgSearch);
        imgLogin = findViewById(R.id.imgLogin);
        imgAdd = findViewById(R.id.ImgAdd);
        imgDecrease = findViewById(R.id.ImgDecrease);
        txtAmount = findViewById(R.id.txtAmount);

    }


}