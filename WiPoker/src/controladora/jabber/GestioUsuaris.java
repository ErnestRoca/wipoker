/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.jabber;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 *
 * @author wida45787385
 */
public class GestioUsuaris {    
/*
    public static void connectar(XMPPConnection c) {
        if (!c.isConnected()) {
            try {
                c.connect();
            } catch (XMPPException ex) {
                Logger.getLogger(GestioUsuaris.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
*/
    public static void ferLogin(final XMPPConnection connexio, final String user, final String password) throws XMPPException {
        if (connexio.isConnected()) {
            connexio.login(user, password);
        } /*else {
            connexio.connect();
            connexio.login(user, password);
        }*/
    }

    public static void desconnectar(XMPPConnection connexio) {
        if (connexio.isConnected()) {
            connexio.close();
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
