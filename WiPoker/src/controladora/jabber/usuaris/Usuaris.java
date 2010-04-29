/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.jabber.usuaris;

import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 *
 * @author wida45787385
 */
public class Usuaris {

    public void ferLogin(XMPPConnection con, String nom, String password) throws XMPPException {
        con.connect();
        SASLAuthentication.unregisterSASLMechanism("DIGEST-MD5");
        con.login(nom, password);
    }

    public void crearCompte(XMPPConnection con, String nom, String password) throws XMPPException {
        con.getAccountManager().createAccount(nom, password);
    }
}
