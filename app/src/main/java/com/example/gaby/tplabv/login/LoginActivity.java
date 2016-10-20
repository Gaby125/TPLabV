package com.example.gaby.tplabv.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gaby.tplabv.R;

public class LoginActivity extends AppCompatActivity
{
    private LoginControlador control;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginModelo modelo=new LoginModelo();
        this.control=new LoginControlador(modelo, this);
        LoginVista vista=new LoginVista(modelo, this, control);
        control.setVista(vista);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null)
        {
            this.control.generarDialogo(this.getString(R.string.respuesta), data.getExtras().getString("mensaje"));
        }
    }
}
