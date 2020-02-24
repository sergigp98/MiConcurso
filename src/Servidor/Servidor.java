/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import miconcurso.Concursante;
import miconcurso.Hilo;

/**
 *
 * @author sergi
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
       int puerto = 5556;
               
                System.setProperty("javax.net.ssl.keyStore","C:\\Users\\sergi\\Documents\\NetBeansProjects\\MiConcurso\\src\\AlmacenClaves");
                System.setProperty("javax.net.ssl.keyStorePassword","1234567");
       
        SSLServerSocketFactory sfact = (SSLServerSocketFactory) SSLServerSocketFactory
                .getDefault();
        SSLServerSocket servidorSSL = (SSLServerSocket) sfact
                .createServerSocket(puerto);
        SSLSocket clienteConectado = null;
        DataInputStream flujoEntrada = null;//FLUJO DE ENTRADA DE CLIENTE
        DataOutputStream flujoSalida = null;//FLUJO DE SALIDA AL CLIENTE
       
        //for (int i = 1; i < 5; i++) {
            System.out.println("Esperando al jugador 1");
            clienteConectado = (SSLSocket) servidorSSL.accept();       
            flujoEntrada = new DataInputStream(clienteConectado.getInputStream());
 
            // EL CLIENTE ME ENVIA UN MENSAJE
            //System.out.println("Recibiendo del CLIENTE: " + i + " \n\t"
                    //+ flujoEntrada.readUTF());
            Concursante concursante = new Concursante("Sergio");
            Runnable r = new Hilo("Sergio", concursante);
            Thread t  = new Thread(r);
            t.start();
   
            flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());
 
            // ENVIO UN SALUDO AL CLIENTE
            flujoSalida.writeUTF("Saludos al cliente del servidor");
            
            if(!t.isAlive()){
                
            }
            
        //}
        // CERRAR STREAMS Y SOCKETS
        
    }
    
}
