/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miconcurso;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergio
 */
public class Hilo extends Thread {

    Scanner sc = new Scanner(System.in);
    private String nombre;
    private Concursante concursante;
    //int resultado = (int) (Math.random() * 10) + 1;
    int resultado = 5; //prueba
    int numero;
    long inicio = System.currentTimeMillis();//COJO EL TIEMPO DE EJECUCIÓN ACTUAL

    public Hilo(String nombre, Concursante concursante) {
        this.nombre = nombre;
        this.concursante = concursante;
    }

    //INICIO EL HILO
    public void run() {

        try {
            
            //System.out.println("Resultado: " + resultado)PRUEBA;
            System.out.println("El concursante llamado " + concursante.getNombre() + " comienza en el " + this.nombre + ".");
            
            do {
                System.out.println(concursante.getNombre().toUpperCase() + " introduce numero del 1 al 10");
                numero = sc.nextInt();
                try {
                    if (resultado == numero) {
                        break;
                    } else {
                        System.out.println(concursante.getNombre() + " ha fallado. Su numero era: " + numero);
                        Thread.sleep(4000);//PARO EL HILO/PROCESO DURANTE ESE TIEMPO
                    }
                } catch (InterruptedException ex) {
                    
                }
                
            } while (resultado != numero);
            
            //CUANDO ACIERTA EL NUMERO CIERRO EL PROCESO Y SACO EL MENSAJE
            System.out.println("El concursante " + concursante.getNombre() + " ha acertado el resultado");
            if (((System.currentTimeMillis() - inicio) / 1000) == 1) {
                System.out.println("HA TARDADO : " + ((System.currentTimeMillis() - inicio) / 1000) + " segundo");
                System.out.println("TERMINA CONCURSO " + concursante.getNombre());
                System.out.println("GANADOR " + concursante.getNombre() + " ¡¡¡¡FELICIDADES!!!!");
            } else {
                System.out.println("HA TARDADO : " + ((System.currentTimeMillis() - inicio) / 1000) + " segundos");
                System.out.println("TERMINA CONCURSO " + concursante.getNombre());
                System.out.println("GANADOR " + concursante.getNombre() + " ¡¡¡¡FELICIDADES!!!!");
            }
            try {
                ProcesoGanador proc = new ProcesoGanador();
            } catch (InterruptedException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
            
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
