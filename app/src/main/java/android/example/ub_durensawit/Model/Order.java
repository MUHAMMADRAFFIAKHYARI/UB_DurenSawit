package android.example.ub_durensawit.Model;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Order {
    @SerializedName("order_id")
    private int order_id;

    @SerializedName("tanggal_order")
    private String tanggal_order;

    @SerializedName("user_id")
    private int user_id;

    @SerializedName("status")
    private String status;

}
