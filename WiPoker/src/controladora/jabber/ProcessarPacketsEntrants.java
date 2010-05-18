/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.jabber;

import controladora.ControladoraPartida;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.packet.Packet;

/**
 *
 * @author wida45787385
 */
public class ProcessarPacketsEntrants implements PacketListener {

    private ControladoraPartida cp = new ControladoraPartida((byte) 5);

    public void determinarPacket(Packet p) {

    }

    public void AssignarcartaPublica(Packet p) {
        if (p.getProperty("publica").equals("publica")) {
            
        }
    }

    public void processPacket(Packet arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
   
}
