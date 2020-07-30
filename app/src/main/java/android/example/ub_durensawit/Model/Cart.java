package android.example.ub_durensawit.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Cart")
public class Cart {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "produk_id")
    public int produk_id;


    @ColumnInfo(name="nama_produk")
   public String namaProduk;

    @ColumnInfo(name = "jumlah")
    public int jumlah;

    @ColumnInfo(name = "harga")
    public int harga;

    @ColumnInfo(name = "kategori_id")
    public int kategori_id;

    @ColumnInfo(name = "nama_kategori")
    public String namaKategori;

    public Cart(int produk_id, String namaProduk, int jumlah, int harga, int kategori_id, String namaKategori) {
        this.produk_id = produk_id;
        this.namaProduk = namaProduk;
        this.jumlah = jumlah;
        this.harga = harga;
        this.kategori_id = kategori_id;
        this.namaKategori = namaKategori;
    }
}
