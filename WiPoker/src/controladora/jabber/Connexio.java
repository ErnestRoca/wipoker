/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.jabber;



import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.muc.MultiUserChat;

/**
 *
 * @author wida45787385
 */
public class Connexio {

    public static XMPPConnection crearConnexio(String servidor) throws XMPPException {
        /*ConnectionConfiguration cc = new ConnectionConfiguration(servidor, 5222);
        cc.setSecurityMode(ConnectionConfiguration.SecurityMode.enabled);
        cc.setDebuggerEnabled(false);
        cc.setReconnectionAllowed(true);
        cc.setSASLAuthenticationEnabled(true);
         * */
        XMPPConnection connexio = new XMPPConnection(servidor, 5222);
        registrarMecanismes();
        return connexio;
    }

    public static void registrarMecanismes() {
        /*
        for (Class c : SASLAuthentication.getRegisterSASLMechanisms()) {
            SmackConfiguration.addSaslMech(c.getSimpleName());
            SASLAuthentication.supportSASLMechanism(c.getSimpleName());
        }
         * */
    }
}

