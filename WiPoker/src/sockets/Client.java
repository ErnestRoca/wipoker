/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import domini.Aposta;
import domini.Carta;
import domini.Jugador;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wida45787385
 */
public class Client {

    private ObjectInputStream fluxeEntrada;
    private ObjectOutputStream fluxeSortida;
    private Socket socket;
    private Jugador jugadorClient;

    public Client(final String ip, final int port, final Jugador jugador) {


        Runnable runnable = new Runnable() {

            public void run() {
                try {
                    jugadorClient = jugador;
                    socket = new Socket(ip, port);
                    InputStream fluxeSocketEntrada = socket.getInputStream();
                    OutputStream fluxeSocketSortida = socket.getOutputStream();
                    fluxeEntrada = new ObjectInputStream(new DataInputStream(fluxeSocketEntrada));
                    fluxeSortida = new ObjectOutputStream(new DataOutputStream(fluxeSocketSortida));
                } catch (UnknownHostException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        try {
            afegirse();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void afegirse() throws IOException {
        fluxeSortida.writeObject(jugadorClient);

    }

    public void apostar(int diners) throws IOException {
        fluxeSortida.writeObject(new Aposta(jugadorClient, diners));
    }

    public void rebreCarta(Carta carta) {
        jugadorClient.getMaActual().getCartes().add(carta);
    }
}
