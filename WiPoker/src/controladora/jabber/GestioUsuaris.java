/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.jabber;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 *
 * @author wida45787385
 */
public class GestioUsuaris {

    protected void ferLogin(XMPPConnection con, String nom, String password) throws XMPPException {
        if (!con.isConnected()) {
            con.connect();
        } else if (!con.isAuthenticated()) {
            SASLAuthentication.unregisterSASLMechanism("DIGEST-MD5");
            con.login(nom, password);
        }
    }

    protected void ferLogin2(XMPPConnection con) throws XMPPException {
        con.login("peracho87", "Peracho45787385C");

    }

    protected void crearCompte(XMPPConnection con, String nom, String password) throws XMPPException {
        if (!con.isConnected()) {
            con.connect();
        } else if (!con.isAuthenticated()) {
            con.getAccountManager().createAccount(nom, password);
        }
    }

    protected void tancarSessio(XMPPConnection con) {
        if (con.isConnected()) {
            con.disconnect();
        }
    }

    public static void main(String[] args) throws XMPPException {
        GestioUsuaris gu = new GestioUsuaris();
        ConnectionConfiguration conf = new ConnectionConfiguration("jabberes.org", 5222);
        XMPPConnection con = new XMPPConnection(conf);
        con.connect();
        System.out.println(con.getAccountManager().supportsAccountCreation());

        gu.ferLogin2(con);
    }
}
