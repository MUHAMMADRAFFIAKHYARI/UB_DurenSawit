package android.example.ub_durensawit.Adapter;

import android.content.Context;
import android.example.ub_durensawit.Model.Cart;
import android.example.ub_durensawit.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartViewHolder> {
    private final LayoutInflater mInflater;
    private List<Cart> mCarts; // Cached copy of words
    private static ClickListener clickListener;

   public CartListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.cartrecyclerview_item, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        if (mCarts != null) {
            Cart current = mCarts.get(position);
            String txt =current.getNama() +" = " + current.getJumlah();
            holder.cartItemView.setText(txt);
        } else {
            // Covers the case of data not being ready yet.
            holder.cartItemView.setText("Belum ada Item yang dimasukkan");
        }
    }

    /**
     * Associates a list of words with this adapter
     */
    public void setCarts(List<Cart> carts) {
        mCarts = carts;
        notifyDataSetChanged();
    }

    public List<Cart> getCarts(){
        return mCarts;
    }

    /**
     * getItemCount() is called many times, and when it is first called,
     * mWords has not been updated (means initially, it's null, and we can't return null).
     */
    @Override
    public int getItemCount() {
        if (mCarts != null)
            return mCarts.size();
        else return 0;
    }

    /**
     * Gets the word at a given position.
     * This method is useful for identifying which word
     * was clicked or swiped in methods that handle user events.
     *
     * @param position The position of the word in the RecyclerView
     * @return The word at the given position
     */
    public Cart getCartAtPosition(int position) {
        return mCarts.get(position);
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        private final TextView cartItemView;

        private CartViewHolder(View itemView) {
            super(itemView);
            cartItemView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(view, getAdapterPosition());
                }
            });
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        CartListAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, int position);
    }
}
