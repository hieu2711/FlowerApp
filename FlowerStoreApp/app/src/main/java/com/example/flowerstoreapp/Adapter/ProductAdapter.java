package com.example.flowerstoreapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flowerstoreapp.Activity.CartActivity;
import com.example.flowerstoreapp.R;
import com.example.flowerstoreapp.model.Products;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    public ProductAdapter(List<Products> listProducts, Context context) {
        this.listProducts = listProducts;
        this.context = context;
    }

    private List<Products> listProducts;
    private Context context;


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder,@SuppressLint("RecyclerView") int position) {
        Products products = listProducts.get(position);
        if(products == null){
            return;
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        Picasso.with(context).load(products.getUrlImage()).into(holder.imgProduct);
        holder.txtNameProduct.setText(String.valueOf(listProducts.get(position).getName()));
        if (holder.txtPrice != null) {
            holder.txtPrice.setText(decimalFormat.format(listProducts.get(position).getUnitPrice()) + " VNĐ");
        }
        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Products flower = listProducts.get(position);
                // Chuyển sang DetailItemActivity và chuyển dữ liệu qua Intent
                Intent intent = new Intent(view.getContext(), CartActivity.class);
                intent.putExtra("flower_name", flower.getName());
                intent.putExtra("flower_price", flower.getUnitPrice());
                intent.putExtra("flower_img", flower.getUrlImage());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listProducts != null){
            return listProducts.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgProduct;
        private TextView txtNameProduct;
        private TextView txtPrice;
        private Button btnAddToCart;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.ImgProduct);
            txtNameProduct = itemView.findViewById(R.id.txtProduct);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }


}
