/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladora.jabber;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;

/**
 *
 * @author wida45787385
 */
public class Connexio {
    
    public Connexio(String servidor) {
        registrarMecanismes();        
    }

    protected XMPPConnection crearConnexio(String servidor) {
        ConnectionConfiguration cc = new ConnectionConfiguration(servidor);
        cc.setSecurityMode(ConnectionConfiguration.SecurityMode.required);
        cc.setDebuggerEnabled(false);
        cc.setReconnectionAllowed(false);
        cc.setSASLAuthenticationEnabled(true);
        XMPPConnection connexio = new XMPPConnection(cc);
        return connexio;
    }

    private void registrarMecanismes() {
        for (Class c: SASLAuthentication.getRegisterSASLMechanisms()) {
            SmackConfiguration.addSaslMech(c.getSimpleName());
            SASLAuthentication.supportSASLMechanism(c.getSimpleName());
        }
    }

    public static void main(String[] args) {
        new Connexio("jabberes.org");
    }

}
