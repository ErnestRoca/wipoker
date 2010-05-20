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

    private final String dni;
    private final String nomComplet;
    private final String alias;
    private final byte edat;
    private short partidesGuanyades;
    private int puntuacio;
    private String telefon;
    private int fitxesInicials;
    private int fitxesActuals;
    private final byte posicioTaula;
    private Ma maActual;
    private Aposta aposta;

    public Jugador(String dni, String nomComplet, String alias, int edat, int partidesGuanyades, int puntuacio, String telefon, int fitxesInicials, int posicioTaula) {
        this.dni = dni;
        this.nomComplet = nomComplet;
        this.alias = alias;
        this.edat = (byte) edat;
        this.partidesGuanyades = (short) partidesGuanyades;
        this.puntuacio = puntuacio;
        this.telefon = telefon;
        this.fitxesInicials = fitxesInicials;
        this.fitxesActuals = fitxesInicials;
        this.posicioTaula = (byte) posicioTaula;
    }

    public Jugador(String dni, String nomComplet, String alias, int edat, String telefon, int posicioTaula) {
        System.out.println("poma2");
        this.dni = dni;
        this.nomComplet = nomComplet;
        this.alias = alias;
        this.edat = (byte) edat;
        this.telefon = telefon;
        this.posicioTaula = (byte) posicioTaula;
    }

    public String getAlias() {
        return alias;
    }

    public String getDni() {
        return dni;
    }

    public byte getEdat() {
        return edat;
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

    public short getPartidesGuanyades() {
        return partidesGuanyades;
    }

    public void setPartidesGuanyades(int partidesGuanyades) {
        this.partidesGuanyades = (short) partidesGuanyades;
    }

    public byte getPosicioTaula() {
        return posicioTaula;
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

    public Ma getMaActual() {
        return maActual;
    }

    public void setMaActual(Ma maActual) {
        this.maActual = maActual;
    }

    public Aposta getAposta() {
        return aposta;
    }

    public void setAposta(Aposta aposta) {
        this.aposta = aposta;
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
        hash = 31 * hash + (this.dni != null ? this.dni.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        String str = nomComplet + " amb " + edat + " anys i amb " + fitxesActuals + "fitxes";
        return str;
    }
}
