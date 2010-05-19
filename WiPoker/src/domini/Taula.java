package domini;

/**
 *
 * @author Andrés
 */
public class Taula {

    //numero de places per a jugar
    private final byte places;
    //numero de places que ja no es poden usar
    private byte cadiresOcupades;
    //Objecte tipus baralla.
    //Baralla que empra el dealer
    Baralla baralla;
    private Partida partidaActual;

    public Taula(int places, Baralla baralla) {
        this.places = (byte) places;
        this.baralla = baralla;
        cadiresOcupades = 0;
    }

    public Baralla getBaralla() {
        return baralla;
    }

    public void setBaralla(Baralla baralla) {
        this.baralla = baralla;
    }

    public byte getCadiresOcupades() {
        return cadiresOcupades;
    }

    public void setCadiresOcupades(int cadiresOcupades) {
        this.cadiresOcupades = (byte) cadiresOcupades;
    }

    public byte getPlaces() {
        return places;
    }

    public Partida getPartidaActual() {
        return partidaActual;
    }

    public void setPartidaActual(Partida partidaActual) {
        this.partidaActual = partidaActual;
    }

    @Override
    public String toString() {
        return "taula de " + places + "que té " + cadiresOcupades + "cadires ocupades";

    }
}
