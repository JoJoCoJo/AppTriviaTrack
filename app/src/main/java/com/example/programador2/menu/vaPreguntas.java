package com.example.programador2.menu;

import java.util.Hashtable;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
/**
 * Created by Programador2 on 24/06/2016.
 */
public class vaPreguntas implements KvmSerializable {


    public int row;
    public int  numcol;
    public int Id;
    public String Pregunta;
    public String RespuestaV;
    public String RespuestaF1;
    public String RespuestaF2;
    public String RespuestaF3;



    public vaPreguntas()
    {
        row=0;
        numcol=0;
        Id = 0;
        Pregunta="";
        RespuestaV ="";
        RespuestaF1 ="";
        RespuestaF2 ="";
        RespuestaF3 ="";
    }

    public vaPreguntas(int orow,int onumcol ,int Oid, String Opregunta,String Orespuestav,String Orespuestaf1,String Orespuestaf2,String Orespuestaf3 )
    {
        this.row=orow;
        this.numcol=onumcol;
        this.Id = Oid;
       this.Pregunta=Opregunta;
        this.RespuestaV =Orespuestav;
        this.RespuestaF1 =Orespuestaf1;
        this.RespuestaF2 =Orespuestaf2;
        this.RespuestaF3 =Orespuestaf3;

    }
    @Override
    public Object getProperty(int arg0) {

        switch(arg0)
        {
            case 0:
                return row;
            case 1:
                return  numcol;
            case 2:
                return Id;
            case 3:
                return Pregunta;
            case 4:
                return RespuestaV;
            case 5:
                return RespuestaF1;
            case 6:
                return RespuestaF2;
            case 7:
                return RespuestaF3;

        }

        return null;
    }

    @Override
    public int getPropertyCount() {
        return 8;
    }

    @Override
    public void getPropertyInfo(int ind, Hashtable ht, PropertyInfo info) {
        switch(ind)
        {
            case 0:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "row";
                break;
            case 1:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "numcol";
                break;
            case 2:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "Id";
                break;
            case 3:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Pregunta";
                break;
            case 4:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "RespuestaV";
                break;
            case 5:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "RespuestaF1";
                break;
            case 6:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "RespuestaF2";
                break;
            case 7:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "RespuestaF3";
                break;
           


            default:break;
        }
    }

    @Override
    public void setProperty(int ind, Object val) {
        switch(ind)
        {
            case 0:
                row = Integer.parseInt(val.toString());
                break;
            case 1:
                numcol = Integer.parseInt(val.toString());
                break;
            case 2:
                Id = Integer.parseInt(val.toString());
                break;
            case 3:
               Pregunta = val.toString();
                break;
            case 4:
                RespuestaV=val.toString();
                break;
            case 5:
               RespuestaF1 =val.toString();
            case 6:
                RespuestaF2 =val.toString();
            case 7:
                RespuestaF3 =val.toString();



            default:
                break;
        }
    }
}
