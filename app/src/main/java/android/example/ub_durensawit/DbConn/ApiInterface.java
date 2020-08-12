package android.example.ub_durensawit.DbConn;
import android.example.ub_durensawit.Model.Order;
import android.example.ub_durensawit.Model.Product;
import android.example.ub_durensawit.Model.User;

import org.json.JSONStringer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    //For User Class
 /*
    @GET("retrofit/POST/.php")
    Call<List<User>> getUser();
    */
    @FormUrlEncoded
    @POST("addUser.php")
    public Call<User> createUser(
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("password") String password,
            @Field("NoTelpon") String NoTelpon
    );

    @FormUrlEncoded
    @POST("emailCheck.php")
    public Call<User> emailCheck(
    @Field("email") String email
    );

//For Product Class
    @FormUrlEncoded
    @GET("getProduct.php")
    public Call<Product> getProduct(
            @Query("id") int id
    );

    @GET("getAllProducts.php")
    public Call<List<Product>> getAllProducts();

    



    //for user login
    @FormUrlEncoded
    @POST("login.php")
    public Call<User> login(
            @Field("email") String email,
            @Field("password")String password
    );

    @FormUrlEncoded
    @POST("addOrder.php")
    public Call<Order> createOrder(
            @Field("tanggal_order") String tanggal_order,
            @Field("user_id") int user_id,
            @Field("status") String status

    );

}



