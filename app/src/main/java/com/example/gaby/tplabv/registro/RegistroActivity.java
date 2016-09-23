package com.example.gaby.tplabv.registro;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.gaby.tplabv.R;
import com.example.gaby.tplabv.entidades.MenuActivity;

public class RegistroActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ActionBar actionBar=this.getSupportActionBar();
        actionBar.setTitle(R.string.registrar);
        actionBar.setDisplayHomeAsUpEnabled(true);
        RegistroModelo modelo=new RegistroModelo();
        RegistroControlador control=new RegistroControlador(modelo, this);
        RegistroVista vista=new RegistroVista(modelo, this, control);
        control.setVista(vista);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch(menuItem.getItemId())
        {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
