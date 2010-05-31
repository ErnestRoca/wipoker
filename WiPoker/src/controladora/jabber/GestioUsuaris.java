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

    public void conectar(XMPPConnection connexio) throws XMPPException {
        if (!connexio.isConnected()) {
            connexio.connect();
        } else {
            System.out.println("Ja estas conectat");
        }
    }

    public void ferLogin(XMPPConnection connexio, String user, String password) throws XMPPException {
        connexio.login(user, password);
    }

    public void desconnectar(XMPPConnection connexio) {
        if (connexio.isConnected()) {
            connexio.disconnect();
        } else {
            System.out.println("no estas conectat");
        }
    }

    public void crearCompte(XMPPConnection connexio, String nom, String password1, String password2, String mail) throws XMPPException {
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
