package com.example.gaby.tplabv.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.gaby.tplabv.entidades.Dialogo;
import com.example.gaby.tplabv.entidades.Usuario;
import com.example.gaby.tplabv.lista.ListaActivity;
import com.example.gaby.tplabv.R;
import com.example.gaby.tplabv.registro.RegistroActivity;

/**
 * Created by Gaby on 21/09/2016.
 */
public class LoginControlador implements View.OnClickListener, Handler.Callback
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
                act.startActivityForResult(intentReg, 1);
                break;
            case R.id.btnIngresar:
                if(vista.verificarCamposVacios())
                {
                    this.vista.actualizarModelo();
                    this.verificarLogin();
                }
                else
                {
                    this.generarDialogo(this.act.getString(R.string.error), this.act.getString(R.string.msj_vacio));
                }
                break;
        }
    }
    public void verificarInicio(SharedPreferences pref)
    {
        Intent intent=this.act.getIntent();
        Bundle extras=intent.getExtras();
        if(extras==null || !extras.getBoolean("Logout", false))
        {
            Intent intentIng=new Intent(act, ListaActivity.class);
            intentIng.putExtra("user", modelo.getUser());
            intentIng.putExtra("key", pref.getString("key", ""));
            act.startActivity(intentIng);
            act.finish();
        }
    }
    private void verificarLogin()
    {
        HiloLogin hilo=new HiloLogin(this.modelo.getUser(), this.modelo.getPass(), new Handler(this));
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
        Usuario usuario=(Usuario)msg.obj;
        this.continuarLogin(usuario);
        return true;
    }
    private void continuarLogin(Usuario usuario)
    {
        if(usuario!=null)
        {
            Intent intentIng=new Intent(act, ListaActivity.class);
            intentIng.putExtra("nombre", usuario.getNombre());
            intentIng.putExtra("mail", usuario.getMail());
            intentIng.putExtra("key", usuario.getKey());
            act.startActivity(intentIng);
            SharedPreferences pref=act.getSharedPreferences("config", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=pref.edit();
            if(modelo.getRecordar())
            {
                editor.putBoolean("recordar", true);
                editor.putString("user", modelo.getUser());
                editor.putString("key", usuario.getKey());
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
            this.generarDialogo(this.act.getString(R.string.error), this.act.getString(R.string.msj_login));
        }
    }
}
