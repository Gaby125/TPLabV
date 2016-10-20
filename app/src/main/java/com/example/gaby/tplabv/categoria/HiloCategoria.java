package com.example.gaby.tplabv.categoria;

import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.gaby.tplabv.entidades.AdministradorHttp;
import com.example.gaby.tplabv.entidades.Categoria;

/**
 * Created by Gaby on 20/10/2016.
 */
public class HiloCategoria extends Thread
{
    private Categoria categoria;
    private String key;
    private Handler handler;
    public HiloCategoria(Categoria categoria, String key, Handler handler)
    {
        super();
        this.categoria=categoria;
        this.key=key;
        this.handler=handler;
    }
    @Override
    public void run()
    {
        try
        {
            AdministradorHttp adm=new AdministradorHttp();
            Uri.Builder parametros=new Uri.Builder();
            parametros.appendQueryParameter("titulo", this.categoria.getNombre());
            parametros.appendQueryParameter("descripcion", this.categoria.getDescripcion());
            byte[] datos=adm.enviarInformacion("http://lkdml.myq-see.com/categorias", parametros, this.key);
            String json=new String(datos, "UTF-8");
            Message msg=new Message();
            msg.obj= Categoria.obtenerError(json);
            this.handler.sendMessage(msg);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
