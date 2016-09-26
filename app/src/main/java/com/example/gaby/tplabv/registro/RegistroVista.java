package com.example.gaby.tplabv.registro;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import com.example.gaby.tplabv.R;

/**
 * Created by Gaby on 21/09/2016.
 */
public class RegistroVista
{
    private RegistroModelo modelo;
    private Button btnAlta;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtUser;
    private EditText txtMail;
    private EditText txtPass;
    private EditText txtRePass;
    public RegistroVista(RegistroModelo modelo, Activity act, RegistroControlador control)
    {
        this.modelo=modelo;
        this.btnAlta=(Button)act.findViewById(R.id.btnAlta);
        this.btnAlta.setOnClickListener(control);
        this.txtNombre=(EditText)act.findViewById(R.id.txtNombre);
        this.txtApellido=(EditText)act.findViewById(R.id.txtApellido);
        this.txtUser=(EditText)act.findViewById(R.id.txtUser);
        this.txtMail=(EditText)act.findViewById(R.id.txtMail);
        this.txtPass=(EditText)act.findViewById(R.id.txtPass);
        this.txtRePass=(EditText)act.findViewById(R.id.txtRePass);
    }
    public void actualizarModelo()
    {
        this.modelo.setNombre(this.txtNombre.getText().toString());
        this.modelo.setApellido(this.txtApellido.getText().toString());
        this.modelo.setUser(this.txtUser.getText().toString());
        this.modelo.setMail(this.txtMail.getText().toString());
        this.modelo.setPass(this.txtPass.getText().toString());
        this.modelo.setRePass(this.txtRePass.getText().toString());
    }
    public boolean verificarCamposVacios()
    {
        return !(this.txtNombre.getText().toString().equals("") ||
                this.txtApellido.getText().toString().equals("") ||
                this.txtUser.getText().toString().equals("") ||
                this.txtMail.getText().toString().equals("") ||
                this.txtPass.getText().toString().equals("") ||
                this.txtRePass.getText().toString().equals(""));
    }
    public boolean verificarContraseña()
    {
        if(this.txtPass.getText().toString().compareTo(this.txtRePass.getText().toString())==0)
        {
            return true;
        }
        return false;
    }
}
