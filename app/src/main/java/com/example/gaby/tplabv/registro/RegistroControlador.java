package com.example.gaby.tplabv.registro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.gaby.tplabv.R;
import com.example.gaby.tplabv.entidades.Dialogo;
import com.example.gaby.tplabv.entidades.Usuario;

/**
 * Created by Gaby on 21/09/2016.
 */
public class RegistroControlador implements View.OnClickListener, Handler.Callback
{
    private RegistroModelo modelo;
    private RegistroVista vista;
    private Activity act;
    public RegistroControlador(RegistroModelo modelo, Activity act)
    {
        this.modelo=modelo;
        this.act=act;
    }
    public void setVista(RegistroVista vista)
    {
        this.vista=vista;
    }
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btnAlta:
                if(this.vista.verificarCamposVacios())
                {
                    if(this.vista.verificarContraseña())
                    {
                        this.vista.actualizarModelo();
                        Usuario usuario=new Usuario(modelo.getNombre(), modelo.getApellido(), modelo.getUser(), modelo.getMail(), modelo.getPass());
                        HiloRegistro hilo=new HiloRegistro(usuario, new Handler(this));
                        hilo.start();
                    }
                    else
                    {
                        this.generarDialogo(act.getString(R.string.error), act.getString(R.string.msj_repass));
                    }
                }
                else
                {
                    this.generarDialogo(act.getString(R.string.error), act.getString(R.string.msj_vacio));
                }
                break;
        }
    }
    private void generarDialogo(String titulo, String mensaje)
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
        Intent intent=new Intent();
        intent.putExtra("mensaje", msg.obj.toString());
        act.setResult(1, intent);
        act.finish();
        return true;
    }
}
