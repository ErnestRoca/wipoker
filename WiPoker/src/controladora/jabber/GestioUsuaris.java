/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.jabber;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 *
 * @author wida45787385
 */
public class GestioUsuaris {

    public static void conectar(XMPPConnection connexio) throws XMPPException {
        Thread t = new Thread();
        t
        if (!connexio.isConnected()) {
            connexio.connect();
        } else {
            System.out.println("Ja estas conectat");
        }
    }

    public static void ferLogin(XMPPConnection connexio, String user, String password) throws XMPPException {
        if (!connexio.isAuthenticated() && connexio.isConnected()) {
            connexio.login(user, password);
        }         
    }

    public static void desconnectar(XMPPConnection connexio) {
        if (connexio.isConnected()) {
            connexio.disconnect();
        } else {
            System.out.println("no estas conectat");
        }
    }

    public static void crearCompte(XMPPConnection connexio, String nom, String password1, String password2, String mail) throws XMPPException {
        if (connexio.getAccountManager().supportsAccountCreation()) {
            if (password1.equals(password2)) {
                connexio.getAccountManager().createAccount(nom, password1);
            } else {
                System.out.println("les contrasenyes no coincideixen");
            }
        } else {
            System.out.println("aquest servidor no suporta la creacio de comptes");
        }
    }
}
