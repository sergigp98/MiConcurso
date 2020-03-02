/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miconcurso;

import java.util.Scanner;

/**
 *
 * @author sergi
 */
public class Contest {
    private Hilo hilo1;
    private Hilo hilo2;

    public Contest() {
        //INTRODUZCO LOS NOMBRES DE LOS CONCURSANTES Y LOS CREO
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce nombre concursante: ");
        String nombre = sc.next();
        sc.nextLine();
        Concursante concursante1 = new Concursante(nombre);
        
        
        System.out.println("Introduce nombre concursante 2: ");
        String nombre2 = sc.next();
        sc.nextLine();
        Concursante concursante2 = new Concursante(nombre2);

        
        //CREO LOS DOS HILOS, UNO PARA CADA CONCURSANTE
        hilo1 = new Hilo("Primer Hilo", concursante1);
        hilo2 = new Hilo("Segundo Hilo", concursante2);

        hilo1.start();
        hilo2.start();
        
        
        
    }

    public Hilo getHilo1() {
        return hilo1;
    }

    public Hilo getHilo2() {
        return hilo2;
    }
    
    

    
    
    
    
    
    
}
