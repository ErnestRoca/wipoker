package domini;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eva
 */
public class Jugador {

    String dni;
    String nomComplet;
    String alias;
    byte edat;
    short partidesGuanyades;
    int puntuacio;
    String telefon;
    int fitxesInicials;
    int fitxesActuals;
    byte posicioTaula;

    public Jugador() {
    }

    public Jugador(String dni, String nomComplet, String alias, byte edat, String telefon, byte posicioTaula,
            short partidesGuanyades, int puntuacio, int fitxesInicials, int fitxesActuals) {
        this(partidesGuanyades, puntuacio, fitxesInicials, fitxesActuals);
        this.dni = dni;
        this.nomComplet = nomComplet;
        this.alias = alias;
        this.edat = edat;
        this.telefon = telefon;
        this.posicioTaula = posicioTaula;
    }

    public Jugador(short partidesGuanyades, int puntuacio, int fitxesInicials, int fitxesActuals) {
        this.partidesGuanyades = partidesGuanyades;
        this.puntuacio = puntuacio;
        this.fitxesInicials = fitxesInicials;
        this.fitxesActuals = fitxesActuals;
    }
}
