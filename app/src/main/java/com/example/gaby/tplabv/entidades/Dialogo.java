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
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        Bundle args=this.getArguments();
        builder.setTitle(args.get("titulo").toString());
        builder.setMessage(args.get("mensaje").toString());
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
