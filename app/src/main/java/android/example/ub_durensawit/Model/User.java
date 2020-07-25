package android.example.ub_durensawit.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("User_id")
    private int User_id;

    @SerializedName("Role_id")
    private int Role_id;

    @SerializedName("city")
    private String city;

    @SerializedName("country")
    private String country;

    @SerializedName("Address")
    private String address;

    @SerializedName("nama")
    private String nama;

    @SerializedName("NoTelpon")
    private String NoTelpon;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("value")
    @Expose
    private String value;

    @SerializedName("message")
    @Expose
    private String message;

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
