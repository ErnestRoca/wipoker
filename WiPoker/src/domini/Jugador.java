package domini;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrés
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public byte getEdat() {
        return edat;
    }

    public void setEdat(byte edat) {
        this.edat = edat;
    }

    public int getFitxesActuals() {
        return fitxesActuals;
    }

    public void setFitxesActuals(int fitxesActuals) {
        this.fitxesActuals = fitxesActuals;
    }

    public int getFitxesInicials() {
        return fitxesInicials;
    }

    public void setFitxesInicials(int fitxesInicials) {
        this.fitxesInicials = fitxesInicials;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public short getPartidesGuanyades() {
        return partidesGuanyades;
    }

    public void setPartidesGuanyades(short partidesGuanyades) {
        this.partidesGuanyades = partidesGuanyades;
    }

    public byte getPosicioTaula() {
        return posicioTaula;
    }

    public void setPosicioTaula(byte posicioTaula) {
        this.posicioTaula = posicioTaula;
    }

    public int getPuntuacio() {
        return puntuacio;
    }

    public void setPuntuacio(int puntuacio) {
        this.puntuacio = puntuacio;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jugador other = (Jugador) obj;
        if ((this.dni == null) ? (other.dni != null) : !this.dni.equals(other.dni)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.dni != null ? this.dni.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString () {
        String str = nomComplet + " amb edat " + edat + " i puntuació "  + puntuacio;
        return str;
    }
}
