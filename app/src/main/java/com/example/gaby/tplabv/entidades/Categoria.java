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
    private String foto;
    private Integer id;
    private Boolean carga;
    public Categoria()
    {
        this.carga=false;
    }
    public Categoria(String nombre, String descripcion, Boolean fav, String foto, Integer id)
    {
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.fav=fav;
        this.foto=foto;
        this.id=id;
        this.carga=false;
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

    public String getFoto()
    {
        return foto;
    }

    public void setFoto(String foto)
    {
        this.foto = foto;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Boolean getCarga()
    {
        return carga;
    }

    public void setCarga(Boolean carga)
    {
        this.carga = carga;
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
                categoria.id=personaJson.getInt("id");
                categoria.nombre=personaJson.getString("titulo");
                categoria.descripcion=personaJson.getString("desc");
                categoria.foto=personaJson.getString("url_foto");
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

    public static Categoria obtenerCategoriaJSON(String json)throws JSONException
    {
        JSONObject objeto=new JSONObject(json);
        if(!objeto.getBoolean("error"))
        {
            Categoria categoria=new Categoria();
            categoria.id=objeto.getInt("id");
            categoria.nombre=objeto.getString("titulo");
            categoria.descripcion=objeto.getString("desc");
            categoria.foto=objeto.getString("url_foto");
            categoria.fav=false;
            return categoria;
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

    public static int obtenerId(String json)throws JSONException
    {
        JSONObject objeto=new JSONObject(json);
        return objeto.getInt("categoria_id");
    }
}
