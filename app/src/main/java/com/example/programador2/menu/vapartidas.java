package com.example.programador2.menu;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by hpsamus on 01/07/2016.
 */
public class vapartidas implements KvmSerializable {

    public int IdPartida ;
    public int IdUsuario1 ;
    public int IdUsuario2 ;
    public String retador ;
    public String retado ;
    public int PuntuajeUsuario1 ;
    public int PuntuajeUsuario2 ;
    public int IdNivel ;
    public String Solicitud ;
    public vapartidas( )
    {}
    public vapartidas(int oIdPartida , int oIdUsuario1 , int oIdUsuario2  ,String oretador,String oretado, int oPuntuajeUsuario1 ,int oPuntuajeUsuario2 ,int oIdNivel  ,String oSolicitud )
    {
        this.IdPartida = oIdPartida;
        this.IdUsuario1 = oIdUsuario1;
        this.IdUsuario2 = oIdUsuario2;
        this.retador = oretador;
        this.retado = oretado;
        this.PuntuajeUsuario1 = oPuntuajeUsuario1;
        this.PuntuajeUsuario2 = oPuntuajeUsuario2;
        this.IdNivel = oIdNivel;
        this.Solicitud = oSolicitud;
    }

    @Override
    public Object getProperty(int arg0) {

        switch(arg0)
        {
            case 0:
                return IdPartida;
            case 1:
                return IdUsuario1;
            case 2:
                return IdUsuario2;
            case 3:
                return retador;
            case 4:
                return retado;
            case 5:
                return PuntuajeUsuario1;
            case 6:
                return PuntuajeUsuario2;
            case 7:
                return IdNivel;
            case 8:
                return Solicitud;

        }

        return null;
    }

    @Override
    public int getPropertyCount() {
        return 9;
    }

    @Override
    public void getPropertyInfo(int ind, Hashtable ht, PropertyInfo info) {
        switch(ind)
        {
            case 0:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "IdPartida";
                break;
            case 1:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "IdUsuario1";
                break;

            case 2:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "IdUsuario2";
                break;


            case 3:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "retador";
                break;
            case 4:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "retado";
                break;
            case 5:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "PuntuajeUsuario1";
                break;
            case 6:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "PuntuajeUsuario2";
                break;

            case 7:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "IdNivel";
                break;
            case 8:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Solicitud";
                break;




            default:break;
        }
    }

    @Override
    public void setProperty(int ind, Object val) {
        switch(ind)
        {
           /* case 0:
                return IdPartida;
            case 1:
                return IdUsuario1;
            case 2:
                return IdUsuario2;
            case 3:
                return retador;
            case 4:
                return retado;
            case 5:
                return PuntuajeUsuario1;
            case 6:
                return PuntuajeUsuario2;
            case 7:
                return IdNivel;
            case 8:
                return Solicitud;*/
            case 0:
                IdPartida = Integer.parseInt(val.toString());
                break;
            case 1:
                IdUsuario1 = Integer.parseInt(val.toString());
                break;
            case 2:
                IdUsuario2 = Integer.parseInt(val.toString());
                break;
            case 3:
                retador =val.toString();
            case 4:
                retado =val.toString();
            case 5:
                PuntuajeUsuario1 =Integer.parseInt(val.toString());
            case 6:
                PuntuajeUsuario2 =Integer.parseInt(val.toString());
            case 7:
                IdNivel =Integer.parseInt(val.toString());
            case 8:
                Solicitud =val.toString();


            default:
                break;
        }
    }

}
