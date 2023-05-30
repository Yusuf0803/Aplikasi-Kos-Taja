package com.mykostaja.kostaja;

public class User {
    public String Usertype, Username, Phone, Email, Password,Repassword;
    private String key;

    public User(String Usertype, String Username, String Phone, String Email, String Password, String Repassword) {
        this.Usertype = Usertype;
        this.Username = Username;
        this.Phone = Phone;
        this.Email = Email;
        this.Password = Password;
        this.Repassword = Repassword;
    }

    public String getUsertype() {
        return Usertype;
    }

    public void setUsertype(String usertype) {
        Usertype = usertype;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRepassword() {
        return Repassword;
    }

    public void setRepassword(String repassword) {
        Repassword = repassword;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}