package com.example.gaby.tplabv.categoria;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.example.gaby.tplabv.R;
import com.example.gaby.tplabv.entidades.Dialogo;
import com.example.gaby.tplabv.entidades.DialogoRespuesta;
import com.example.gaby.tplabv.lista.ListaActivity;

import java.io.File;

/**
 * Created by Gaby on 22/09/2016.
 */
public class CategoriaControlador implements View.OnClickListener, Handler.Callback, DialogInterface.OnClickListener
{
    private CategoriaModelo modelo;
    private CategoriaVista vista;
    private Activity act;
    private String tipo;
    private Uri uri;
    private Integer id;
    public CategoriaControlador(CategoriaModelo modelo, Activity act)
    {
        this.modelo=modelo;
        this.act=act;
        this.tipo="alta";
        this.id=-1;
    }
    public void setVista(CategoriaVista vista)
    {
        this.vista=vista;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public void setUri(Uri uri)
    {
        this.uri = uri;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btnCrear:
                if(vista.verificarCamposVacios())
                {
                    this.iniciarHilo();
                    break;
                }
                else
                {
                    this.generarDialogo(act.getString(R.string.error), act.getString(R.string.msj_vacio));
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
            case R.id.btnEliminar:
                DialogoRespuesta dialogo=new DialogoRespuesta();
                dialogo.setEscuchador(this);
                Bundle args=new Bundle();
                args.putString("titulo", act.getString(R.string.advertencia));
                args.putString("mensaje", act.getString(R.string.msj_eliminar));
                dialogo.setArguments(args);
                dialogo.show(((FragmentActivity)act).getSupportFragmentManager(), "Registro");
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        switch(which)
        {
            case AlertDialog.BUTTON_POSITIVE:
                this.tipo="baja";
                this.iniciarHilo();
                break;
            case AlertDialog.BUTTON_NEGATIVE:
                break;
        }
    }

    private void iniciarHilo()
    {
        this.vista.actualizarModelo(this.uri, this.id);
        HiloCategoria hilo=new HiloCategoria(this.modelo.getCategoria(), act.getIntent().getExtras().getString("key"), new Handler(this), this.tipo);
        hilo.start();
    }

    public void generarDialogo(String titulo, String mensaje)
    {
        Dialogo dialogo=new Dialogo();
        Bundle args=new Bundle();
        args.putString("titulo", titulo);
        args.putString("mensaje", mensaje);
        dialogo.setArguments(args);
        dialogo.show(((FragmentActivity)act).getSupportFragmentManager(), "Registro");
    }

    @Override
    public boolean handleMessage(Message msg)
    {
        if(this.tipo.equals("alta"))
        {
            this.modelo.getCategoria().setId(msg.arg1);
        }
        Object[] objetos=(Object[])msg.obj;
        boolean error=(boolean)objetos[0];
        String foto=(String)objetos[1];
        this.enviarDatos(error, foto);
        return true;
    }
    private void enviarDatos(boolean error, String foto)
    {
        Intent intent=new Intent();
        if(!error)
        {
            intent.putExtra("id", this.modelo.getCategoria().getId());
            intent.putExtra("nombre", this.modelo.getCategoria().getNombre());
            intent.putExtra("descripcion", this.modelo.getCategoria().getDescripcion());
            intent.putExtra("favorita", this.modelo.getCategoria().getFav());
            intent.putExtra("foto", foto);
            intent.putExtra("indice", this.modelo.getIndice());
            intent.putExtra("tipo", this.tipo);
            act.setResult(0, intent);
            act.finish();
        }
        else
        {
            this.generarDialogo(act.getString(R.string.error), act.getString(R.string.msj_server));
        }
    }
    public void modificarImagen()
    {
        this.vista.actualizarImagen(this.uri);
    }
}
