package com.example.gaby.tplabv.entidades;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gaby on 20/10/2016.
 */
public class Usuario
{
    private String nombre;
    private String apellido;
    private String user;
    private String mail;
    private String pass;
    private String key;
    public Usuario()
    {
    }
    public Usuario(String nombre, String apellido, String user, String mail, String pass)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.user = user;
        this.mail = mail;
        this.pass = pass;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getApellido()
    {
        return apellido;
    }

    public String getUser()
    {
        return user;
    }

    public String getMail()
    {
        return mail;
    }

    public String getPass()
    {
        return pass;
    }

    public String getKey()
    {
        return key;
    }

    public static Usuario obtenerUsuarioJSON(String json)throws JSONException
    {
        Usuario usuario=new Usuario();
        JSONObject objeto=new JSONObject(json);
        if(!objeto.getBoolean("error"))
        {
            usuario.nombre=objeto.getString("name");
            usuario.mail=objeto.getString("email");
            usuario.key=objeto.getString("apiKey");
            return usuario;
        }
        else
        {
            return null;
        }
    }

    public static String obtenerMensaje(String json)throws JSONException
    {
        JSONObject objeto=new JSONObject(json);
        return objeto.getString("message");
    }
}
