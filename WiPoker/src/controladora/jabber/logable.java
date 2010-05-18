/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladora.jabber;

import org.jivesoftware.smack.XMPPException;

/**
 *
 * @author andres
 */
public interface logable {
    public void prepararConnexio();    
    public void conectar() throws  ;
    public void desconnectar();
    public boolean estasLogat();
    public void ferLogin(String user, String password) throws XMPPException;
    

}
