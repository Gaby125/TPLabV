package com.example.gaby.tplabv.entidades;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gaby.tplabv.R;
import com.example.gaby.tplabv.lista.ListaModelo;

/**
 * Created by Gaby on 21/09/2016.
 */
public class Adaptador extends RecyclerView.Adapter<ViewHolderCategoria>
{//Esta clase no está siendo utilizada por el momento, debido a que la clase "ListaVista" cumple el rol de Adapter sobre el ReciclerView (del Activity Lista)
    private ListaModelo modelo;
    public Adaptador(ListaModelo modelo)
    {
        this.modelo=modelo;
    }
    @Override
    public ViewHolderCategoria onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.categoria_layout, parent, false);
        ViewHolderCategoria vhCategoria=new ViewHolderCategoria(view);
        return vhCategoria;
    }

    @Override
    public void onBindViewHolder(ViewHolderCategoria holder, int position)
    {
        Categoria categoria=modelo.getCategorias().get(position);
        holder.getTvNombre().setText(categoria.getNombre());
        holder.getTvDescripcion().setText(categoria.getDescripcion());
        holder.getChkFav().setChecked(categoria.getFav());
        holder.setIndice(position);
    }

    @Override
    public int getItemCount()
    {
        return this.modelo.getCategorias().size();
    }
}
