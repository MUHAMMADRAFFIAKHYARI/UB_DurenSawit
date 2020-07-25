package android.example.ub_durensawit.DbConn;
import android.example.ub_durensawit.Model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
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


}
