package com.example.flowerstoreapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.flowerstoreapp.Adapter.ProductAdapter;
import com.example.flowerstoreapp.Api.ApiService;
import com.example.flowerstoreapp.R;
import com.example.flowerstoreapp.model.Category;
import com.example.flowerstoreapp.model.Products;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    ImageView iconHome,iconCate,iconLogin;
    Spinner spinner;
    List<Category> listCate;
    List<Products> alFlower;
    ProductAdapter productAdapter;
    RecyclerView recyclerView;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        anhXa();
        CauHinh();
        iconHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentHome();
            }
        });
        iconLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentLogin();
            }
        });
        iconCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentCate();
            }
        });
        CallApiGetFlower();
        GetCLickOnSearchView();
        CallApiGetCategory();
        EventSpinner();
    }
    private  void anhXa(){
        spinner = findViewById(R.id.spinnerCate);
        iconHome = findViewById(R.id.imgHome);
        recyclerView = findViewById(R.id.recyclerView3);
        searchView = findViewById(R.id.searchView);
        iconCate = findViewById(R.id.imgCart);
        iconLogin = findViewById(R.id.imgLogin);
    }

    private void EventSpinner(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                  CallApiGetFlower();
                }
                else {
                    CallApiGetFlowerByCategoryId(i);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void GetCLickOnSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<Products> listFilter = new ArrayList<>();
                for(Products flower: alFlower){
                    if(flower.getName().toLowerCase().contains(newText.toLowerCase())){
                        listFilter.add(flower);
                    }
                }
                LoadData(listFilter);
                return false;
            }
        });
    }

    private void LoadData(List<Products> flowerList){
        ProductAdapter flowerAdapter = new ProductAdapter(flowerList, getApplicationContext());
        recyclerView.setAdapter(flowerAdapter);
    }
    private void CallApiGetFlower() {
            ApiService.apiService.getListFlowers()
                    .enqueue(new Callback<List<Products>>() {
                        @Override
                        public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                            alFlower = response.body();
                            productAdapter = new ProductAdapter(alFlower, SearchActivity.this);
                            recyclerView.setAdapter(productAdapter);
                            }


                        @Override
                        public void onFailure(Call<List<Products>> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Call API ERROR", Toast.LENGTH_LONG).show();
                        }
                    });
    }


    private void CallApiGetCategory() {
        ApiService.apiService.getListCategory()
                .enqueue(new Callback<List<Category>>() {
                    @Override
                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                        listCate = response.body();
                        if (listCate != null) {
                            List<String> cate = new ArrayList<>();
                            cate.add("Tất cả");
                            for (Category category : listCate) {
                                String categoryName = category.getName();
                                cate.add(categoryName);
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(SearchActivity.this, android.R.layout.simple_spinner_item, cate);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinner.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Category>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"CALL API ERROR",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void CallApiGetFlowerByCategoryId(int id) {
        ApiService.apiService.getListFlowerByIdCate(id).enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                alFlower = response.body();
                productAdapter = new ProductAdapter(alFlower, SearchActivity.this);
                recyclerView.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Call API ERROR", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void intentHome(){
        Intent myintent = new Intent(SearchActivity.this,MainActivity.class);
        startActivity(myintent);
    }
    private void intentCate(){
        Intent myintent = new Intent(SearchActivity.this,CartActivity.class);
        startActivity(myintent);
    }
    private void intentLogin(){
        Intent myintent = new Intent(SearchActivity.this,LoginActivity.class);
        startActivity(myintent);
    }
    private void CauHinh() {
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

    }

}
