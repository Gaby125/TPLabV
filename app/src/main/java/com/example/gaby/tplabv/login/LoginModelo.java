package com.example.gaby.tplabv.login;

/**
 * Created by Gaby on 21/09/2016.
 */
public class LoginModelo
{
    private String user;
    private String pass;
    private Boolean recordar;
    public LoginModelo()
    {
    }
    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public Boolean getRecordar()
    {
        return recordar;
    }

    public void setRecordar(Boolean recordar)
    {
        this.recordar = recordar;
    }
}
