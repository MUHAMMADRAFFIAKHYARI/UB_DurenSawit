package android.example.ub_durensawit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.example.ub_durensawit.ItemBuyActivity;
import android.example.ub_durensawit.Model.CategoryItem;
import android.example.ub_durensawit.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryItemRecyclerAdapter extends RecyclerView.Adapter<CategoryItemRecyclerAdapter.CategoryItemViewHolder> {
    private Context context;
    private List<CategoryItem> categoryItemList;

    public CategoryItemRecyclerAdapter(Context context, List<CategoryItem> categoryItemList) {
        this.context = context;
        this.categoryItemList = categoryItemList;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryItemViewHolder(LayoutInflater.from(context).inflate(R.layout.category_row_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position) {

        holder.itemImage.setImageResource(categoryItemList.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return categoryItemList.size();
    }

    public static final class CategoryItemViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;

        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(view.getContext(), ItemBuyActivity.class);
                    //i.putExtra("product image", productImage);
//                    i.putExtra("position image", productImage[getAdapterPosition()]);
//                    i.putExtra("product name", productName[getAdapterPosition()]);
//                    i.putExtra("product category", productCategory[getAdapterPosition()]);
//                    i.putExtra("product price", productPrice[getAdapterPosition()]);
                    view.getContext().startActivity(i);
                }
            });

        }
    }
}
