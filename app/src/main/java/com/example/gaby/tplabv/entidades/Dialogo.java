package com.example.gaby.tplabv.entidades;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.gaby.tplabv.R;

/**
 * Created by Gaby on 25/09/2016.
 */
public class Dialogo extends DialogFragment
{
    private String titulo;
    private String mensaje;
    public Dialogo()
    {
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.error);
        builder.setMessage(R.string.msj_vacio);
        builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });
        AlertDialog alertDialog=builder.create();
        return alertDialog;
    }
}
