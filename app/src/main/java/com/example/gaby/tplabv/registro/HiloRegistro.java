package com.example.gaby.tplabv.registro;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import com.example.gaby.tplabv.entidades.AdministradorHttp;
import com.example.gaby.tplabv.entidades.Usuario;

/**
 * Created by Gaby on 20/10/2016.
 */
public class HiloRegistro extends Thread
{
    private Usuario usuario;
    private Handler handler;
    public HiloRegistro(Usuario usuario, Handler handler)
    {
        super();
        this.usuario=usuario;
        this.handler=handler;
    }
    @Override
    public void run()
    {
        try
        {
            AdministradorHttp adm=new AdministradorHttp();
            Uri.Builder parametros=new Uri.Builder();
            parametros.appendQueryParameter("nombre", this.usuario.getNombre());
            parametros.appendQueryParameter("apellido", this.usuario.getApellido());
            parametros.appendQueryParameter("usuario", this.usuario.getUser());
            parametros.appendQueryParameter("email", this.usuario.getMail());
            parametros.appendQueryParameter("password", this.usuario.getPass());
            byte[] datos=adm.enviarInformacion("http://lkdml.myq-see.com/register", parametros, null);
            String json=new String(datos, "UTF-8");
            Message msg=new Message();
            msg.obj= Usuario.obtenerMensaje(json);
            this.handler.sendMessage(msg);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
