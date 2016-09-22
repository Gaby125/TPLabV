package com.example.gaby.tplabv.categoria;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.gaby.tplabv.R;
import com.example.gaby.tplabv.lista.ListaActivity;

/**
 * Created by Gaby on 22/09/2016.
 */
public class CategoriaControlador implements View.OnClickListener
{
    private CategoriaModelo modelo;
    private CategoriaVista vista;
    private Activity act;
    public CategoriaControlador(CategoriaModelo modelo, Activity act)
    {
        this.modelo=modelo;
        this.act=act;
    }
    public void setVista(CategoriaVista vista)
    {
        this.vista=vista;
    }
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btnCrear:
                this.vista.actualizarModelo();
                Intent intent=new Intent();
                intent.putExtra("nombre", this.modelo.getCategoria().getNombre());
                intent.putExtra("descripcion", this.modelo.getCategoria().getDescripcion());
                intent.putExtra("favorita", this.modelo.getCategoria().getFav());
                intent.putExtra("indice", this.modelo.getIndice());
                act.setResult(0, intent);
                act.finish();
                break;
        }
    }
}
