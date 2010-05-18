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

    protected void conectar(XMPPConnection connexio) throws XMPPException {
        if (!connexio.isConnected()) {
            connexio.connect();
        } else {
            //sacar en gui un mensaje ya estas conectado
        }
    }

    protected void ferLogin(XMPPConnection connexio, String user, String password) throws XMPPException {
        connexio.login(user, password);
    }

    protected void desconnectar(XMPPConnection connexio) {
        if (connexio.isConnected()) {
            connexio.disconnect();
        } else {
            //sacar en gui un mensaje ya estas desconectado
        }
    }
}
