/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import miconcurso.Concursante;
import miconcurso.Hilo;

/**
 *
 * @author sergi
 */
public class MiServidor {

    /**
     * @param args the command line arguments
     */
    private SSLSocket clienteConectado;
    private SSLSocket clienteConectado2;
    private SSLServerSocketFactory sfact;
    private SSLServerSocket servidorSSL;
    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;
    Scanner escaner = new Scanner(System.in);

    int puerto = 5556;

    public void levantarConexion(int puerto) {
        try {
            System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\sergi\\Documents\\NetBeansProjects\\MiConcurso\\src\\AlmacenClaves");
            System.setProperty("javax.net.ssl.keyStorePassword", "1234567");

            sfact = (SSLServerSocketFactory) SSLServerSocketFactory
                    .getDefault();
            servidorSSL = (SSLServerSocket) sfact
                    .createServerSocket(puerto);
            clienteConectado = (SSLSocket) servidorSSL.accept();
            //clienteConectado2 = (SSLSocket) servidorSSL.accept();
            System.out.print("Conexión establecida con: " + clienteConectado.getInetAddress().getHostName() + "\n\n\n");
        } catch (Exception e) {
            System.out.print("Error en levantarConexion(): " + e.getMessage());
            System.exit(0);
        }
    }

    public void flujos() {
        try {
            flujoEntrada = new DataInputStream(clienteConectado.getInputStream());
            flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());
            flujoSalida.flush();
        } catch (IOException e) {
            System.out.print("Error en la apertura de flujos");
        }
    }

    public void recibirDatos() {
        String st = "";
        try {
            do {
                st = (String) flujoEntrada.readUTF();
                System.out.print("\n[Cliente] => " + st);
                System.out.print("\n[Usted] => ");
            } while (!st.equals("salir()"));
        } catch (IOException e) {
            cerrarConexion();
        }
    }

    public void enviar(String s) {
        try {
            flujoSalida.writeUTF(s);
            flujoSalida.flush();
        } catch (IOException e) {
            System.out.print("Error en enviar(): " + e.getMessage());
        }
    }

    public void escribirDatos() {
        while (true) {
            System.out.print("[Usted] => ");
            enviar(escaner.nextLine());
        }
    }

    public void cerrarConexion() {
        try {
            flujoEntrada.close();
            flujoSalida.close();
            clienteConectado.close();
        } catch (IOException e) {
            System.out.print("Excepción en cerrarConexion(): " + e.getMessage());
        } finally {
            System.out.print("Conversación finalizada....");
            System.exit(0);

        }
    }
    
    public void ejecutarConexion(int puerto) {
        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        levantarConexion(puerto);
                        flujos();
                        recibirDatos();
                    } finally {
                        cerrarConexion();
                    }
                }
            }
        });
        hilo.start();
    }

    public static void main(String[] args) {
        // TODO code application logic here

        MiServidor s = new MiServidor();
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingresa el puerto [5050 por defecto]: ");
        String puerto = sc.nextLine();
        if (puerto.length() <= 0) puerto = "5556";
        s.ejecutarConexion(Integer.parseInt(puerto));
        s.escribirDatos();
    }

}
