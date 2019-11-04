/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miconcurso;

import java.util.Scanner;

/**
 *
 * @author Sergio
 */
public class MiConcurso {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce nombre concursante: ");
        String nombre = sc.next();
        sc.nextLine();

        Concursante concursante1 = new Concursante(nombre);
        System.out.println("Introduce nombre concursante 2: ");
        String nombre2 = sc.next();
        sc.nextLine();
        Concursante concursante2 = new Concursante(nombre2);

        Hilo hilo1 = new Hilo("Primer Hilo", concursante1);
        Hilo hilo2 = new Hilo("Segundo Hilo", concursante2);

        hilo1.start();
        hilo2.start();

    }

}
