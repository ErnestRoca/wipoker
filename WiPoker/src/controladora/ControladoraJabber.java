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

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JFrame;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.FromContainsFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.muc.HostedRoom;
import org.jivesoftware.smackx.muc.MultiUserChat;
import presentacio.jabber.BuscarSala;

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
        
        connexio.addPacketListener(listeners, new PacketTypeFilter(Message.class));
    }

    public void prepararEscoltadorsSala() {
        //muc.addParticipantListener(listeners);
        muc.addUserStatusListener(listeners);
        muc.addParticipantStatusListener(listeners);
        if (muc != null) {
            try {
                muc.addUserStatusListener(listeners);
                muc.addParticipantStatusListener(listeners);

                PacketFilter filter = new AndFilter(new PacketTypeFilter(muc.createMessage().getClass()), new FromContainsFilter(
                        muc.getRoom()));
                connexio.createPacketCollector(filter);
                connexio.addPacketListener(listeners, filter);
            } catch (Exception ex) {
                muc = null;
            }
        }
    }

    public void setSala(JID r) throws XMPPException {

        if (muc != null) {
            connexio.removePacketListener(listeners);
            muc.leave();
        }
        room = r;
        muc = new MultiUserChat(connexio, r.getJID());
        prepararEscoltadorsSala();
        Collection<HostedRoom> sales = MultiUserChat.getHostedRooms(connexio, r.getServer());
        boolean existeix = false;
        for (HostedRoom o : sales) {
            if (o.getName().equals(r.getName())) {
                existeix = true;
            }
        }
        try {
            if (!existeix) {
                
                muc.create(r.getNick());
                muc.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
                muc.changeSubject("Sala dedicada al juego WiPPoker");

            } else {
                afegirJugadoraSala(r);
            }
        } catch (Exception ex) {
        }

    }

    public void afegirJugadoraSala(JID jid) {

        if (!muc.isJoined()) {
            try {
                muc.join(jid.getNick());
            } catch (Exception ex) {
                muc = null;
            }
        }
    }
}
