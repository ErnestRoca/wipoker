/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.jabber;

import domini.Jugador;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
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
public class Connexio {

    public static XMPPConnection crearConnexio(String servidor) throws XMPPException {
        XMPPConnection connexio = new XMPPConnection(servidor, 5222);
        return connexio;
    }

    

    /*public static MultiUserChat crearSala(JID jid, XMPPConnection con) {
    MultiUserChat muc = null;
    try {
    //cadena completa per servidor jabberes.org sala@conf.jabberes.org/andres
    muc = new MultiUserChat(con, jid.getJID());
    muc.create(jid.getName());
    muc.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
    muc.changeSubject("sala per al joc wipoker");
    } catch (XMPPException ex) {
    Logger.getLogger(Connexio.class.getName()).log(Level.SEVERE, null, ex);
    System.out.println("error creant sala");
    System.out.println(ex.getMessage());
    }
    return muc;
    }

    public static int buscarsala(JFrame j) {
    BuscarSala sala = new BuscarSala(j, true);
    sala.setVisible(false);
    if (sala.func) {

    setRoom(sala.room);
    sala = null;
    int ocupacio = gui.getCjabber().getMuc().getOccupantsCount();
    if (ocupacio < 0) {
    }
    }
    }

    public static void unirseSala(MultiUserChat muc, JID jid) {
    try {
    if (!muc.isJoined()) {
    muc.join(jid.getNick());
    }
    } catch (XMPPException ex) {
    Logger.getLogger(Connexio.class.getName()).log(Level.SEVERE, null, ex);
    System.out.println("error afegint jugador");
    }
    }*/
}

