/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.jabber;

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
}
