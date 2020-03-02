/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miconcurso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sergio
 */
public class ProcesoGanador implements Serializable{

    public ProcesoGanador() throws InterruptedException, IOException {
        ArrancarGanador();
    }

    public static void ArrancarGanador() throws InterruptedException {
        String cmd = "start https://image.slidesharecdn.com/yakepregunatakathe-171117223539/95/preguntas-y-respuestas-24-638.jpg?cb=1510958203";
        ProcessBuilder pb = new ProcessBuilder("cmd", "/C", cmd);
        Process process;
        try {
            process = pb.start();
            InputStream inputstream = process.getInputStream();
            BufferedInputStream bufferedinputstream = new BufferedInputStream(inputstream);
        } catch (IOException ex) {
            Logger.getLogger(ProcesoGanador.class.getName()).log(Level.SEVERE, null, ex);
        }

        sleep(2000);
    }

}
