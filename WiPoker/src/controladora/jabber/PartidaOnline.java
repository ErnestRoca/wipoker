/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladora.jabber;

import org.jivesoftware.smack.ConnectionListener;

/**
 *
 * @author andres
 */
public class PartidaOnline implements ConnectionListener {

    public void connectionClosed() {

    }

    public void connectionClosedOnError(Exception excptn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void reconnectingIn(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void reconnectionSuccessful() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void reconnectionFailed(Exception excptn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
