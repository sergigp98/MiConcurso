/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import miconcurso.Concursante;
import miconcurso.Hilo;
import miconcurso.Panel_Login;

/**
 *
 * @author sergi
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {        
        
        Panel_Login login = new Panel_Login();
        
        
        // TODO code application logic here
        String Host = "localhost";
        int puerto = 5556;//puerto remoto
 
        // Propiedades JSSE)
        System.setProperty("javax.net.ssl.trustStore","C:\\Users\\sergi\\Desktop\\SERGIO\\DAM\\2º DAM\\NetBeansProjects\\MiConcurso\\src\\AlmacenClaves");
        System.setProperty("javax.net.ssl.trustStorePassword","1234567");
 
        System.out.println("PROGRAMA CLIENTE INICIADO....");
 
        SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket Cliente  = (SSLSocket) sfact.createSocket(Host, puerto);
        
        
 
        // CREO FLUJO DE SALIDA AL SERVIDOR
        DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());
 
        // ENVIO UN SALUDO AL SERVIDOR
        flujoSalida.writeUTF("HAS GANADO!!!!!!!!");
 
        // CREO FLUJO DE ENTRADA AL SERVIDOR
        DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());
 
        
        // EL servidor ME ENVIA UN MENSAJE
        System.out.println("Recibiendo del SERVIDOR: \n\t" + flujoEntrada.readUTF());
 
        // CERRAR STREAMS Y SOCKETS
        flujoEntrada.close();
        flujoSalida.close();
        Cliente.close();
    }

}
