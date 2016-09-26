package com.example.gaby.tplabv.registro;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.gaby.tplabv.R;
import com.example.gaby.tplabv.entidades.Dialogo;
import com.example.gaby.tplabv.login.LoginActivity;

/**
 * Created by Gaby on 21/09/2016.
 */
public class RegistroControlador implements View.OnClickListener
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
                        act.finish();
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
}
