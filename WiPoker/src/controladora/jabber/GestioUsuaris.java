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
public class GestioUsuaris {

    private XMPPConnection connection;

    public GestioUsuaris() {
        XMPPConnection.DEBUG_ENABLED = false;
    }

    public XMPPConnection getConnection() {
        return connection;
    }

    public void prepararConnexio() {
        ConnectionConfiguration cc = new ConnectionConfiguration("jabberes.org", 5222);
        
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
        List<String> lista = SmackConfiguration.getSaslMechs();
        for (String c: lista) {
            System.out.println(c);
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
        System.out.println(SmackConfiguration.getVersion());
        g.ferLogin("peracho87", "Peracho45787385C");
        System.out.println(g.connection.isAuthenticated());
    }
}
