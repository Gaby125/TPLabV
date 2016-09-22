package com.example.gaby.tplabv.registro;

import android.app.Activity;
import android.widget.Button;

import com.example.gaby.tplabv.R;

/**
 * Created by Gaby on 21/09/2016.
 */
public class RegistroVista
{
    private RegistroModelo modelo;
    private Button btnAlta;
    public RegistroVista(RegistroModelo modelo, Activity act, RegistroControlador control)
    {
        this.modelo=modelo;
        this.btnAlta=(Button)act.findViewById(R.id.btnAlta);
        this.btnAlta.setOnClickListener(control);
    }
}
