/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miconcurso;

import java.io.Serializable;

/**
 *
 * @author Sergio
 */
public class Concursante implements Serializable{

    private String nombre;

    public Concursante(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Concursante GANADOR{" + "nombre=" + nombre + '}';
    }

    
}
