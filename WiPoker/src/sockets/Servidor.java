/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sockets;

import controladora.ControladoraGui;
import domini.Jugador;
import domini.Taula;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wida45787385
 */
public class Servidor {

    private ServerSocket socket;

    private Taula taula;
    private ControladoraGui gui;
    
    public Servidor (int port) {
        try {
            socket = new ServerSocket(port);
            socket.accept();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void jugar() throws InterruptedException {
        System.out.println("---------********NOVA PARTIDA******---------");
        gui.actualitzarLog("*********NOVA PARTIDA*********");
        gui.ocultarPanellsJugadors();
        gui.gestionarFitxes();
        gui.mostrarAvatars(gui.getCp().partida.getJugadors());
        Collections.sort(gui.getCp().partida.getJugadors(), new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                Jugador j1 = (Jugador) o1;
                Jugador j2 = (Jugador) o2;
                return j1.getPosicioTaula() - j2.getPosicioTaula();
            }
        });
        int boto = 0;
        int numJugadorsActius = 0;
        for (int i = 0; i < gui.getCp().partida.getJugadors().size(); i++) {
            if (!gui.getCp().partida.getJugadors().get(i).isEliminat()) {
                numJugadorsActius++;
            }
        }
        while (numJugadorsActius > 1) {
            iniciarRonda(boto);
            determinarJugadorsEliminats();
            //Avança Boto
            if (boto == gui.getCp().partida.getJugadors().size()) {
                boto = 0;
                if (gui.getCp().partida.getJugadors().get(boto).isEliminat() == true) {
                    boolean botoTrobat = false;
                    for (int i = boto + 1; i < gui.getCp().partida.getJugadors().size() && !botoTrobat; i++) {
                        if (!gui.getCp().partida.getJugadors().get(i).isEliminat()) {
                            boto = i;
                            botoTrobat = true;
                            dealer = i;
                        }
                    }
                }
            } else {
                boolean botoTrobat = false;
                for (int i = boto + 1; i < gui.getCp().partida.getJugadors().size() && !botoTrobat; i++) {
                    if (!gui.getCp().partida.getJugadors().get(i).isEliminat()) {
                        boto = i;
                        botoTrobat = true;
                        dealer = i;
                    }
                }
            }
            numJugadorsActius = 0;
            for (int i = 0; i < gui.getCp().partida.getJugadors().size(); i++) {
                if (!gui.getCp().partida.getJugadors().get(i).isEliminat()) {
                    numJugadorsActius++;
                }
            }
        }
    }



}
