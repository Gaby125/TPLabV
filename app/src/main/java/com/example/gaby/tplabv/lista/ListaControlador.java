package com.example.gaby.tplabv.lista;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.gaby.tplabv.R;
import com.example.gaby.tplabv.categoria.CategoriaActivity;
import com.example.gaby.tplabv.entidades.Categoria;
import com.example.gaby.tplabv.entidades.Constante;
import com.example.gaby.tplabv.entidades.ViewHolderCategoria;

import java.util.ArrayList;
import java.util.List;

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
                int id=Integer.parseInt(vhCategoria.getTvId().getText().toString());
                Intent intentCat=new Intent(act, CategoriaActivity.class);
                intentCat.putExtra("id", id);
                intentCat.putExtra("nombre", vhCategoria.getTvNombre().getText());
                intentCat.putExtra("descripcion", vhCategoria.getTvDescripcion().getText());
                intentCat.putExtra("favorita", vhCategoria.getChkFav().isChecked());
                if(vhCategoria.getImgFoto().getTag()!=null)
                {
                    intentCat.putExtra("foto", vhCategoria.getImgFoto().getTag().toString());
                }
                intentCat.putExtra("indice", this.buscarIndice(id));
                intentCat.putExtra("key", this.act.getIntent().getExtras().getString("key", ""));
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
            String tipo=extras.getString("tipo", "error");
            Categoria categoria=new Categoria(extras.getString("nombre", "Por defecto"), extras.getString("descripcion", "Por defecto"), extras.getBoolean("favorita", false), extras.getString("foto", null), extras.getInt("id", -1));
            switch(tipo)
            {
                case "alta":
                    this.modelo.getCategorias().add(categoria);
                    this.vista.notifyItemInserted(this.modelo.getCategorias().size()-1);
                    break;
                case "modificacion":
                    this.modelo.getCategorias().set(indice, categoria);
                    this.vista.notifyItemChanged(indice);
                    break;
                case "baja":
                    this.modelo.getCategorias().remove(indice.intValue());
                    this.vista.notifyItemRemoved(indice);
                    break;
                case "error":
                    Log.d("Error", "Ha ocurrido un error");
                    break;
            }
            /*for(Categoria cate:this.modelo.getCategorias())
            {
                Log.d("Indice", cate.
            }*/
            //this.vista.notifyDataSetChanged();
        }
    }
    public void cargarLista()
    {
        ArrayList<Categoria> categorias=new ArrayList<Categoria>();
        categorias.add(new Categoria("Categoría 1", "cat1", true, null, 2));
        modelo.setCategorias(categorias);//cargo una lista auxiliar para que no haga referencia a nulo al cargar el recycler view
        HiloLista hilo=new HiloLista(this.act.getIntent().getExtras().getString("key", ""), new Handler(this));
        hilo.start();
    }
    private int buscarIndice(int id)
    {
        List<Categoria> categorias=this.modelo.getCategorias();
        for(int i=0;i<categorias.size();i++)
        {
            if(id==categorias.get(i).getId())
            {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean handleMessage(Message msg)
    {
        switch(msg.arg1)
        {
            case Constante.CARGA_CATEGORIAS:
                ArrayList<Categoria> categorias=(ArrayList<Categoria>)msg.obj;
                this.modelo.setCategorias(categorias);
                this.vista.notifyDataSetChanged();
                return true;
            case Constante.CARGA_FOTO:
                Object[] objetos=(Object[])msg.obj;
                ((ImageView)objetos[0]).setImageBitmap(((Bitmap)objetos[1]));
                return true;
            default:
                return false;
        }
    }
}
