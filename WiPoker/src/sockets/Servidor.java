/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import domini.Jugador;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wida45787385
 */

public class Servidor {

    private ServerSocket socketServidor;
    private Socket socketClient;
    private Client client;

    public Servidor(String ip, int port, Jugador jugador)  {
        try {
            socketServidor = new ServerSocket(port);
            socketClient = socketServidor.accept();
            client = new Client(ip, port, jugador);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}