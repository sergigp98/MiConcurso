/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    DataInputStream flujoEntrada;
    DataOutputStream flujoSalida;

    public Cliente() {

        // TODO code application logic here
        String Host = "localhost";
        int puerto = 5556;//puerto remoto

        // Propiedades JSSE)
        System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\Usuario DAM 2\\Documents\\NetBeansProjects\\MiConcurso\\src\\AlmacenClaves");
        System.setProperty("javax.net.ssl.trustStorePassword", "1234567");

        try {
            SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket Cliente = (SSLSocket) sfact.createSocket(Host, puerto);

            flujoEntrada = new DataInputStream(Cliente.getInputStream());

            flujoSalida = new DataOutputStream(Cliente.getOutputStream());

            empiezaConcurso();
            

            concursoGanado();
            // CERRAR STREAMS Y SOCKETS
            flujoEntrada.close();
            flujoSalida.close();
            Cliente.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void empiezaConcurso() {
        String juego = null;
        try {
            juego = flujoEntrada.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(juego);
    }

    public void concursoGanado() {
        try {
            flujoSalida.writeUTF("HAS GANADO!!!!!!!!");

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

}
