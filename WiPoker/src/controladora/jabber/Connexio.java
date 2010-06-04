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

    public static XMPPConnection crearConnexio(String servidor) {
        ConnectionConfiguration cc = new ConnectionConfiguration("jabberes.org", 5222);
        cc.setSecurityMode(ConnectionConfiguration.SecurityMode.required);
        cc.setDebuggerEnabled(false);
        cc.setReconnectionAllowed(true);
        //cc.setSASLAuthenticationEnabled(true);
        registrarMecanismes();
        XMPPConnection connexio = new XMPPConnection(cc);
        return connexio;
    }

    public static void registrarMecanismes() {
        for (Class c : SASLAuthentication.getRegisterSASLMechanisms()) {
            SmackConfiguration.addSaslMech(c.getSimpleName());
            SASLAuthentication.supportSASLMechanism(c.getSimpleName());
        }
    }
}

