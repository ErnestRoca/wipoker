/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.jabber;



import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
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

    public static MultiUserChat crearSala(JID jid, XMPPConnection con) {
        MultiUserChat muc = null;
        try {
            //cadena completa per servidor jabberes.org sala@conf.jabberes.org/andres
            muc = new MultiUserChat(con, jid.getJID());
            muc.create(jid.getNick());
            muc.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
            muc.changeSubject("sala per al joc wipoker");            
        } catch (XMPPException ex) {
            Logger.getLogger(Connexio.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error creant sala");
            System.out.println(ex.getMessage());
        }
        return muc;
    }

    public static void unirseSala(MultiUserChat muc, JID jid) {
        try {
            System.out.println(jid.toString());
            muc.join(jid.getNick());
            
            //muc.join(jid.getNick());
        } catch (XMPPException ex) {
            Logger.getLogger(Connexio.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error afegint jugador");
        }
    }
}

