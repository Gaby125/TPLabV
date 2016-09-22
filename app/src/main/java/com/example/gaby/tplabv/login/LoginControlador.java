package com.example.gaby.tplabv.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import com.example.gaby.tplabv.lista.ListaActivity;
import com.example.gaby.tplabv.R;
import com.example.gaby.tplabv.registro.RegistroActivity;

/**
 * Created by Gaby on 21/09/2016.
 */
public class LoginControlador implements View.OnClickListener
{
    private LoginModelo modelo;
    private LoginVista vista;
    private Activity act;
    public LoginControlador(LoginModelo modelo, Activity act)
    {
        this.modelo=modelo;
        this.act=act;
    }
    public void setVista(LoginVista vista)
    {
        this.vista=vista;
    }
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btnRegistrar:
                Intent intentReg=new Intent(act, RegistroActivity.class);
                act.startActivity(intentReg);
                break;
            case R.id.btnIngresar:
                this.vista.actualizarModelo();
                Intent intentIng=new Intent(act, ListaActivity.class);
                intentIng.putExtra("user", modelo.getUser());
                act.startActivity(intentIng);
                SharedPreferences pref=act.getSharedPreferences("config", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                if(modelo.getRecordar())
                {
                    editor.putBoolean("recordar", true);
                    editor.putString("user", modelo.getUser());
                    editor.putString("pass", modelo.getPass());
                    editor.commit();
                }
                else
                {
                    editor.putBoolean("recordar", false);
                    editor.commit();
                }
                act.finish();
                break;
        }
    }
}
