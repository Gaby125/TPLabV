package com.example.gaby.tplabv.categoria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.gaby.tplabv.R;
import com.example.gaby.tplabv.entidades.Dialogo;
import com.example.gaby.tplabv.lista.ListaActivity;

/**
 * Created by Gaby on 22/09/2016.
 */
public class CategoriaControlador implements View.OnClickListener, Handler.Callback
{
    private CategoriaModelo modelo;
    private CategoriaVista vista;
    private Activity act;
    private Boolean modificacion;
    public CategoriaControlador(CategoriaModelo modelo, Activity act)
    {
        this.modelo=modelo;
        this.act=act;
        this.modificacion=false;
    }
    public void setVista(CategoriaVista vista)
    {
        this.vista=vista;
    }

    public void setModificacion(Boolean modificacion)
    {
        this.modificacion = modificacion;
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btnCrear:
                if(vista.verificarCamposVacios())
                {
                    this.vista.actualizarModelo();
                    if(!this.modificacion)
                    {
                        HiloCategoria hilo=new HiloCategoria(this.modelo.getCategoria(), act.getIntent().getExtras().getString("key"), new Handler(this));
                        hilo.start();
                    }
                    else
                    {
                        this.enviarDatos(false);
                    }
                    break;
                }
                else
                {
                    Dialogo dialogo=new Dialogo();
                    Bundle args=new Bundle();
                    args.putString("titulo", act.getString(R.string.error));
                    args.putString("mensaje", act.getString(R.string.msj_vacio));
                    dialogo.setArguments(args);
                    dialogo.show(((FragmentActivity)act).getSupportFragmentManager(), "Categoria");
                }
        }
    }

    @Override
    public boolean handleMessage(Message msg)
    {
        this.enviarDatos((boolean)msg.obj);
        return true;
    }
    private void enviarDatos(boolean error)
    {
        Intent intent=new Intent();
        if(!error)
        {
            intent.putExtra("nombre", this.modelo.getCategoria().getNombre());
            intent.putExtra("descripcion", this.modelo.getCategoria().getDescripcion());
            intent.putExtra("favorita", this.modelo.getCategoria().getFav());
            intent.putExtra("indice", this.modelo.getIndice());
        }
        else
        {
            intent.putExtra("indice", -1);
        }
        act.setResult(0, intent);
        act.finish();
    }
}
