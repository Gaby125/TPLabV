package com.example.gaby.tplabv.entidades;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by alumno on 06/10/2016.
 */
public class AdministradorHttp
{
    public byte[] obtenerInformacion(String cadenaUrl, String key)throws IOException
    {
        URL url=new URL(cadenaUrl);
        HttpURLConnection conexion=(HttpURLConnection)url.openConnection();
        conexion.setRequestMethod("GET");
        conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conexion.setRequestProperty("AUTHORIZATION", key);
        conexion.setConnectTimeout(10000);
        conexion.setDoInput(true);
        conexion.connect();
        int respuesta=conexion.getResponseCode();
        if(respuesta==200)
        {
            InputStream is=conexion.getInputStream();
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            byte[] buffer=new byte[1024];
            int longitud=0;
            while((longitud=is.read(buffer))!=-1)
            {
                baos.write(buffer, 0, longitud);
            }
            is.close();
            return baos.toByteArray();
        }
        else
        {
            Log.d("Respuesta", String.valueOf(respuesta));
            throw new IOException();
        }
    }

    public byte[] enviarInformacion(String cadenaUrl, Uri.Builder postParams, String key) throws IOException
    {
        URL url=new URL(cadenaUrl);
        HttpURLConnection conexion=(HttpURLConnection)url.openConnection();
        conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        if(key!=null)
        {
            conexion.setRequestProperty("AUTHORIZATION", key);
        }
        conexion.setConnectTimeout(10000);
        conexion.setRequestMethod("POST");
        conexion.setDoOutput(true);
        String query = postParams.build().getEncodedQuery();
        OutputStream os = conexion.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();
        int response = conexion.getResponseCode();
        if(response==200 || response==201)
        {
            InputStream is = conexion.getInputStream();
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            byte[] buffer=new byte[1024];
            int longitud=0;
            while((longitud=is.read(buffer))!=-1)
            {
                baos.write(buffer, 0, longitud);
            }
            is.close();
            return baos.toByteArray();
        }
        else
        {
            Log.d("Respuesta", String.valueOf(response));
            throw new IOException();
        }
    }
}
