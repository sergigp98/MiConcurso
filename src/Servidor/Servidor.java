/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import miconcurso.Concursante;
import miconcurso.Hilo;
import miconcurso.Panel_Login;
import miconcurso.ProcesoGanador;

/**
 *
 * @author sergi
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO code application logic here
        int puerto = 5556;

        System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\sergi\\Desktop\\SERGIO\\DAM\\2º DAM\\NetBeansProjects\\MiConcurso\\src\\AlmacenClaves");
        System.setProperty("javax.net.ssl.keyStorePassword", "1234567");

        SSLServerSocketFactory sfact = (SSLServerSocketFactory) SSLServerSocketFactory
                .getDefault();
        SSLServerSocket servidorSSL = (SSLServerSocket) sfact
                .createServerSocket(puerto);
        SSLSocket clienteConectado = null;
        DataInputStream flujoEntrada = null;//FLUJO DE ENTRADA DE CLIENTE
        DataOutputStream flujoSalida = null;//FLUJO DE SALIDA AL CLIENTE

        System.out.println("Esperando al cliente ");
        clienteConectado = (SSLSocket) servidorSSL.accept();

        flujoEntrada = new DataInputStream(clienteConectado.getInputStream());
        flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());
        flujoSalida.writeUTF("EMPIEZA EL JUEGO!!!!" + "\n--------------------\n--------------------");

        // EL CLIENTE ME ENVIA UN MENSAJE
        System.out.println("Recibiendo del CLIENTE: " + " \n\t"
                + flujoEntrada.readUTF());
        
        //RECUPERO EL NOMBRE DEL GANADOR GUARDADO EN EL FICHERO
        Concursante c;
        File f = new File("src/ganador.txt");
        FileInputStream fileInputStream = new FileInputStream(f);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        c = (Concursante) objectInputStream.readObject();
        System.out.println(c.getNombre());
        objectInputStream.close();

        // CERRAR STREAMS Y SOCKETS
        flujoEntrada.close();
        flujoSalida.close();
        clienteConectado.close();
        servidorSSL.close();

        

    }

}
