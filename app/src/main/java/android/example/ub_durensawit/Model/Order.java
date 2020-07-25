package android.example.ub_durensawit.Model;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Order {
    @SerializedName("order_detail_id")
    private int order_detail_id;

    @SerializedName("order_id")
    private int order_id;

    @SerializedName("produk_id")
    private int produk_id;

    @SerializedName("jumlah")
    private int jumlah;

    @SerializedName("tanggal_order")
    private Date tanggal_order;

    @SerializedName("user_id")
    private int user_id;

    @SerializedName("status")
    private String status;

    @SerializedName("harga")
    private int harga;



}
