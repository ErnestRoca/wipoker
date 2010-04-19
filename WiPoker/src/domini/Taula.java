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
    byte places;
    //numero de places que ja no es poden usar
    byte cadiresOcupades;
    //Objecte tipus baralla.
    //Baralla que empra el dealer
    Baralla baralla;

    //constructor per defecte
    public Taula() {
    }

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

    public void setPlaces(byte places) {
        this.places = places;
    }

    public String toString() {
        return "taula de " + places + "que té " + cadiresOcupades + "cadires ocupades";

    }
}
