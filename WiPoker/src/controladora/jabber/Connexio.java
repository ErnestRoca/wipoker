/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.jabber;



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
        //cadena completa per servidor jabberes.org sala@conf.jabberes.org/andres
        
        MultiUserChat muc = new MultiUserChat(con, jid.getFullJID());
        try {
            muc.create(jid.name);
            muc.sendConfigurationForm(new Form(Form.TYPE_SUBMIT));
            muc.changeSubject("sala per al joc wipoker");
        } catch (Exception e) {
            System.out.println("error creant sala");
            System.out.println(e.getMessage());
        }
        try {
            if (!muc.isJoined()) {
                muc.join(jid.getNick());
            }
        } catch (Exception e) {
            System.out.println("error entrant sala");
        }
        
        return muc;
    }
}

