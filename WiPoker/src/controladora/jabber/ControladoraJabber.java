



package controladora.jabber;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPException;

/**
 *
 * @author wida45787385
 */
public class ControladoraJabber {

   
    private XMPPConnection connection;
    private ConnectionConfiguration config;

    public ControladoraJabber(String servidor, int port) throws XMPPException {
        prepararCommunicacions(servidor, port);
    }

    public void prepararCommunicacions(String servidor, int port) throws XMPPException {
        config = new ConnectionConfiguration(servidor, port);
        connection = new XMPPConnection(config);
        connection.connect();
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
