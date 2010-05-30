/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.jabber;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.muc.MultiUserChat;

/**
 *
 * @author wida45787385
 */
public class Connexio {

    public Connexio(String servidor) {
        registrarMecanismes();
    }

    public static XMPPConnection crearConnexio(String servidor) {
        ConnectionConfiguration cc = new ConnectionConfiguration("jabberes.org", 5222);
        cc.setSecurityMode(ConnectionConfiguration.SecurityMode.required);
        cc.setDebuggerEnabled(false);
        cc.setReconnectionAllowed(false);
        cc.setSASLAuthenticationEnabled(true);
        XMPPConnection connexio = new XMPPConnection(cc);
        return connexio;
    }

    public static void registrarMecanismes() {
        for (Class c : SASLAuthentication.getRegisterSASLMechanisms()) {
            SmackConfiguration.addSaslMech(c.getSimpleName());
            SASLAuthentication.supportSASLMechanism(c.getSimpleName());
        }
    }

    public static MultiUserChat crearSalaChat(XMPPConnection connexio, String servidor) {
        MultiUserChat muc = new MultiUserChat(connexio, servidor);
        return muc;
    }

    public static JID crearSalaJoc(String param) {
        return new JID(param);
    }
}

