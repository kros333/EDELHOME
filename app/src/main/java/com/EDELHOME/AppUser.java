package com.EDELHOME;

import com.google.gson.annotations.SerializedName;

public class AppUser
{
//    @SerializedName("userid")
//    private long userid;
    @SerializedName("login")
    private String login;
    @SerializedName("pass")
    private String pass;
    @SerializedName("isadmin")
    private Boolean isadmin;

    AppUser()
    {

    }

    AppUser(String login, String pass, Boolean isadmin)
    {
//        this.userid = userid;
        this.login = login;
        this.pass = pass;
        this.isadmin = isadmin;
    }

//    public long getUserid()
//    {
//        return this.userid;
//    }

    public String getLogin()
    {
        return this.login;
    }

    public String getPass()
    {
        return this.pass;
    }

    public Boolean getIsadmin()
    {
        return this.isadmin;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public void setIsadmin(Boolean isadmin)
    {
        this.isadmin = isadmin;
    }
}
