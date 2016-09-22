package com.example.gaby.tplabv.lista;

import com.example.gaby.tplabv.entidades.Categoria;

import java.util.List;

/**
 * Created by Gaby on 21/09/2016.
 */
public class ListaModelo
{
    private List<Categoria> categorias;
    public ListaModelo()
    {
    }

    public List<Categoria> getCategorias()
    {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias)
    {
        this.categorias = categorias;
    }
}
