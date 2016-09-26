package com.example.gaby.tplabv.registro;

/**
 * Created by Gaby on 21/09/2016.
 */
public class RegistroModelo
{
    private String nombre;
    private String apellido;
    private String user;
    private String mail;
    private String pass;
    private String rePass;
    public RegistroModelo()
    {
    }
    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getApellido()
    {
        return apellido;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getPass()
    {
        return pass;
    }

    public void setPass(String pass)
    {
        this.pass = pass;
    }

    public String getRePass()
    {
        return rePass;
    }

    public void setRePass(String rePass)
    {
        this.rePass = rePass;
    }
}
