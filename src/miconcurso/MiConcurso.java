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
        // TODO code application logic here
        
        /*Scanner sc = new Scanner (System.in);
        System.out.println("Introduce el numero");
        int numero = sc.nextInt();*/
        
        
        Concursante concursante1 = new Concursante("Sergio");
        Concursante concursante2 = new Concursante("Juan");
        
        Hilo hilo1 = new Hilo("Primer Hilo", concursante1);
        Hilo hilo2 = new Hilo("Segundo Hilo", concursante2);
        
        hilo1.start();
        hilo2.start();
        
        
        
        
    }
    
}
