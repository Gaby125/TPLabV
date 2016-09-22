package com.example.gaby.tplabv.entidades;

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
}
