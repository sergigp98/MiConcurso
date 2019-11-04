/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miconcurso;

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
    int resultado = (int) (Math.random() * 10) + 1;
    int numero;
    long inicio = System.currentTimeMillis();

    public Hilo(String nombre, Concursante concursante) {
        this.nombre = nombre;
        this.concursante = concursante;
    }

    public void run() {

        //System.out.println("Resultado: " + resultado);
        System.out.println("El concursante llamado " + concursante.getNombre() + " comienza");

        do {
            System.out.println("Introduce numero del 1 al 10");
            numero = sc.nextInt();
            try {
                if (resultado == numero) {
                    break;
                } else {
                    System.out.println(concursante.getNombre() + " ha fallado. Su numero era: " + numero);
                    Thread.sleep(5000);
                }
            } catch (InterruptedException ex) {

            }

        } while (resultado != numero);

        System.out.println("El concursante " + concursante.getNombre() + " ha acertado el resultado");
        if (((System.currentTimeMillis() - inicio) / 1000) == 1) {
            System.out.println("HA TARDADO : " + ((System.currentTimeMillis() - inicio) / 1000) + " segundo");
            System.out.println("TERMINA CONCURSO " + concursante.getNombre());
        } else {
            System.out.println("HA TARDADO : " + ((System.currentTimeMillis() - inicio) / 1000) + " segundos");
            System.out.println("TERMINA CONCURSO " + concursante.getNombre());
        }
    }
}
