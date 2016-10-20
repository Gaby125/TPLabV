package com.example.gaby.tplabv.entidades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gaby on 21/09/2016.
 */
public class Categoria
{
    private String nombre;
    private String descripcion;
    private Boolean fav;
    public Categoria()
    {
    }
    public Categoria(String nombre, String descripcion, Boolean fav)
    {
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.fav=fav;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public Boolean getFav()
    {
        return fav;
    }

    public void setFav(Boolean fav)
    {
        this.fav = fav;
    }

    public static List<Categoria> obtenerCategoriasJSON(String json)throws JSONException
    {
        List<Categoria> categorias=new ArrayList<Categoria>();
        JSONObject objeto=new JSONObject(json);
        if(!objeto.getBoolean("error"))
        {
            JSONArray arrayPersonas=objeto.getJSONArray("categorias");
            for(int i=0;i<arrayPersonas.length();i++)
            {
                Categoria categoria=new Categoria();
                JSONObject personaJson=arrayPersonas.getJSONObject(i);
                categoria.nombre=personaJson.getString("titulo");
                categoria.descripcion=personaJson.getString("desc");
                categoria.fav=false;
                categorias.add(categoria);
            }
            return categorias;
        }
        else
        {
            return null;
        }
    }

    public static boolean obtenerError(String json)throws JSONException
    {
        JSONObject objeto=new JSONObject(json);
        return objeto.getBoolean("error");
    }
}
