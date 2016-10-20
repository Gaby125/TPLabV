package com.example.gaby.tplabv.login;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.gaby.tplabv.R;

/**
 * Created by Gaby on 21/09/2016.
 */
public class LoginVista
{
    private LoginModelo modelo;
    private Button btnRegistrar;
    private Button btnIngresar;
    private EditText txtUser;
    private EditText txtPass;
    private CheckBox chkRecordar;
    public LoginVista(LoginModelo modelo, Activity act, LoginControlador control)
    {
        this.modelo=modelo;
        this.btnIngresar=(Button)act.findViewById(R.id.btnIngresar);
        this.btnIngresar.setOnClickListener(control);
        this.btnRegistrar=(Button)act.findViewById(R.id.btnRegistrar);
        this.btnRegistrar.setOnClickListener(control);
        this.txtUser=(EditText)act.findViewById(R.id.txtUser);
        this.txtPass=(EditText)act.findViewById(R.id.txtPass);
        this.chkRecordar=(CheckBox)act.findViewById(R.id.chkRecordar);
        this.chkRecordar.setChecked(false);
        this.cargarPreferences(act, control);
    }
    private void cargarPreferences(Activity act, LoginControlador control)
    {
        SharedPreferences pref=act.getSharedPreferences("config", Context.MODE_PRIVATE);
        if(pref.getBoolean("recordar", false))
        {
            this.txtUser.setText(pref.getString("user", ""));
            this.chkRecordar.setChecked(true);
            this.actualizarModelo();
            if(pref.getBoolean("logueado", false))
            {
                control.verificarInicio(pref);
            }
        }
    }
    public void actualizarModelo()
    {
        this.modelo.setUser(this.txtUser.getText().toString());
        this.modelo.setPass(this.txtPass.getText().toString());
        this.modelo.setRecordar(chkRecordar.isChecked());
    }
    public boolean verificarCamposVacios()
    {
        return !(this.txtUser.getText().toString().equals("") || this.txtPass.getText().toString().equals(""));
    }
}
