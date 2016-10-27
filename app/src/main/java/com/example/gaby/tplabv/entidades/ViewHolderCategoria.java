package com.example.gaby.tplabv.entidades;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gaby.tplabv.R;

/**
 * Created by Gaby on 21/09/2016.
 */
public class ViewHolderCategoria extends RecyclerView.ViewHolder// implements View.OnClickListener
{
    private TextView tvNombre;
    private TextView tvDescripcion;
    private CheckBox chkFav;
    private ImageView imgFoto;
    private TextView tvIndice;
    private Integer indice;
    public ViewHolderCategoria(View itemView)
    {
        super(itemView);
        this.tvNombre=(TextView)itemView.findViewById(R.id.tvNombre);
        this.tvDescripcion=(TextView)itemView.findViewById(R.id.tvDescripcion);
        this.chkFav=(CheckBox)itemView.findViewById(R.id.chkFav);
        this.imgFoto=(ImageView) itemView.findViewById(R.id.imgFoto);
        this.tvIndice=(TextView)itemView.findViewById(R.id.tvIndice);
        //itemView.setOnClickListener(this);
    }

    public TextView getTvNombre()
    {
        return tvNombre;
    }

    public TextView getTvDescripcion()
    {
        return tvDescripcion;
    }

    public CheckBox getChkFav()
    {
        return chkFav;
    }

    public ImageView getImgFoto()
    {
        return imgFoto;
    }

    public TextView getTvIndice()
    {
        return tvIndice;
    }

    public Integer getIndice()
    {
        return indice;
    }

    public void setIndice(Integer indice)
    {
        this.indice = indice;
    }

    /*@Override
    public void onClick(View v)
    {
        Log.d("Categoría", this.tvNombre.getText()+" ");
    }*/
}
