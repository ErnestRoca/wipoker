package domini;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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

    public Taula(byte places, byte cadiresOcupades, Baralla baralla) {
        this.places = places;
        this.cadiresOcupades = cadiresOcupades;
        this.baralla = baralla;        
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

    public void setCadiresOcupades(byte cadiresOcupades) {
        this.cadiresOcupades = cadiresOcupades;
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
