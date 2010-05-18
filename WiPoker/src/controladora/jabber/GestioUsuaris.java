/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.jabber;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 *
 * @author wida45787385
 */
public class GestioUsuaris implements logable {

    private XMPPConnection connection;

    public GestioUsuaris() {
        XMPPConnection.DEBUG_ENABLED = false;
    }

    public XMPPConnection getConnection() {
        return connection;
    }

    public void prepararConnexio() {
        ConnectionConfiguration cc = new ConnectionConfiguration("jabberes.org", 5222);
        cc.setSecurityMode(ConnectionConfiguration.SecurityMode.enabled);
        cc.setDebuggerEnabled(false);
        cc.setReconnectionAllowed(false);
        cc.setSASLAuthenticationEnabled(true);
        connection = new XMPPConnection(cc);
    }

    public void prepararMecanismes() {
        ArrayList<String> mecanismes = new ArrayList<String>();
        List<Class> tipusMecanismes = SASLAuthentication.getRegisterSASLMechanisms();
        for (Class o : tipusMecanismes) {
            mecanismes.add(o.getSimpleName());
        }
        for (String s : mecanismes) {
            SASLAuthentication.supportSASLMechanism(s);
        }
    }

    public void conectar() throws XMPPException {
        connection.connect();
    }

    public boolean estasLogat() {
        return connection.isAuthenticated();
    }

    public void ferLogin(String user, String password) throws XMPPException {

        connection.login(user, password);
    }

    public void desconnectar() {
        connection.disconnect();
    }

    public static void main(String[] args) throws XMPPException {
        GestioUsuaris g = new GestioUsuaris();
        g.prepararConnexio();
        g.prepararMecanismes();
        g.conectar();
        System.out.println(g.connection.isConnected());
        System.out.println(g.connection.getSASLAuthentication().hasAnonymousAuthentication());
        g.connection.login(null, null, null);
    }
}
