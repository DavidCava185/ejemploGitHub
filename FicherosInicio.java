
package com.mycompany.ficheros;

import java.io.IOException;
import static java.lang.System.exit;
import javax.swing.JOptionPane;

/**
 *
 * @author: David Cavanillas
 */
public class FicherosInicio {


    public static void main(String[] args) throws IOException {
//Creamos un ArrayList para las opciones que nos aparecen en la ventana: Crear, Leer...etc
        String[] opciones = {"Crear","Leer","Escribir","Salir"};
//Definimos una variable tipo int llamada "opcion", guarda la selección del botón       
        int opcion;

//Definimos un variable bucle tipo boolean, inicializada como true para que se repita el bucle hasta que sea false (hasta darle a salir)        
        boolean bucle = true;
//Creamos el bucle para repetir lo siguiente:
//JOptionPane.showOptionDialog(para que aparezca la ventana), dentro de ella:
//- Primero ponemos de dónde vienes, en este caso null, vacío
//- Contenido de la ventana (Elige una de las opciones)
//- Título de la ventana (Gestión de ficheros)
//- Tipo de opción e información (JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE) 
//- El siguiente null es para el icono que aparece al lado de "Elige una de las opciones"
//- Por último ponemos opciones para las opciones que quieras poner y empiezan en la posición 0
        while (bucle){
            opcion = JOptionPane.showOptionDialog(null, "Elige una de las opciones","Gestión de ficheros",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
 //Creamos un switch de opciones en el que si se da el case 0, llama al método crear, case 1 Leer, case 2 Escribir
 //Si se da el case 3, tenemos un método que fuerza la salida del programa y decimos que la variable bucle es falsa para romper el bucle while
            switch (opcion) {

                    case 0->{
                        crear();
                    }
                    case 1->{
                        leer();
                    }
                    case 2->{
                        escribir();
                    }
                    case 3->{
                        exit(0);
                        bucle=false;
                    }

            }
        }   
    }
}