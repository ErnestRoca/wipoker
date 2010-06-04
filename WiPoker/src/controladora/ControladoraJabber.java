/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import controladora.jabber.Connexio;

import controladora.jabber.JID;

import controladora.jabber.Listeners;
import controladora.jabber.Trafic;
import domini.Ronda;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.FromContainsFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
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

    public MultiUserChat getMuc() {
        return muc;
    }

    public void crearSala(JID jid) {
        //cadena completa per servidor jabberes.org sala@conf.jabberes.org/andres
        room = jid;
        muc = new MultiUserChat(connexio, jid.getJID());
        try {
            muc.create(room.getNick());
            muc.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
            muc.changeSubject("sala per al joc wipoker");
        } catch (Exception e) {
            System.out.println("error creant sala");
        }
        try {
            if (!muc.isJoined()) {
                muc.join(room.getNick());
            }
        } catch (Exception e) {
            System.out.println("error entrant sala");
        }
        if (muc != null) {
            try {
                muc.addParticipantStatusListener(listeners);
                muc.addUserStatusListener(listeners);
                PacketFilter pf = new AndFilter(new PacketTypeFilter(muc.createMessage().getClass()),
                        new FromContainsFilter(muc.getRoom()));
                connexio.createPacketCollector(pf);
                connexio.addPacketListener(listeners, pf);
            } catch (Exception e) {
                System.out.println("error afegint escoltadors");
            }
        }
    }
}
