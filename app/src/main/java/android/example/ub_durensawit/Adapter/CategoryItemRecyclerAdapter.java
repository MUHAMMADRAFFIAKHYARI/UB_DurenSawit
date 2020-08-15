package android.example.ub_durensawit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.example.ub_durensawit.ItemBuyActivity;
import android.example.ub_durensawit.Model.CategoryItem;
import android.example.ub_durensawit.Model.Product;
import android.example.ub_durensawit.R;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class CategoryItemRecyclerAdapter extends RecyclerView.Adapter<CategoryItemRecyclerAdapter.CategoryItemViewHolder> {
    private Context context;
    private List<CategoryItem> categoryItemList;
    private List<Product> productList;

    public CategoryItemRecyclerAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.category_row_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position) {

        Product product = productList.get(position);
        ImageView imageView = holder.itemImage;
        TextView productName = holder.productName;
        TextView productPrice = holder.productPrice;
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();
        Glide.with(context)
                .load(product.getImageUrl())
                .placeholder(circularProgressDrawable)
                .transition(DrawableTransitionOptions.withCrossFade())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        //circularProgressDrawable.stop();
                        holder.progressBar.setVisibility(View.GONE);


                        return false;
                    }
                })
                .into(imageView);
        productName.setText(product.getNama_produk());
        productPrice.setText(Integer.toString(product.getHarga()));
        holder.idProduk = product.getProduk_id();


    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static final class CategoryItemViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;
        TextView productName;
        TextView productPrice;
        ProgressBar progressBar;
        int idProduk;


        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            progressBar = itemView.findViewById(R.id.progress);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), ItemBuyActivity.class);
                    i.putExtra("idProduk", idProduk);
                    i.putExtra("namaProduk",productName.getText().toString());
                    i.putExtra("hargaProduk",productPrice.getText().toString());


//                    i.putExtra("product name", productName[getAdapterPosition()]);
//                    i.putExtra("product category", productCategory[getAdapterPosition()]);
//                    i.putExtra("product price", productPrice[getAdapterPosition()]);
                    view.getContext().startActivity(i);
                }
            });

        }
    }
}
