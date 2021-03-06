/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import controladora.jabber.JID;

import controladora.jabber.Listeners;
import controladora.jabber.Trafic;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.Chat;
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

/**
 *
 * @author wida45787385
 */
public class ControladoraJabber {

    private XMPPConnection connexio;
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
        prepararEscoltadorsConnexio();
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
        System.out.println("prepararEscoltadorsConnexio");
        connexio.addConnectionListener(listeners);
        connexio.addPacketListener(listeners, new PacketTypeFilter(Message.class));
    }

    public void prepararEscoltadorsSala() {
        System.out.println("prepararEscoltadorsSala");

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

    public void setSala(JID r) {
        room = r;
        muc = new MultiUserChat(connexio, r.getJID());
        prepararEscoltadorsSala();
        try {
            muc.create(room.getNick());
            muc.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
            muc.changeSubject("Sala dedicada al juego WiPPoker");
            muc.join(room.getNick());
        } catch (XMPPException ex) {
            Logger.getLogger(ControladoraJabber.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
