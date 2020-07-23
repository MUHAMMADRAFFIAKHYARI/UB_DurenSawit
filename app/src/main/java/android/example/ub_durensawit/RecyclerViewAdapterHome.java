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
        holder.textView.setText( productName[position]);
        holder.textView.setText( productCategory[position]);
        holder.textView.setText("Rp. " + productPrice[position]);
    }

    @Override
    public int getItemCount() {
        return productImage.length;
    }

    public class TheViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public TheViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.product_image);
            textView = itemView.findViewById(R.id.product_name);
            textView = itemView.findViewById(R.id.product_category);
            textView = itemView.findViewById(R.id.product_price);

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
