package com.example.android;

public class Login_Info {
    private String name,email,img_url;

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getImg_url() {
        return img_url;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    private static Login_Info instance = null;


    public static synchronized Login_Info getInstance(){

        if(null==instance){

            instance = new Login_Info();

        }
        return instance;

    }

}
