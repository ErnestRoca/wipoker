package domini;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ula
 */
public class Ronda {
    //Jugador que guanya la ronda
    Jugador jugadorGuanyador;
    //pot de la ronda
    double pot;
    //Constructor per defecte
    public Ronda() {
    }

    public Ronda(Jugador jugadorGuanyador, double pot) {
        this.jugadorGuanyador = jugadorGuanyador;
        this.pot = pot;
    }

    public Jugador getJugadorGuanyador() {
        return jugadorGuanyador;
    }

    public void setJugadorGuanyador(Jugador jugadorGuanyador) {
        this.jugadorGuanyador = jugadorGuanyador;
    }

    public double getPot() {
        return pot;
    }

    public void setPot(double pot) {
        this.pot = pot;
    }

    @Override
    public String toString() {
        return ("Pot : " + pot  + ", guanyador: " + jugadorGuanyador.nomComplet);
    }
}
