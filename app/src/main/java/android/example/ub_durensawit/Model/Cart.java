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
    private int produk_id;


    @ColumnInfo(name="nama_produk")
    String namaProduk;

    @ColumnInfo(name = "jumlah")
    int jumlah;

    @ColumnInfo(name = "harga")
    int harga;

    @ColumnInfo(name = "kategori_id")
    int kategori_id;

    @ColumnInfo(name = "nama_kategori")
    String namaKategori;





}
