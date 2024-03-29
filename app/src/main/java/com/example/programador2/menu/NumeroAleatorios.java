package com.example.programador2.menu;

/**
 * Created by hpsamus on 02/07/2016.
 */
import java.util.ArrayList;
public class NumeroAleatorios {


    private int valorInicial;
    private int valorFinal;
    private ArrayList listaNumero;

    public NumeroAleatorios(int valorInicial, int valorFinal,ArrayList listaNumeroo ){
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
       listaNumero = new ArrayList();
        listaNumero=listaNumeroo;
    }

    private int numeroAleatorio(){
        return (int)(Math.random()*(valorFinal-valorInicial+1)+valorInicial);
    }

    public int generar(){
        if(listaNumero.size() < (valorFinal+valorFinal) - (valorInicial+valorInicial) +1){
            //Aun no se han generado todos los numeros
            int numero = numeroAleatorio();//genero un numero
            if(listaNumero.isEmpty()){//si la lista esta vacia
                listaNumero.add(numero);
                return numero;
            }else{//si no esta vacia
                if(listaNumero.contains(numero)){//Si el numero que generé esta contenido en la lista
                    return generar();//recursivamente lo mando a generar otra vez
                }else{//Si no esta contenido en la lista
                    listaNumero.add(numero);
                    return numero;
                }

            }
        }else{// ya se generaron todos los numeros
            return 99;
        }
    }



}
