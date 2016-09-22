package com.example.gaby.tplabv.lista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gaby.tplabv.R;
import com.example.gaby.tplabv.categoria.CategoriaActivity;
import com.example.gaby.tplabv.entidades.Categoria;
import com.example.gaby.tplabv.entidades.MenuActivity;
import com.example.gaby.tplabv.login.LoginActivity;

import java.util.ArrayList;

public class ListaActivity extends MenuActivity
{
    private ListaControlador controlador;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        ActionBar actionBar=this.getSupportActionBar();
        actionBar.setTitle(R.string.lista);
        actionBar.setDisplayHomeAsUpEnabled(true);
        ListaModelo modelo=new ListaModelo();
        ArrayList<Categoria> categorias=new ArrayList<Categoria>();
        categorias.add(new Categoria("Categoría 1", "cat1", true));
        categorias.add(new Categoria("Categoría 2", "cat2", false));
        categorias.add(new Categoria("Categoría 3", "cat3", true));
        modelo.setCategorias(categorias);
        ListaControlador control=new ListaControlador(modelo, this);
        ListaVista vista=new ListaVista(modelo, this, control);
        control.setVista(vista);
        this.controlador=control;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch(menuItem.getItemId())
        {
            case R.id.favoritos:
                Log.d("ClickMenu", "Favortios");
                return true;
            case R.id.logout:
                Intent i2=new Intent(this, LoginActivity.class);
                startActivity(i2);
                this.finish();
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return false;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        this.controlador.modificarLista(data);
    }
}
