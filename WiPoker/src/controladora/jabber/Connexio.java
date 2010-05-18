/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladora.jabber;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;

/**
 *
 * @author wida45787385
 */
public class Connexio {
    
    public Connexio(String servidor) {

    }

    private XMPPConnection iniciarConfiguracio(String servidor) {
        ConnectionConfiguration cc = new ConnectionConfiguration(servidor);
        cc.setSecurityMode(ConnectionConfiguration.SecurityMode.required);
        cc.setDebuggerEnabled(false);
        cc.setReconnectionAllowed(false);
        cc.setSASLAuthenticationEnabled(true);
        XMPPConnection connexio = new XMPPConnection(cc);
        return connexio;
    }

}
