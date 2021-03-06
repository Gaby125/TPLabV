package com.example.gaby.tplabv.entidades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gaby.tplabv.R;
import com.example.gaby.tplabv.categoria.CategoriaActivity;
import com.example.gaby.tplabv.lista.ListaActivity;
import com.example.gaby.tplabv.login.LoginActivity;

/**
 * Created by Gaby on 22/09/2016.
 */
public class MenuActivity extends AppCompatActivity
{
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch(menuItem.getItemId())
        {
            case R.id.categorias:
                Intent i=new Intent(this, ListaActivity.class);
                i.putExtra("key", this.getIntent().getExtras().getString("key", ""));
                startActivity(i);
                return true;
            case R.id.favoritos:
                Log.d("ClickMenu", this.getString(R.string.favs));
                return true;
            case R.id.logout:
                Intent i2=new Intent(this, LoginActivity.class);
                i2.putExtra("Logout", true);
                SharedPreferences pref=this.getSharedPreferences("config", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
                editor.putBoolean("logueado", false);
                editor.commit();
                this.finishAffinity();
                startActivity(i2);
                this.finish();
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
