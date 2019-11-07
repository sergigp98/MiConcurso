/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miconcurso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Thread.sleep;

/**
 *
 * @author Sergio
 */
public class Proceso {

    public Proceso() throws InterruptedException, IOException {
        DocumentosExplorador();
    }
    
    
    
    public static void DocumentosExplorador() throws IOException, InterruptedException {
        String cmd = "https://image.slidesharecdn.com/yakepregunatakathe-171117223539/95/preguntas-y-respuestas-24-638.jpg?cb=1510958203"; 
        ProcessBuilder pb = new ProcessBuilder("cmd", "/C", cmd);        
        Process process;
        process = pb.start();
        InputStream inputstream = process.getInputStream();
        BufferedInputStream bufferedinputstream = new BufferedInputStream(inputstream);
        sleep(2000);
    }
    
}
