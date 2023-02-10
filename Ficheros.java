
package com.mycompany.ficheros;

import java.awt.Dimension;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.exit;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author: David Cavanillas
 */
public class Ficheros {


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
//Creamos un método para crear ficheros
    private static void crear() throws IOException{
//Definimos dos variables: ruta (donde irá mi fichero) y miFichero (nombre del fichero creado)
        String ruta = null;
        String miFichero = null;
//JFileChooser es una librería que crea un buscador de archivos simple  
//busqueda será una ventana y setDialogTitle le pondrá el nombre "Crear fichero" en la parte de arriba de la ventana
        JFileChooser busqueda = new JFileChooser();
        busqueda.setDialogTitle("Crear fichero");
//La variable retorno abre una ventana llamada busqueda que es el gestor de archivos de java, retorno guarda el valor del botón seleccionado            
        int retorno = busqueda.showOpenDialog (busqueda);
//Con este if, el retorno se iguala a la opción que queremos que ocurra en el JFileChooser  
//Lo primero, igualamos ruta con busqueda.getSelectedFile().getParent() para que nos indique la dirección getAbsolutePath() del fichero seleccionado
//Creamos una clase tipo File (ya creada en Java) que la llamamos documento
//Igualamos miFichero a JfileChooser busqueda y nos devuelve el nombre del fichero
//NOTA: getName (devuelve el nombre) getParent (devuleve la dirección donde lo guardamos)
//Los System.out.println(ruta),  System.out.println(miFichero), nos enseña por consola que se ha guardado
//Por último hacemos otro if para comprobar si el documento ya existe está inicializado como true, entonces si existe te dirá que el fichero ya existe, sino te creará el fichero
        if(retorno== JFileChooser.APPROVE_OPTION){
            ruta = busqueda.getSelectedFile().getParent();
            File documento = new File(busqueda.getSelectedFile().getAbsolutePath());
            miFichero = busqueda.getName(documento);
            System.out.println(ruta);
            System.out.println(miFichero);

            if(documento.exists()){
                System.out.println("Este fichero ya existe");
            }
            else{
              documento.createNewFile();    
            }   
        }
//Por otro lado, creamos un else if igual que el de arriba pero con cancel_option
        else if(retorno== JFileChooser.CANCEL_OPTION){
            System.out.println("Has cancelado la operación");
        }      
    }

    private static void leer() throws FileNotFoundException, IOException{
        String ruta = null;
        String miFichero = null;

        JFileChooser busqueda = new JFileChooser();
        busqueda.setDialogTitle("Leer fichero");

        int retorno = busqueda.showOpenDialog (busqueda);

        if(retorno == JFileChooser.APPROVE_OPTION){
            ruta = busqueda.getSelectedFile().getParent();
            File documento = new File(busqueda.getSelectedFile().getAbsolutePath());
            miFichero = busqueda.getName(documento);
            System.out.println(ruta);
            System.out.println(miFichero);
//Hasta aquí será todo igual excepto que en el mensaje tendremos en vez de Crear fichero, Leer fichero
//Ahora creamos una librería de java llamada  BufferedReader que hemos llamado leer y accederá a nuesto File documento
            BufferedReader leer = new BufferedReader(new FileReader(documento));
            String linea = leer.readLine();
            String textoCompleto = linea;
//Creamos dos variables: linea y textoCompleto
            linea = leer.readLine();
//En este while decimos que mientras la linea no esté vacía sigamos leyendo las líneas del texto y que se guarden en textoCompleto
            while(linea!=null){
                textoCompleto = textoCompleto + "\n" + linea;
                linea = leer.readLine();
            }
//Creamos un JoptionPane.shoeMessageDialog como arriba donde nos dará el nombre del texto completo como texto principal, y el título será el nombre del documento leido
            JOptionPane.showMessageDialog(null, textoCompleto, documento.getName(), JOptionPane.PLAIN_MESSAGE);
        }
    }

    private static void escribir() throws FileNotFoundException, IOException{
        String ruta = null;
        String miFichero = null;

        JFileChooser busqueda = new JFileChooser();
        busqueda.setDialogTitle("Escribir en fichero");

        int retorno = busqueda.showOpenDialog (busqueda);

        if(retorno == JFileChooser.APPROVE_OPTION){
            ruta = busqueda.getSelectedFile().getParent();
            File documento = new File(busqueda.getSelectedFile().getAbsolutePath());
            miFichero = busqueda.getName(documento);
            System.out.println(ruta);
            System.out.println(miFichero);
//Hasta aquí será todo igual excepto que en el mensaje tendremos en vez de Crear fichero, Leer fichero
//Ahora creamos una librería de java llamada  BufferedReader que hemos llamado leer y accederá a nuesto File documento
            BufferedReader leer = new BufferedReader(new FileReader(documento));
            String linea = leer.readLine();
            String textoCompleto = linea;
//Creamos dos variables: linea y textoCompleto
            linea = leer.readLine();
//En este while decimos que mientras la linea no esté vacía sigamos leyendo las líneas del texto y que se guarden en textoCompleto
            while(linea!=null){
                textoCompleto = textoCompleto + "\n" + linea;
                linea = leer.readLine();
            }
//Creamos un JTextArea que le llamamos zonaDeEscritura y la inicializamos con el texto completo
            JTextArea zonaDeEscritura = new JTextArea(textoCompleto);
//setLineWrap sirve para que si el texto excede el límite del cuadro de texto haga un espacio           
            zonaDeEscritura.setLineWrap(true);
//set.Editable sirve para que el texto sea editable, si lo pusieses como false no podrías editar el texto
            zonaDeEscritura.setEditable(true);
            
//Estas tres lineas sirven para hacer un scroll y en el momento en el que editas el texto no te desborda el texto, te crea un scroll            
            JScrollPane scroll = new JScrollPane(zonaDeEscritura);
            scroll.setPreferredSize(new Dimension(300,300));
            scroll.getViewport().setView(zonaDeEscritura);
            
//Creamos un JoptionPane.shoeMessageDialog como arriba donde nos dará el nombre del texto completo como texto principal, y el título será el nombre del documento leido
            JOptionPane.showMessageDialog(null, scroll, documento.getName(), JOptionPane.PLAIN_MESSAGE);
//Usamos el FileWriter para guardar el texto editado y lo ponemos como false para sobreescribir el contenido            
            FileWriter guardoLoQueEscribo = new FileWriter(documento, false);
//Creamos el BufferedWriter para poder escribir texto y guardarlo en guardoLoQueEscribo
            BufferedWriter escritor = new BufferedWriter (guardoLoQueEscribo);
//Obtenemos el texto de zonaDeEscritura y escritor.write escribe
            escritor.write(zonaDeEscritura.getText());
           
            escritor.flush();
            escritor.close();
        }
    }
   
}
