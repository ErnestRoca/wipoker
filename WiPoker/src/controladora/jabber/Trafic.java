/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controladora.jabber;

import domini.Aposta;
import domini.Carta;
import domini.Jugador;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

/**
 *
 * @author wida45787385
 */
public class Trafic {
            
    public Trafic() {
        
    }
    
    public Packet enviarCarta(Carta carta, String destinatari, boolean privada) {
        Message m = new Message(destinatari, Message.Type.NORMAL);
        m.setProperty("tipus", Carta.class);
        if (privada) {
            m.setProperty("privada", true);
        } else {
            m.setProperty("privada", false);
        }
        m.setProperty("pal", carta.getPal());
        m.setProperty("valor", carta.getValor());
        return m;
    }

    public Carta rebreCarta(Packet p, boolean privades) {
        Carta c = null;
        if (p.getProperty("tipus").equals(Carta.class)) {
            c = new Carta(new Integer(p.getProperty("pal").toString()), new Integer(p.getProperty("valor").toString()));
        }
        return c;
    }

    public Packet enviarAposta(Aposta aposta, String destinatari) {
        Message m = new Message(destinatari, Message.Type.NORMAL);
        m.setProperty("tipus", Aposta.class);
        m.setProperty("jugador", aposta.getJugador());
        m.setProperty("quantitat", aposta.getQuantitat());
        return m;
    }

    public Aposta rebreAposta(Packet p) {
        Aposta a = null;
        if (p.getProperty("tipus").equals(Aposta.class)) {
            a = new Aposta((Jugador) p.getProperty("jugador"), Double.parseDouble(p.getProperty("quantitat").toString()));
        }
        return a;
    }
}
