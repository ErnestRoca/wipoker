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
public class Connexio {

    public static XMPPConnection crearConnexio(String servidor) {

        XMPPConnection connexio = null;
        try {
            connexio = new XMPPConnection(servidor, 5222);
        } catch (XMPPException ex) {
            Logger.getLogger(Connexio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connexio;
    }
}
