/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladora.jabber;

import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.muc.ParticipantStatusListener;

/**
 *
 * @author wida45787385
 */
public class Listeners implements ConnectionListener, ConnectionCreationListener, PacketListener, ParticipantStatusListener {

    //connectionlistener: escucha la conexion para detectar caidas

    public void connectionClosed() {

    }

    public void connectionClosedOnError(Exception arg0) {

    }

    public void reconnectingIn(int arg0) {

    }

    public void reconnectionSuccessful() {

    }

    public void reconnectionFailed(Exception arg0) {

    }

    //ConnectionCreationListener: detecta cuando se ha conectado a un servidor

    public void connectionCreated(XMPPConnection arg0) {
        
    }

    //PacketListener: fa una accio quan enftra un paquet
    
    public void processPacket(Packet arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //ParticipantStatusListener: detecta modificacions en entrades, sortides i permisssos dels jugadors

    public void joined(String arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void left(String arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void kicked(String arg0, String arg1, String arg2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void voiceGranted(String arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void voiceRevoked(String arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void banned(String arg0, String arg1, String arg2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void membershipGranted(String arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void membershipRevoked(String arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void moderatorGranted(String arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void moderatorRevoked(String arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void ownershipGranted(String arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void ownershipRevoked(String arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void adminGranted(String arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void adminRevoked(String arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void nicknameChanged(String arg0, String arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void afegirFiltre(XMPPConnection connexio, Class claseFiltre) {
        PacketFilter pf = new PacketTypeFilter(claseFiltre);
        connexio.createPacketCollector(pf);
    }

}
