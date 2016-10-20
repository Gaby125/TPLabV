package com.example.gaby.tplabv.login;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.gaby.tplabv.entidades.AdministradorHttp;
import com.example.gaby.tplabv.entidades.Usuario;

/**
 * Created by Gaby on 20/10/2016.
 */
public class HiloLogin extends Thread
{
    private String user;
    private String pass;
    private Handler handler;
    public HiloLogin(String user, String pass, Handler handler)
    {
        super();
        this.user=user;
        this.pass=pass;
        this.handler=handler;
    }
    @Override
    public void run()
    {
        try
        {
            AdministradorHttp adm=new AdministradorHttp();
            Uri.Builder parametros=new Uri.Builder();
            parametros.appendQueryParameter("email", this.user);
            parametros.appendQueryParameter("password", this.pass);
            byte[] datos=adm.enviarInformacion("http://lkdml.myq-see.com/login", parametros, null);
            String json=new String(datos, "UTF-8");
            Message msg=new Message();
            msg.obj= Usuario.obtenerUsuarioJSON(json);
            this.handler.sendMessage(msg);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
