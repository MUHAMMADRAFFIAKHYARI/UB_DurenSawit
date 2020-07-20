package android.example.ub_durensawit.DbConn;
import android.example.ub_durensawit.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
   /*
    @GET("retrofit/POST/.php")
    Call<List<User>> getUser();
    */


    @FormUrlEncoded
    @Headers( "Content-Type: application/json" )
    @POST("/addUser.php")
    public Call<User> insertUser(
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("password") String password,
            @Field("NoTelpon") String NoTelpon
            );



}
