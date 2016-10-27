package com.example.gaby.tplabv.categoria;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.gaby.tplabv.R;
import com.example.gaby.tplabv.entidades.Dialogo;
import com.example.gaby.tplabv.lista.ListaActivity;

import java.io.File;

/**
 * Created by Gaby on 22/09/2016.
 */
public class CategoriaControlador implements View.OnClickListener, Handler.Callback
{
    private CategoriaModelo modelo;
    private CategoriaVista vista;
    private Activity act;
    private Boolean modificacion;
    private Uri uri;
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

    public void setUri(Uri uri)
    {
        this.uri = uri;
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btnCrear:
                if(vista.verificarCamposVacios())
                {
                    this.vista.actualizarModelo(this.uri);
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
                    break;
                }
            case R.id.btnCamara:
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File dir=new File(Environment.getExternalStorageDirectory(), "imagenDir");
                dir.mkdirs();
                File img=new File(dir, "imagen.jpg");
                this.uri= Uri.fromFile(img);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                this.act.startActivityForResult(intent, 2);
                break;
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
            intent.putExtra("foto", this.modelo.getCategoria().getFoto());
            intent.putExtra("indice", this.modelo.getIndice());
        }
        else
        {
            intent.putExtra("indice", -1);
        }
        act.setResult(0, intent);
        act.finish();
    }
    public void modificarImagen()
    {
        this.vista.actualizarImagen(this.uri);
    }
}
