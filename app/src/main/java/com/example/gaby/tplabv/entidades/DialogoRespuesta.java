package com.example.gaby.tplabv.entidades;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.gaby.tplabv.R;

/**
 * Created by Gaby on 03/11/2016.
 */
public class DialogoRespuesta extends DialogFragment
{
    private DialogInterface.OnClickListener escuchador;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        Bundle args=this.getArguments();
        builder.setTitle(args.get("titulo").toString());
        builder.setMessage(args.get("mensaje").toString());
        builder.setPositiveButton(R.string.aceptar, this.escuchador);
        builder.setNegativeButton(R.string.cancelar, this.escuchador);
        AlertDialog alertDialog=builder.create();
        return alertDialog;
    }

    public void setEscuchador(DialogInterface.OnClickListener escuchador)
    {
        this.escuchador = escuchador;
    }
}
