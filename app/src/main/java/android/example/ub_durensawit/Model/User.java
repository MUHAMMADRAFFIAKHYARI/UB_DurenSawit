package android.example.ub_durensawit.Model;

public class User {
private String nama,phone,email,password;



    public User(String nama, String phone, String email, String password){
    this.nama = nama;
    this.phone = phone;
    this.email = email;
    this.password = password;
}

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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


}
