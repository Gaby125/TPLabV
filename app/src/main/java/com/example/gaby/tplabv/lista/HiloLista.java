package com.example.gaby.tplabv.lista;

import android.os.Handler;
import android.os.Message;

import com.example.gaby.tplabv.entidades.AdministradorHttp;
import com.example.gaby.tplabv.entidades.Categoria;
import com.example.gaby.tplabv.entidades.Usuario;

/**
 * Created by Gaby on 20/10/2016.
 */
public class HiloLista extends Thread
{
    private String key;
    private Handler handler;
    public HiloLista(String key, Handler handler)
    {
        super();
        this.key=key;
        this.handler=handler;
    }
    @Override
    public void run()
    {
        try
        {
            AdministradorHttp adm=new AdministradorHttp();
            byte[] datos=adm.obtenerInformacion("http://lkdml.myq-see.com/categorias", this.key);
            String json=new String(datos, "UTF-8");
            Message msg=new Message();
            msg.obj= Categoria.obtenerCategoriasJSON(json);
            this.handler.sendMessage(msg);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
