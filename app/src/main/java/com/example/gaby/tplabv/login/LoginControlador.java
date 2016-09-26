package com.example.gaby.tplabv.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.example.gaby.tplabv.entidades.Dialogo;
import com.example.gaby.tplabv.lista.ListaActivity;
import com.example.gaby.tplabv.R;
import com.example.gaby.tplabv.registro.RegistroActivity;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Gaby on 21/09/2016.
 */
public class LoginControlador implements View.OnClickListener
{
    private LoginModelo modelo;
    private LoginVista vista;
    private Activity act;
   // private static Boolean inicioApp;
    /*static
    {
        Log.d("inicioAppStatic", "Static");
        LoginControlador.inicioApp=false;
    }*/
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
                if(vista.verificarCamposVacios())
                {
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
                    }
                    else
                    {
                        editor.putBoolean("recordar", false);
                    }
                    editor.putBoolean("logueado", true);
                    editor.commit();
                    act.finish();
                }
                else
                {
                    Dialogo dialogo=new Dialogo();
                    dialogo.show(((FragmentActivity)act).getSupportFragmentManager(), "Login");
                }
                break;
        }
    }
    public void verificarInicio()
    {
        Intent intent=this.act.getIntent();
        Bundle extras=intent.getExtras();
        if(extras==null || !extras.getBoolean("Logout", false))
        {
            Intent intentIng=new Intent(act, ListaActivity.class);
            intentIng.putExtra("user", modelo.getUser());
            act.startActivity(intentIng);
            act.finish();
        }
    }
}
