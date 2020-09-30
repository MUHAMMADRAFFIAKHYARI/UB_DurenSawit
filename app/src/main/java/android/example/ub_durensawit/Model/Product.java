package android.example.ub_durensawit.Model;

import android.media.Image;

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

    @SerializedName("harga")
    private int harga;

    @SerializedName("kategori_id")
    private int kategori_id;


    @SerializedName("id_supplier")
    private int id_supplier;

    private String ImageUrl;

    public int getHarga() {
        return harga;
    }

    public int getProduk_id() {
        return produk_id;
    }

    public String getDeskripsi_produk() {
        return deskripsi_produk;
    }

    public int getKategori_produk_id() {
        return kategori_produk_id;
    }

    public String getNama_produk() {
        return nama_produk;
    }

    public int getId_satuan() {
        return id_satuan;
    }

    public int getKategori_id() {
        return kategori_id;
    }

    public int getId_supplier() {
        return id_supplier;
    }

    public String getImageUrl(){
        //Sementara
        String format =(getProduk_id() == 1) ? ".png" : ".jpg";
        ImageUrl = "https://budiganteng.000webhostapp.com/product/"+Integer.toString(getProduk_id()) + format;
        return ImageUrl;
    }
}
