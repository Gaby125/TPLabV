package com.example.gaby.tplabv.lista;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.example.gaby.tplabv.entidades.AdministradorHttp;
import com.example.gaby.tplabv.entidades.Categoria;
import com.example.gaby.tplabv.entidades.Constante;
import com.example.gaby.tplabv.entidades.Usuario;

/**
 * Created by Gaby on 20/10/2016.
 */
public class HiloLista extends Thread
{
    private String key;
    private Handler handler;
    private ImageView imagen;
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
            Message msg=new Message();
            if(imagen==null)
            {
                byte[] datos=adm.obtenerInformacion("http://lkdml.myq-see.com/categorias", this.key);
                String json=new String(datos, "UTF-8");
                msg.arg1=Constante.CARGA_CATEGORIAS;
                msg.obj= Categoria.obtenerCategoriasJSON(json);
            }
            else
            {
                byte[] imagenBytes=adm.obtenerInformacion("http://lkdml.myq-see.com/"+this.imagen.getTag().toString(), null);
                Bitmap imagenBitMap = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);
                //this.imagen.setImageBitmap(imagenBitMap);
                msg.arg1=Constante.CARGA_FOTO;
                msg.obj=new Object[]{this.imagen, imagenBitMap};
            }
            this.handler.sendMessage(msg);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setImagen(ImageView imagen)
    {
        this.imagen = imagen;
    }
}
