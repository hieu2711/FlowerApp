package com.example.flowerstoreapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.flowerstoreapp.Api.ApiService;
import com.example.flowerstoreapp.Api.ApiServiceFlowerFavorites;
import com.example.flowerstoreapp.Adapter.ProductAdapter;
import com.example.flowerstoreapp.Adapter.ProductFavoritesAdapter;
import com.example.flowerstoreapp.R;
import com.example.flowerstoreapp.model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    private RecyclerView rcvProducts;
    private RecyclerView rcvProductsFavorites;
    private List<Products> alFlower;
    private List<Products> alFlowerFavorites;
    ProductAdapter productAdapter;
    ProductFavoritesAdapter productFavoritesAdapter;
    ImageView iconLogin;
    ImageView iconSearch;
    String username,password;
    ImageView iconCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViewFlipper();
        anhXa();
        CauHinh();
        CallApiGetFlower();
        CallApiGetFlowerFavorites();
        iconLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentLogin();
                int flag = getIntent().getIntExtra("flag",0);
                if(flag == 200) {
                    IntentLoginSuccess();
                }

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

    private void CallApiGetFlower() {
        if(isConnected(this)){
            ApiService.apiService.getListFlowers()
                    .enqueue(new Callback<List<Products>>() {
                        @Override
                        public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                            alFlower = response.body();
                            productAdapter = new ProductAdapter(alFlower, MainActivity.this);
                            rcvProducts.setAdapter(productAdapter);
                        }

                        @Override
                        public void onFailure(Call<List<Products>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Call API ERROR", Toast.LENGTH_LONG).show();
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "Internet Erorr!", Toast.LENGTH_LONG).show();
        }
    }

    private void CallApiGetFlowerFavorites() {
        if(isConnected(this)){
            ApiServiceFlowerFavorites.apiServicesFavorites.getListFlowersFavorites()
                    .enqueue(new Callback<List<Products>>() {
                        @Override
                        public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                            alFlowerFavorites = response.body();
                            productFavoritesAdapter = new ProductFavoritesAdapter(alFlowerFavorites, MainActivity.this);
                            rcvProductsFavorites.setAdapter(productFavoritesAdapter);
                        }

                        @Override
                        public void onFailure(Call<List<Products>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "CALL API ERROR", Toast.LENGTH_LONG).show();
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "Internet Erorr!", Toast.LENGTH_LONG).show();
        }
    }
    private void IntentLogin(){
        Intent myintent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(myintent);
    }

    private void GetAccount(){
         username = getIntent().getStringExtra("username");
         password = getIntent().getStringExtra("password");

    }
    private void IntentLoginSuccess(){
        Intent myintent = new Intent(MainActivity.this,LoginSuccessActivity.class);
        GetAccount();
        myintent.putExtra("username",username);
        myintent.putExtra("password",password);
        startActivity(myintent);
    }

    private void IntentSearch(){
        Intent intent = new Intent(MainActivity.this,SearchActivity.class);
        startActivity(intent);
    }
    private void IntentCart(){
        Intent intent = new Intent(MainActivity.this,CartActivity.class);
        startActivity(intent);
    }
    private void setViewFlipper(){
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                viewFlipper.showNext();
                handler.postDelayed(this, 3000); // Chuyển đổi hình sau mỗi 2 giây
            }
        };
        viewFlipper = findViewById(R.id.viewFlipper);
        viewFlipper.addView(getImageView(R.drawable.flower1));
        viewFlipper.addView(getImageView(R.drawable.flower2));
        viewFlipper.addView(getImageView(R.drawable.flower3));
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
        runnable.run();
    }
    private ImageView getImageView(int imageResId) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(imageResId);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }
    private boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return (wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected());
    }
    private void CauHinh() {
        rcvProducts.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rcvProducts.setLayoutManager(gridLayoutManager);

        rcvProductsFavorites.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 2);
        gridLayoutManager1.setOrientation(GridLayoutManager.VERTICAL);
        rcvProductsFavorites.setLayoutManager(gridLayoutManager1);

    }

    private  void anhXa(){
        iconLogin = findViewById(R.id.login);
        rcvProducts = findViewById(R.id.recyclerView);
        rcvProductsFavorites = findViewById(R.id.recyclerView2);
        iconSearch = findViewById(R.id.imgSearch);
        iconCart = findViewById(R.id.imgCart);
    }
}