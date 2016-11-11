package com.example.gaby.tplabv.categoria;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gaby.tplabv.R;
import com.example.gaby.tplabv.entidades.Categoria;

/**
 * Created by Gaby on 22/09/2016.
 */
public class CategoriaVista
{
    public static Bitmap imagen;
    private CategoriaModelo modelo;
    private EditText txtNombre;
    private EditText txtDescripcion;
    private CheckBox chkFav;
    private Button btnCrear;
    private TextView tvTitulo;
    private ImageButton btnCamara;
    private ImageView imgCamara;
    private Button btnEliminar;
    public CategoriaVista(CategoriaModelo modelo, Activity act, CategoriaControlador control)
    {
        this.modelo=modelo;
        this.txtNombre=(EditText)act.findViewById(R.id.txtNombre);
        this.txtDescripcion=(EditText)act.findViewById(R.id.txtDescripcion);
        this.chkFav=(CheckBox)act.findViewById(R.id.chkFav);
        this.btnCrear=(Button)act.findViewById(R.id.btnCrear);
        this.btnCrear.setOnClickListener(control);
        this.tvTitulo=(TextView)act.findViewById(R.id.tvTitulo);
        this.btnCamara=(ImageButton)act.findViewById(R.id.btnCamara);
        this.btnCamara.setOnClickListener(control);
        this.imgCamara=(ImageView)act.findViewById(R.id.imgCamara);
        this.btnEliminar=(Button)act.findViewById(R.id.btnEliminar);
        this.btnEliminar.setOnClickListener(control);
        this.completarCampos(act, control);
    }
    private void completarCampos(Activity act, CategoriaControlador control)
    {
        Intent intent=act.getIntent();
        Bundle extras=intent.getExtras();
        if(!extras.getString("nombre", "").equals(""))
        {
            this.btnCrear.setText(R.string.modificar);
            this.tvTitulo.setText(R.string.cate_modif);
            this.btnEliminar.setVisibility(View.VISIBLE);
            control.setId(extras.getInt("id", -1));
            this.txtNombre.setText(extras.getString("nombre", ""));
            this.txtDescripcion.setText(extras.getString("descripcion", ""));
            this.chkFav.setChecked(extras.getBoolean("favorita", false));
            String foto=extras.getString("foto", null);
            control.setFoto(foto);
            if(foto!=null)
            {
                this.imgCamara.setImageBitmap(imagen);
            }
            this.modelo.setIndice(extras.getInt("indice", 0));
            control.setTipo("modificacion");
        }
    }
    public void actualizarModelo(String foto, Integer id)
    {
        Categoria categoria=new Categoria(this.txtNombre.getText().toString(), this.txtDescripcion.getText().toString(), this.chkFav.isChecked(), foto, id);
        this.modelo.setCategoria(categoria);
    }
    public boolean verificarCamposVacios()
    {
        return !(this.txtNombre.getText().toString().equals("") || this.txtDescripcion.getText().toString().equals(""));
    }
    public void actualizarImagen(Bitmap bitmap)
    {
        this.imgCamara.setImageBitmap(bitmap);
    }
}
