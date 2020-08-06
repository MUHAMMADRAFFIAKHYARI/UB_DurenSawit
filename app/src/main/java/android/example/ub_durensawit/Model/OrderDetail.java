package android.example.ub_durensawit.Model;

import com.google.gson.annotations.SerializedName;

public class OrderDetail {
    @SerializedName("order_detail_id")
    private int order_detail_id;

    @SerializedName("order_id")
    private int order_id;

    @SerializedName("produk_id")
    private int produk_id;

    @SerializedName("jumlah")
    private int  jumlah;

    @SerializedName("harga")
    private int harga;



}
