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

    protected XMPPConnection connection;

    public GestioUsuaris() {
        XMPPConnection.DEBUG_ENABLED = false;
    }

    public XMPPConnection getConnection() {
        return connection;
    }

    public void setConnection(XMPPConnection connection) {
        this.connection = connection;
    }

    public void prepararConnexio() {
        ConnectionConfiguration cc = new ConnectionConfiguration("jabber.org", 5222);
        cc.setSecurityMode(ConnectionConfiguration.SecurityMode.enabled);
        cc.setDebuggerEnabled(false);
        cc.setReconnectionAllowed(false);
        cc.setSASLAuthenticationEnabled(true);
        connection = new XMPPConnection(cc);
    }

    public void conectar() throws XMPPException {
        connection.connect();
    }

    public boolean estasLogat() {
        return connection.isAuthenticated();
    }

    public boolean ferLogin(String user, String password) {
        try {
            connection.login(user, password);
        } catch (XMPPException e) {
            return false;
        }
        return estasLogat();
    }

    public void desconnectar() {
        connection.disconnect();
    }

    public static void main(String[] args) throws XMPPException {
        GestioUsuaris g = new GestioUsuaris();
        g.prepararConnexio();
        g.connection.connect();
    }
}
