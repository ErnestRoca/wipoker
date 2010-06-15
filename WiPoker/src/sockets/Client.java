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

        jugadorClient = jugador;

        try {
            socket = new Socket(ip, port);
            InputStream fluxeSocketEntrada = socket.getInputStream();
            OutputStream fluxeSocketSortida = socket.getOutputStream();
            fluxeEntrada = new ObjectInputStream(new DataInputStream(fluxeSocketEntrada));
            fluxeSortida = new ObjectOutputStream(new DataOutputStream(fluxeSocketSortida));
            System.out.println(fluxeEntrada == null);
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void afegirse() {
        try {
            fluxeSortida.writeObject(jugadorClient);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void apostar(int diners) throws IOException {
        fluxeSortida.writeObject(new Aposta(jugadorClient, diners));
    }

    public void rebreCarta(Carta carta) {
        jugadorClient.getMaActual().getCartes().add(carta);
    }
}
