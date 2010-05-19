/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladora;

import controladora.jabber.Connexio;
import domini.Aposta;
import domini.Ronda;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Packet;

/**
 *
 * @author wida45787385
 */
public class ControladoraJabber implements PacketListener {

    public XMPPConnection connexio;
    public Ronda ronda = new Ronda();
    
    public ControladoraJabber(String servidor) {
        connexio = Connexio.crearConnexio(servidor);
        
    }

    public void afegirAlPot(Packet aposta) {
        PacketFilter pfAposta = new PacketTypeFilter(Aposta.class);
        pfAposta.accept(aposta);
        int diners = (Integer) aposta.getProperty("diners");
        ronda.setPot(ronda.getPot() + diners);

    }

    public void processPacket(Packet arg0) {
        afegirAlPot(arg0);
    }

}
