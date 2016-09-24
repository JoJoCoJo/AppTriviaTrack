package com.example.programador2.menu;
;


import android.app.Activity;
/*
import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader
import java.io.IOException;
import java.io.OutputStreamWriter;*/


import java.io.BufferedReader;

/**
 * Created by Programador2 on 20/06/2016.
 */
public class variables  {

    public String ip="192.168.1.139";

    public String Usu;


  /*  public String getUsu() {
        return Usu;
    }

    public void setUsu(String usu) {
        Usu = usu;
    }


  public void escribirFicheroMemoriaInterna(String cade)
    {
        OutputStreamWriter escritor=null;
        try
        {
            escritor=new OutputStreamWriter(openFileOutput ("pruebafichero.txt", Context.MODE_PRIVATE));
            escritor.write(cade);
        }
        catch (Exception ex)
        {
            Log.e("ivan", "Error al escribir fichero a memoria interna");
        }
        finally
        {
            try {
                if(escritor!=null)
                    escritor.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String leerFicheroMemoriaInterna(String cad)
    {
        InputStreamReader flujo=null;
        BufferedReader lector=null;
        try
        {
            flujo= new InputStreamReader(openFileInput("pruebafichero.txt"));
            lector= new BufferedReader(flujo);
            String texto = lector.readLine();
            while(texto!=null)
            {

                texto=lector.readLine();
                cad=texto;
            }
        }
        catch (Exception ex)
        {
            Log.e("ivan", "Error al leer fichero desde memoria interna");
        }
        finally
        {
            try {
                if(flujo!=null)
                    flujo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cad;
    }*/
}
