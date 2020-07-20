package android.example.ub_durensawit.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("NoTelpon")
    @Expose
    private String NoTelpon;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("message")
    @Expose
    private String message;



    public User(String nama, String email, String password ,String NoTelpon ){
    this.nama = nama;
    this.NoTelpon = NoTelpon;
    this.email = email;
    this.password = password;
}

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoTelpon() {
        return NoTelpon;
    }

    public void setNoTelpon(String NoTelpon) {
        this.NoTelpon = NoTelpon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }
    public String getValue(){
        return value;
    }
}
