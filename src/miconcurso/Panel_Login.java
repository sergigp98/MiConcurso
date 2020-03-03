/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miconcurso;

import BBDD.Conexion;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import utils.Usuario;
import Cliente.Cliente;

/**
 *
 * @author sergi
 */
public class Panel_Login extends JFrame {

    Container container;
    JPanel panelCentral;
    Label txtUsuario, txtPassword;
    JTextField usuario, password;
    JButton iniciar;
    
    Conexion conexion = new Conexion();
    
    private Contest contest; 

    public Panel_Login() {
        initGUI();
    }

    public void initGUI() {
        instancias();
        configurarContainer();
        acciones();

        this.setSize(new Dimension(300, 150));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void instancias() {
        container = this.getContentPane();
        panelCentral = new JPanel();

        txtUsuario = new Label("USUARIO");
        txtPassword = new Label("CONTRASEÑA");
        usuario = new JTextField();
        password = new JTextField();
        
        iniciar = new JButton("Iniciar Juego");
    }

    private void configurarContainer() {
        this.setLayout(new BorderLayout());
        this.add(panelCentral(), BorderLayout.CENTER);
        this.add(iniciar,BorderLayout.SOUTH);
    }

    private void acciones() {
        iniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Usuario login = InicioSesion();
                //System.out.println(login);
                try{
                if(usuario.getText().equals(login.getUsuario())|| password.getText().equals(login.getPassword())){
                    System.out.println("INICIO SESION CORRECTO");
                    
                  Cliente cliente = new Cliente();
                  
                  contest = new Contest();                    
                }
                }catch(NullPointerException e){
                    System.out.println("INICIO SESION INCORRECTO");
                }
            }
        });
        
    }

    private JPanel panelCentral() {
        panelCentral.setLayout(new GridLayout(2, 2));
        panelCentral.add(txtUsuario);
        panelCentral.add(usuario);
        panelCentral.add(txtPassword);
        panelCentral.add(password);
        return panelCentral;

    }
    
    private Usuario InicioSesion() {


        PreparedStatement ps;
        ResultSet rs;

        String usuario1 = usuario.getText().toString();
        String password1 = SHA512(password.getText().toString(),"s");
        
        Usuario login = null;

        //Sentencia
        String consultaSql = "SELECT * FROM usuario WHERE usuario = ? AND pasword = ?";
        try {
            ps = conexion.getConexion().prepareStatement(consultaSql);
            ps.setString(1, usuario1);
            ps.setString(2, password1);
            rs = ps.executeQuery();

            //COGO EL USUARIO Y CONTRASEÑA SEGUN RECORRO LA BASE DE DATOS Y LO GUARDO EN UN ENTRENADOR PARA PODER RECUPEARLO LUEGO
            while (rs.next()) {
                String usuarioInicio = rs.getString("usuario"); //Nombre de la columna de la BBDD
                String passwordInicio = rs.getString("pasword"); //Password de la columna de la BBDD
                login = new Usuario(usuarioInicio, passwordInicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return login;
    }
    
    public String SHA512(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public Contest getContest() {
        return contest;
    }
    
    

}
