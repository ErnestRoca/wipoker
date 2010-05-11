/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladora;

/**
 *
 * @author wida45787385
 */
public class ControladoraJoc {

    ControladoraPartida cp;

    public ControladoraJoc() throws InterruptedException {
        cp = new ControladoraPartida((byte) 2);
        jugar();
    }

    public void jugar() throws InterruptedException {
        cp.crearBaralla();
        cp.barallar();
    }

    public static void main(String[] args) throws InterruptedException {
        new ControladoraJoc();
    }
    
}
