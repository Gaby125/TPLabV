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
    private String tipo;
    public HiloCategoria(Categoria categoria, String key, Handler handler, String tipo)
    {
        super();
        this.categoria=categoria;
        this.key=key;
        this.handler=handler;
        this.tipo=tipo;
    }
    @Override
    public void run()
    {
        Message msg=new Message();
        try
        {
            AdministradorHttp adm=new AdministradorHttp();
            Uri.Builder parametros=new Uri.Builder();
            String url="http://lkdml.myq-see.com/categorias";
            String metodo="";
            switch(tipo)
            {
                case "alta":
                    parametros.appendQueryParameter("titulo", this.categoria.getNombre());
                    parametros.appendQueryParameter("descripcion", this.categoria.getDescripcion());
                    metodo="POST";
                    break;
                case "baja":
                    url+="/"+this.categoria.getId();
                    metodo="DELETE";
                    break;
                case "modificacion":
                    parametros.appendQueryParameter("titulo", this.categoria.getNombre());
                    parametros.appendQueryParameter("descripcion", this.categoria.getDescripcion());
                    parametros.appendQueryParameter("categoria_id", this.categoria.getId().toString());
                    metodo="PUT";
                    break;
            }
            byte[] datos=adm.enviarInformacion(url, parametros, this.key, metodo);
            String json=new String(datos, "UTF-8");
            msg.obj= Categoria.obtenerError(json);
            if(tipo.equals("alta"))
            {
                msg.arg1=Categoria.obtenerId(json);
            }
            this.handler.sendMessage(msg);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            msg.obj=true;
            this.handler.sendMessage(msg);
        }
    }
}
