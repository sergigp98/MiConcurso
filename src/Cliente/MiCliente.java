/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import miconcurso.Concursante;
import miconcurso.Hilo;

/**
 *
 * @author sergi
 */
public class MiCliente {

    /**
     * @param args the command line arguments
     */
    private SSLSocket Cliente;
    //private SSLSocket clienteConectado2;
    private SSLSocketFactory sfact;
    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;
    Scanner escaner = new Scanner(System.in);

    int puerto = 5556;

    public void levantarConexion(String ip, int puerto) {
        try {
            String Host = "localhost";

            // Propiedades JSSE)
            System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\sergi\\Documents\\NetBeansProjects\\MiConcurso\\src\\AlmacenClaves");
            System.setProperty("javax.net.ssl.trustStorePassword", "1234567");

            //System.out.println("PROGRAMA CLIENTE INICIADO....");

            SSLSocketFactory sfact = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket Cliente = (SSLSocket) sfact.createSocket(Host, puerto);
            System.out.print("Conectado a :" + Cliente.getInetAddress().getHostName());
        } catch (Exception e) {
            System.out.print("Excepción al levantar conexión: " + e.getMessage());
            System.exit(0);
        }
    }

    public void abrirFlujos() {
        try {
            flujoEntrada = new DataInputStream(Cliente.getInputStream());
            flujoSalida = new DataOutputStream(Cliente.getOutputStream());
            flujoSalida.flush();
        } catch (IOException e) {
            System.out.print("Error en la apertura de flujos");
        }
    }

    public void enviar(String s) {
        try {
            flujoSalida.writeUTF(s);
            flujoSalida.flush();
        } catch (IOException e) {
            System.out.print("IOException on enviar");
        }
    }

    public void cerrarConexion() {
        try {
            flujoEntrada.close();
            flujoSalida.close();
            Cliente.close();
            System.out.print("Conexión terminada");
        } catch (IOException e) {
            System.out.print("IOException on cerrarConexion()");
        } finally {
            //System.exit(0);
        }
    }

    public void ejecutarConexion(String ip, int puerto) {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    levantarConexion(ip, puerto);
                    abrirFlujos();
                    recibirDatos();
                } finally {
                    cerrarConexion();
                }
            }
        });
        hilo.start();
    }

    public void recibirDatos() {
        String st = "";
        try {
            do {
                st = (String) flujoEntrada.readUTF();
                System.out.print("\n[Servidor] => " + st);
                System.out.print("\n[Usted] => ");
            } while (!st.equals("salir()"));
        } catch (IOException e) {}
    }

    public void escribirDatos() {
        String entrada = "";
        while (true) {
            System.out.print("[Usted] => ");
            entrada = escaner.nextLine();
            if(entrada.length() > 0)
                enviar(entrada);
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here

        MiCliente cliente = new MiCliente();
        Scanner escaner = new Scanner(System.in);
        System.out.print("Ingresa la IP: [localhost por defecto] ");
        String ip = escaner.nextLine();
        if (ip.length() <= 0) ip = "localhost";

        System.out.print("Puerto: [5050 por defecto] ");
        String puerto = escaner.nextLine();
        if (puerto.length() <= 0) puerto = "5050";
        cliente.ejecutarConexion(ip, Integer.parseInt(puerto));
        cliente.escribirDatos();
    }

}
