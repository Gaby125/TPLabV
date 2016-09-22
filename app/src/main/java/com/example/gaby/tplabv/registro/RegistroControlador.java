package com.example.gaby.tplabv.registro;

import android.app.Activity;
import android.view.View;

import com.example.gaby.tplabv.R;
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
                act.finish();
                break;
        }
    }
}
