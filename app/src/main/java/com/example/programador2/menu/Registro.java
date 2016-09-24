package com.example.programador2.menu;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Programador2 on 24/05/2016.
 */
public class Registro extends AppCompatActivity {
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    private Button btnRegister;
    public String msje="";
    public boolean  ms;
    public String re="";
    private EditText txtnombre,txtusuario,txtpassword;

    public String nombre,usuario,fecha,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        txtnombre=(EditText)findViewById(R.id.txtNombre);
        txtpassword=(EditText)findViewById(R.id.txtPass);
        txtusuario=(EditText)findViewById(R.id.txtUserName);



        btnRegister=(Button)findViewById(R.id.btnRegister);
        dateView = (TextView) findViewById(R.id.lblFecha);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    nombre = txtnombre.getText().toString().trim();
                    usuario = txtusuario.getText().toString().trim();
                    fecha = dateView.getText().toString().trim();
                    password=txtpassword.getText().toString().trim();

                    TareaWSConsulta tarea = new TareaWSConsulta();
                    tarea.execute();
                    Toast.makeText(getBaseContext(),msje .toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){

                    Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    private class TareaWSConsulta extends AsyncTask<String,Integer,Boolean> {

        protected Boolean doInBackground(String... params) {
            //string nombre, string apellido, DateTime fecha, string Usuario, string Password
            boolean resul = true;
            variables var = new variables();
            final String NAMESPACE = "http://tempuri.org/";
            final String URL = "http://"+var.ip.trim()+":8091/Service.asmx";
            // http://192.168.1.184:8091/Service.asmx
            final String METHOD_NAME = "NuevoUsuariocadena";
            final String SOAP_ACTION = "http://tempuri.org/NuevoUsuariocadena";
            final SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            // Variables var = new Variables();

         //   final SoapObject oUser = request.addProperty("oUser",usuario );
             request.addProperty("nombre",nombre );
            //request.addProperty("oPasword",nombre);
            request.addProperty("apellido", "");

       request.addProperty("fecha", fecha);
            request.addProperty("Usuario", usuario);

            request.addProperty("Password",password);



            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(URL);
            try {
                transporte.call(SOAP_ACTION, envelope);
                SoapPrimitive resultado_xml = (SoapPrimitive) envelope.getResponse();
                re = resultado_xml.toString();
                msje = re;

                if (re.equals(""))
                    resul = false;
            } catch (Exception e) {
               // msje=e.toString();
                resul = false;
            }

            return resul;
        }


        protected void onPostExecute(Boolean result) {

            Toast.makeText(getBaseContext(), re, Toast.LENGTH_SHORT).show();
            if (result == false) {
                Toast.makeText(getBaseContext(), "No se encontro el servidor ;) ", Toast.LENGTH_SHORT).show();
            }
            if (result == true) {
                if (re.equals("0")) {
                    Toast.makeText(getBaseContext(), "Usuario o Contraseña invalidos", Toast.LENGTH_SHORT).show();
                }
                if (re.equals("1")) {

                    Toast.makeText(getBaseContext(), "Registrado",Toast.LENGTH_SHORT).show();
                    Intent in = new Intent (Registro.this ,MainActivity.class);


                           /* in.putExtra("tipousuario", tipousuario);
                            in.putExtra("detallecontador", "d");
                            in.putExtra("listadocontador", "d");
                            txtPassword.setText("");
                            txtUsuario.setText("");*/
                    startActivity(in );

                /*    if (tipousuario.equals("false")) {

                            Intent in2 = new Intent (Login.this ,Contador.class);
                            Toast.makeText(getBaseContext(), "Bienvenido",Toast.LENGTH_SHORT).show();
                            in2.putExtra("texto", txtUsuario.getText().toString());
                            in2.putExtra("detallecontador", "d");
                            in2.putExtra("listadocontador", "d");
                            txtPassword.setText("");
                            txtUsuario.setText("");
                            startActivity(in2 );


                    }*/

                }
            }
        }
    }
}
