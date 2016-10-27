package com.example.gaby.tplabv.lista;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.gaby.tplabv.R;
import com.example.gaby.tplabv.categoria.CategoriaActivity;
import com.example.gaby.tplabv.entidades.Categoria;
import com.example.gaby.tplabv.entidades.ViewHolderCategoria;

import java.util.ArrayList;

/**
 * Created by Gaby on 21/09/2016.
 */
public class ListaControlador implements View.OnClickListener, Handler.Callback
{
    private ListaModelo modelo;
    private ListaVista vista;
    private Activity act;
    public ListaControlador(ListaModelo modelo, Activity act)
    {
        this.modelo=modelo;
        this.act=act;
    }
    public void setVista(ListaVista vista)
    {
        this.vista=vista;
    }
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btnAgregar:
                Intent intent=new Intent(act, CategoriaActivity.class);
                intent.putExtra("key", this.act.getIntent().getExtras().getString("key", ""));
                act.startActivityForResult(intent, 0);
                break;
            default:
                ViewHolderCategoria vhCategoria=new ViewHolderCategoria(v);
                //vhCategoria.getAdapterPosition();
                Intent intentCat=new Intent(act, CategoriaActivity.class);
                intentCat.putExtra("nombre", vhCategoria.getTvNombre().getText());
                intentCat.putExtra("descripcion", vhCategoria.getTvDescripcion().getText());
                intentCat.putExtra("favorita", vhCategoria.getChkFav().isChecked());
                if(vhCategoria.getImgFoto().getTag()!=null)
                {
                    intentCat.putExtra("foto", vhCategoria.getImgFoto().getTag().toString());
                }
                intentCat.putExtra("indice", Integer.parseInt(vhCategoria.getTvIndice().getText().toString()));
                act.startActivityForResult(intentCat, 0);
                break;
        }
    }
    public void modificarLista(Intent datos)
    {
        if(datos!=null)
        {
            Bundle extras=datos.getExtras();
            Integer indice=extras.getInt("indice", -1);
            Categoria categoria=new Categoria(extras.getString("nombre", "Por defecto"), extras.getString("descripcion", "Por defecto"), extras.getBoolean("favorita", false), extras.getString("foto", null));
            if(indice==-1)
            {
                this.modelo.getCategorias().add(categoria);
            }
            else
            {
                this.modelo.getCategorias().set(indice, categoria);
            }
            /*for(Categoria cate:this.modelo.getCategorias())
            {
                Log.d("Indice", cate.
            }*/
            this.vista.notifyDataSetChanged();
        }
    }
    public void cargarLista()
    {
        ArrayList<Categoria> categorias=new ArrayList<Categoria>();
        categorias.add(new Categoria("Categoría 1", "cat1", true, null));
        modelo.setCategorias(categorias);//cargo una lista auxiliar para que no haga referencia a nulo al cargar el recycler view
        HiloLista hilo=new HiloLista(this.act.getIntent().getExtras().getString("key", ""), new Handler(this));
        hilo.start();
    }

    @Override
    public boolean handleMessage(Message msg)
    {
        ArrayList<Categoria> categorias=(ArrayList<Categoria>)msg.obj;
        this.modelo.setCategorias(categorias);
        this.vista.notifyDataSetChanged();
        return true;
    }
}
