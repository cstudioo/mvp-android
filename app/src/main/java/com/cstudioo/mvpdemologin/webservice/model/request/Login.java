package com.cstudioo.mvpdemologin.webservice.model.request;

public class Login {

    String email;
    String password;
    String fbid;

    public Login(){}

    public Login(String mEmail, String mPassword, String mFbid){
        email = mEmail;
        password = mPassword;
        fbid = mFbid;
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

    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fbid) {
        this.fbid = fbid;
    }
}
