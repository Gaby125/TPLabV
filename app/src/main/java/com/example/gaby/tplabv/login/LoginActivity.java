package com.example.gaby.tplabv.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gaby.tplabv.R;

public class LoginActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginModelo modelo=new LoginModelo();
        LoginControlador control=new LoginControlador(modelo, this);
        LoginVista vista=new LoginVista(modelo, this, control);
        control.setVista(vista);
    }
}
