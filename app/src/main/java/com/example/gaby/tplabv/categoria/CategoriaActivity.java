package com.example.gaby.tplabv.categoria;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.gaby.tplabv.R;
import com.example.gaby.tplabv.entidades.MenuActivity;
import com.example.gaby.tplabv.login.LoginActivity;

public class CategoriaActivity extends MenuActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        ActionBar actionBar=this.getSupportActionBar();
        actionBar.setTitle(R.string.categoria);
        actionBar.setDisplayHomeAsUpEnabled(true);
        CategoriaModelo modelo=new CategoriaModelo();
        CategoriaControlador control=new CategoriaControlador(modelo, this);
        CategoriaVista vista=new CategoriaVista(modelo, this, control);
        control.setVista(vista);
    }
}
