/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladora.jabber;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 *
 * @author andres
 */
public interface logable {
    public void prepararConnexio();    
    public void conectar() throws  XMPPException;
    public void desconnectar();
    public boolean estasLogat();
    public boolean ferLogin(String user, String password);
    

}
