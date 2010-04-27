package controladora.jabber;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPException;

/**
 *
 * @author wida45787385
 */
public abstract class GestioUsuaris {

    private ConnectionConfiguration config = new ConnectionConfiguration("", 0);
    private XMPPConnection connection = new XMPPConnection(config);

    public GestioUsuaris() throws XMPPException {
    }

    public void crearUsuari(String dni, String nom, String alias, byte edat, String telefon) {
    }

    public void login(String usuari, String password) throws XMPPException {
        connection.login(usuari, password);
    }

    public void desconnectar() {
        connection.disconnect();
    }
}
