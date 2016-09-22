package com.example.gaby.tplabv.categoria;

import com.example.gaby.tplabv.entidades.Categoria;

/**
 * Created by Gaby on 22/09/2016.
 */
public class CategoriaModelo
{
    private Categoria categoria;
    public Integer indice;
    public CategoriaModelo()
    {
    }

    public Categoria getCategoria()
    {
        return categoria;
    }

    public void setCategoria(Categoria categoria)
    {
        this.categoria = categoria;
    }

    public Integer getIndice()
    {
        return indice;
    }

    public void setIndice(Integer indice)
    {
        this.indice = indice;
    }
}
