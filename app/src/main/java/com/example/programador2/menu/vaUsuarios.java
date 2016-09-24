package com.example.programador2.menu;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Date;
import java.util.Hashtable;

/**
 * Created by hpsamus on 10/07/2016.
 */
public class vaUsuarios implements KvmSerializable {
    public int IdUsuario ;
    public String Nombre ;
    public String Apellido ;
    public String Fecha ;
    public String Usuario ;
    public String Password ;
  public  vaUsuarios()
  {

      this.IdUsuario =0;
      this.Nombre = "";
      this.Apellido = "";
     // this.Fecha = '00000000';
      this.Usuario = "";
      this.Password = "";
  }

    public vaUsuarios(int oIdUsuario, String oNombre, String oApellido, String oFecha, String oUsuario,String oPassword)
    {
        this.IdUsuario = oIdUsuario;
        this.Nombre = oNombre;
        this.Apellido = oApellido;
        this.Fecha = oFecha;
        this.Usuario = oUsuario;
        this.Password = oPassword;

    }
    @Override
    public Object getProperty(int arg0) {

        switch(arg0)
        {
            case 0:
                return IdUsuario;
            case 1:
                return  Nombre;
            case 2:
                return Apellido;
            case 3:
                return Fecha;
            case 4:
                return Usuario;
            case 5:
                return Password;


        }

        return null;
    }

    @Override
    public int getPropertyCount() {
        return 6;
    }

    @Override
    public void getPropertyInfo(int ind, Hashtable ht, PropertyInfo info) {
        switch(ind)
        {
            case 0:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "IdUsuario";
                break;
            case 1:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "Nombre";
                break;
            case 2:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "Apellido";
                break;
            case 3:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Fecha";
                break;
            case 4:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Usuario";
                break;
            case 5:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "Password";
                break;




            default:break;
        }




    }
    @Override
    public void setProperty(int ind, Object val) {
        switch(ind)
        {
            case 0:
                IdUsuario = Integer.parseInt(val.toString());
                break;
            case 1:
                Nombre = val.toString();
                break;
            case 2:
                Apellido = val.toString();
                break;
            case 3:
                Fecha =  val.toString();
                break;
            case 4:
                Usuario =val.toString();
                break;
            case 5:
                Password =val.toString();




            default:
                break;
        }
    }


}
