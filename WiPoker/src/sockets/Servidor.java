/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import domini.Jugador;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wida45787385
 */
public class Servidor extends Thread {

    private ServerSocket socketServidor;
    private Socket socketClient;
    private Client client;
    private ObjectInputStream fluxeEntrada;
    private ObjectOutputStream fluxeSortida;
    private String ip;
    private int port;
    private Jugador jugador;

    public Servidor(final String ip, final int port, final Jugador jugador) {
        this.ip = ip;
        this.port = port;
        this.jugador =jugador;
    }

    
    @Override
    public void run() {
        try {
            socketServidor = new ServerSocket(port);
            socketClient = socketServidor.accept();
            fluxeEntrada = new ObjectInputStream(new DataInputStream(socketClient.getInputStream()));
            fluxeSortida = new ObjectOutputStream(new DataOutputStream(socketClient.getOutputStream()));
            client = new Client(ip, port, jugador);
            System.out.println("hola");           
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Jugador afegirJugadorRemot() {
        Jugador j = null;
        try {
            j = (Jugador) fluxeEntrada.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return j;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Socket getSocketClient() {
        return socketClient;
    }

    public void setSocketClient(Socket socketClient) {
        this.socketClient = socketClient;
    }

    public ServerSocket getSocketServidor() {
        return socketServidor;
    }

    public void setSocketServidor(ServerSocket socketServidor) {
        this.socketServidor = socketServidor;
    }
} 
    
