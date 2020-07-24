package android.example.ub_durensawit;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapterHome extends RecyclerView.Adapter<RecyclerViewAdapterHome.TheViewHolder> {


    int [] productImage;
    String [] productName;
    String [] productCategory;
    String [] productPrice;

    public RecyclerViewAdapterHome(int[] productImage, String[] productName, String[] productCategory, String[] productPrice) {
        this.productImage = productImage;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
    }



    @NonNull
    @Override
    public TheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_all_item,parent,false);
        TheViewHolder theViewHolder = new TheViewHolder(view);
        return theViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TheViewHolder holder, int position) {
        holder.imageView.setImageResource(productImage[position]);
        holder.nama_product.setText(productName[position]);
        holder.category_product.setText(productCategory[position]);
        holder.price_product.setText("Rp. " + productPrice[position]);
    }

    @Override
    public int getItemCount() {
        return productImage.length;
    }

    public class TheViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nama_product, category_product, price_product;

        public TheViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.product_image);
            nama_product = itemView.findViewById(R.id.product_name);
            category_product = itemView.findViewById(R.id.product_category);
            price_product = itemView.findViewById(R.id.product_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), ItemBuyActivity.class);
                    //i.putExtra("product image", productImage);
                    i.putExtra("position image", productImage[getAdapterPosition()]);
                    i.putExtra("product name", productName[getAdapterPosition()]);
                    i.putExtra("product category", productCategory[getAdapterPosition()]);
                    i.putExtra("product price", productPrice[getAdapterPosition()]);
                    view.getContext().startActivity(i);
                }
            });


        }
    }
}
