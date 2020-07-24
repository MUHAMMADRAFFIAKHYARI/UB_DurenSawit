package android.example.ub_durensawit.Model;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("produk_id")
    private int produk_id;

    @SerializedName("deskripsi_produk")
    private String deskripsi_produk;

    @SerializedName("kategori_produk_id")
    private int kategori_produk_id;

    @SerializedName("nama_produk")
    private String nama_produk;

    @SerializedName("id_satuan")
    private int id_satuan;

    @SerializedName("kategori_id")
    private int kategori_id;

    @SerializedName("id_supplier")
    private int id_supplier;



}
