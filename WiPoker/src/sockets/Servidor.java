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

    public Servidor(final String ip, final int port, final Jugador jugador) {
        try {            
            socketServidor = new ServerSocket(port);                        
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            escoltar();
            System.out.println("hola");
            afegirseClient(ip, port, jugador);
        }
        
    }

    public void escoltar() {
        
        Thread t = new Thread() {            

            @Override
            public void run() {
                try {
                    socketClient = socketServidor.accept();
                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t.start();
        
    }

    public void afegirseClient(final String ip, final int port, final Jugador jugador) {
        client = new Client(ip, port, jugador);
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
    
