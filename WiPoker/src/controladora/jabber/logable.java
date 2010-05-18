/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladora.jabber;

import org.jivesoftware.smack.XMPPConnection;

/**
 *
 * @author andres
 */
public interface logable {
    public void prepararConnexio(XMPPConnection con);
    public boolean estasLogat(XMPPConnection con);
    public boolean ferLogin(String user, String password, XMPPConnection con);
    public void desconnexio(XMPPConnection con);
}
