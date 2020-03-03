/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miconcurso;

import javax.swing.SwingUtilities;

/**
 *
 * @author Usuario DAM 2
 */
public class MainConcurso {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Panel_Login login = new Panel_Login();
            }
        });
    }

}
