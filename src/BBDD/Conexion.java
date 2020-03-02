/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.Usuario;

/**
 *
 * @author sergi
 */
public class Conexion {
    
    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    
    public Conexion(){
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/bd_psp_login", "admin", "admin");
            System.out.println("BASE DE DATOS CARGADA");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos : " + e.getMessage());
        }
    }
        
}
