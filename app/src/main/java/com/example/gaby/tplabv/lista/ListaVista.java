package com.example.gaby.tplabv.lista;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gaby.tplabv.entidades.Adaptador;
import com.example.gaby.tplabv.entidades.Categoria;
import com.example.gaby.tplabv.R;
import com.example.gaby.tplabv.entidades.ViewHolderCategoria;

/**
 * Created by Gaby on 21/09/2016.
 */
public class ListaVista extends RecyclerView.Adapter<ViewHolderCategoria>
{
    private ListaModelo modelo;
    private ListaControlador control;
    private RecyclerView rvCategoria;
    private Button btnAgregar;
    public ListaVista(ListaModelo modelo, Activity act, ListaControlador control)
    {
        this.modelo=modelo;
        this.control=control;
        this.rvCategoria=(RecyclerView)act.findViewById(R.id.rvCategoria);
        //Adaptador adaptador=new Adaptador(this.modelo);
        this.rvCategoria.setAdapter(this);
        this.rvCategoria.setLayoutManager(new LinearLayoutManager(act));
        this.btnAgregar=(Button)act.findViewById(R.id.btnAgregar);
        this.btnAgregar.setOnClickListener(control);
    }
    public ViewHolderCategoria onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.categoria_layout, parent, false);
        view.setOnClickListener(control);
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
        holder.getTvIndice().setText(String.valueOf(position));
        holder.setIndice(position);
    }

    @Override
    public int getItemCount()
    {
        return this.modelo.getCategorias().size();
    }

}
