/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladora.jabber;


import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.muc.InvitationListener;
import org.jivesoftware.smackx.muc.InvitationRejectionListener;
import org.jivesoftware.smackx.muc.ParticipantStatusListener;
import org.jivesoftware.smackx.muc.UserStatusListener;

/**
 *
 * @author wida45787385
 */
public class Listeners implements  ConnectionListener,  PacketListener,
        ParticipantStatusListener, UserStatusListener {

    //connectionlistener: escucha la conexion para detectar caidas

    public void connectionClosed() {
        System.out.println("connectionclosed");

    }

    public void connectionClosedOnError(Exception arg0) {
        System.out.println("connectionClosedOnError " + arg0 );

    }

    public void reconnectingIn(int arg0) {
        System.out.println("reconnectingIn " + arg0 );
    }

    public void reconnectionSuccessful() {
        System.out.println("reconnectionSuccessful");
    }

    public void reconnectionFailed(Exception arg0) {
        System.out.println("reconnectionFailed " + arg0);
    }

    //ConnectionCreationListener: detecta cuando se ha conectado a un servidor

    public void connectionCreated(XMPPConnection arg0) {
        System.out.println("s'ha creat una connexio");
    }

    //PacketListener: fa una accio quan enftra un paquet
    
    public void processPacket(Packet arg0) {
        
    }

    //ParticipantStatusListener: detecta modificacions en entrades, sortides i permisssos dels jugadors

    public void joined(String arg0) {
        System.out.println("jugador afegit " + arg0);
    }

    public void left(String arg0) {
        System.out.println("jugador eliminat " + arg0);
    }

    public void kicked(String arg0, String arg1, String arg2) {
        System.out.println("kicjed " + arg1 + "-" + arg2);
    }

    public void voiceGranted(String arg0) {
        System.out.println("voiceGranted" + arg0);
    }

    public void voiceRevoked(String arg0) {
        System.out.println("voiceRevoked " + arg0);
    }

    public void banned(String arg0, String arg1, String arg2) {
        System.out.println("banned " + arg1 + "-" + arg2 + "-");
    }

    public void membershipGranted(String arg0) {
        System.out.println("membershipGranted" + arg0);
    }

    public void membershipRevoked(String arg0) {
        System.out.println("membershipRevoked" + arg0);
    }

    public void moderatorGranted(String arg0) {
        System.out.println("moderatorGranted " + arg0);
    }

    public void moderatorRevoked(String arg0) {
        System.out.println("moderatorRevoked" + arg0);
    }

    public void ownershipGranted(String arg0) {
        System.out.println("ownershipGranted " + arg0);
    }

    public void ownershipRevoked(String arg0) {
        System.out.println("ownershipRevoked " +arg0);
    }

    public void adminGranted(String arg0) {
        System.out.println("adminGranted" + arg0);
    }

    public void adminRevoked(String arg0) {
        System.out.println("adminRevoked" + arg0);
    }

    public void nicknameChanged(String arg0, String arg1) {
        System.out.println("nicknameChanged " + arg0 + "-" + arg1);
    }

    public void afegirFiltre(XMPPConnection connexio, Class claseFiltre) {
        PacketFilter pf = new PacketTypeFilter(claseFiltre);
        connexio.createPacketCollector(pf);
    }

    //userstatuslisteners

    public void kicked(String arg0, String arg1) {
        System.out.println("jugador afegit");
    }

    public void voiceGranted() {
        
    }

    public void voiceRevoked() {
        
    }

    public void banned(String arg0, String arg1) {
        
    }

    public void membershipGranted() {
        
    }

    public void membershipRevoked() {
       
    }

    public void moderatorGranted() {
        
    }

    public void moderatorRevoked() {
        
    }

    public void ownershipGranted() {
        
    }

    public void ownershipRevoked() {
        
    }

    public void adminGranted() {
        
    }

    public void adminRevoked() {
        
    }

    public void kicked(String arg0) {
        System.out.println("kicked " + arg0);
    }

    public void banned(String arg0) {
        
    }

    public void nicknameChanged(String arg0) {
        
    }

    public void invitationReceived(XMPPConnection arg0, String arg1, String arg2, String arg3, String arg4, Message arg5) {
        
    }

    //invitation listeneer

}
