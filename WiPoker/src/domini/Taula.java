package domini;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author wida45787385
 */
public class Taula {

    //numero de places per a jugar
    byte places;
    //numero de places que ja no es poden usar
    byte cadiresOcupades;
    //Objecte tipus baralla.
    //Baralla que empra el dealer
    //Baralla baralla;

    //constructor per defecte
    public Taula() {
    }

    public Taula(byte places, byte cadiresOcupades) { //Baralla baralla) {
        this.places = places;
        this.cadiresOcupades = cadiresOcupades;
        //this.baralla = baralla;
    }
}
