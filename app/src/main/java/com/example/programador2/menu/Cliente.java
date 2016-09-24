package com.example.programador2.menu;

import java.util.Hashtable;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import android.R.integer;
import android.R.string;
/**
 * Created by Programador2 on 04/06/2016.
 */
public class Cliente implements KvmSerializable  {

    public int Id;
    public String Nombre;
    public String Aseguradora;
    public int numvidas;


    public Cliente()
    {
        Id = 0;
        Nombre= "";
        Aseguradora="";
        numvidas=0;
    }

    public Cliente(int id, String nombre,String aseguradora,int NUMVI)
    {
        this.Id = id;
        this.Nombre = nombre;
        this.Aseguradora=aseguradora;
        this.numvidas=NUMVI;

    }



    @Override
    public Object getProperty(int arg0) {

        switch(arg0)
        {
            case 0:
                return Id;
            case 1:
                return Nombre;
            case 2:
                return Aseguradora;
            case 3:
                return numvidas;



        }

        return null;
    }

    @Override
    public int getPropertyCount() {
        return 4;
    }

    @Override
    public void getPropertyInfo(int ind, Hashtable ht, PropertyInfo info) {
        switch(ind)
        {
            case 0:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "Id";
                break;
            case 1:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Nombre";
                break;
            case 2:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Aseguradora";
                break;
            case 3:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "numvidas";
                break;


            default:break;
        }
    }

    @Override
    public void setProperty(int ind, Object val) {
        switch(ind)
        {
            case 0:
                Id = Integer.parseInt(val.toString());
                break;
            case 1:
                Nombre = val.toString();
                break;
            case 2:
                Aseguradora=val.toString();
                break;
            case 3:
                numvidas = Integer.parseInt(val.toString());



            default:
                break;
        }
    }
}
