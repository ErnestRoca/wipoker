package domini;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Andrés
 */
public class Aposta {

    private Jugador jugador;
    private double quantitat;

    public Aposta() {
    }

    public Aposta(Jugador jugador, double quantitat) {
        this.jugador = jugador;
        this.quantitat = quantitat;
    }

    public double getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(double quantitat) {
        this.quantitat = quantitat;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public String toString() {
        return String.valueOf(quantitat);
    }
}
