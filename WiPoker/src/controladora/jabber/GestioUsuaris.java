/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.jabber;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 *
 * @author wida45787385
 */
public class GestioUsuaris implements logable {

    private XMPPConnection connection;

    public GestioUsuaris(String servidor) {
       XMPPConnection.DEBUG_ENABLED = false;
    }

    public void prepararConnexio(XMPPConnection con) {
        ConnectionConfiguration cc = new ConnectionConfiguration("jabber.org", 5222);        
        cc.setSecurityMode(ConnectionConfiguration.SecurityMode.enabled);
        cc.setDebuggerEnabled(false);
        cc.setReconnectionAllowed(false);
        cc.setSASLAuthenticationEnabled(true);
        con = new XMPPConnection(cc);
    }

     public boolean estasLogat(XMPPConnection con) {
        return con.isAuthenticated();
    }

    public boolean ferLogin(String user, String password, XMPPConnection con) {
        try {
            con.login(user, password);
        } catch (XMPPException e) {
            return false;
        }
        return estasLogat(con);
    }

    public void desconnexio(XMPPConnection con) {
        con.disconnect();
    }

    public static void main(String[] args) {
        GestioUsuaris g = new GestioUsuaris("hermes.jabber.org");
        
        

        
    }
}
