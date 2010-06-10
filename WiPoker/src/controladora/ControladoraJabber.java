/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import controladora.jabber.Connexio;
import controladora.jabber.GestioUsuaris;

import controladora.jabber.JID;

import controladora.jabber.Listeners;
import controladora.jabber.Trafic;
import domini.Ronda;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.FromContainsFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.muc.MultiUserChat;

/**
 *
 * @author wida45787385
 */
public class ControladoraJabber {

    private XMPPConnection connexio;
    private Ronda ronda = new Ronda();
    private MultiUserChat muc;
    private JID room;
    private Listeners listeners;
    private Trafic trafic;

    public ControladoraJabber() {
        listeners = new Listeners();
        trafic = new Trafic();
        room = new JID();
        
    }

    public XMPPConnection getConnexio() {
        return connexio;
    }

    public void setConnexio(XMPPConnection connexio) {
        this.connexio = connexio;
    }

    public JID getJid() {
        return room;
    }

    public void setRoom(JID room) {
        this.room = room;
    }

    public MultiUserChat getMuc() {
        return muc;
    }

    public void setMuc(MultiUserChat muc) {
        this.muc = muc;
    }

    public Listeners getListeners() {
        return listeners;
    }

    public void prepararEscoltadorsConnexio() {
        connexio.addConnectionListener(listeners);
        connexio.addConnectionListener(listeners);
        connexio.addPacketListener(listeners, new PacketTypeFilter(Message.class));    
    }

    public void prepararEscoltadorsSala() {
        muc.addParticipantListener(listeners);
        muc.addUserStatusListener(listeners);
        muc.addParticipantStatusListener(listeners);

    }
}
