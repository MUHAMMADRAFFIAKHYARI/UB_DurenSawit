package android.example.ub_durensawit.Model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Cart")
public class Cart {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "produk_id")
    private int produk_id;

    @ColumnInfo(name = "jumlah")
    private int jumlah;
    @ColumnInfo(name = "nama")
    private String nama;

    @ColumnInfo(name = "harga")
    private int harga;

    private int total_harga;

    public Cart(int produk_id,String nama,int jumlah,int harga) {
        this.produk_id = produk_id;
        this.nama = nama;
        this.jumlah = jumlah;
        this.harga = harga;
    }

    public Cart(){

    }

    public int getProduk_id() {
        return produk_id;
    }

    public String getNama(){
        return nama;
    }



    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getTotal_harga(){ return total_harga; }

    public int get_Harga(){
        return harga;
    }
    public void setHarga(int harga){
        this.harga = harga;
    }
}
