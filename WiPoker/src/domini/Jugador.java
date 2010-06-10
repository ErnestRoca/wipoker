package domini;

import controladora.Torn;
import java.util.ArrayList;
import javax.swing.ImageIcon;

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
    private int apostaTotalRonda = 0;
    private boolean haFetFold = false;
    private boolean haFetAllin = false;
    private boolean eliminat = false;
    private Torn torn;
    private ImageIcon avatar;

    public Jugador(String dni, String nomComplet, String alias, int edat, String telefon, int posicioTaula, String nomAvatar) {
        this.dni = dni;
        this.nomComplet = nomComplet;
        this.alias = alias;
        this.edat = (byte) edat;
        this.telefon = telefon;
        this.posicioTaula = (byte) posicioTaula;
        maActual = new Ma(new ArrayList<Carta>());
        this.torn = new Torn(this);
        try {
            avatar = new ImageIcon(Carta.class.getResource("/serveis/imatges/" + nomAvatar + ".png"));
        } catch (Exception ex) {
            System.out.println("No s'ha trobat la imatge" + " associada amb el valor: avatar");
        }
    }

    public Jugador(String dni, String nomComplet, String alias, int edat, int partidesGuanyades, int puntuacio, String telefon, int fitxesInicials, int posicioTaula, String avatar) {
        this(dni, nomComplet, alias, edat, telefon, posicioTaula, avatar);
        this.partidesGuanyades = (short) partidesGuanyades;
        this.puntuacio = puntuacio;
        this.fitxesInicials = fitxesInicials;
        this.fitxesActuals = fitxesInicials;
        maActual = new Ma(new ArrayList<Carta>());
        this.torn = new Torn(this);
    }

    public Jugador(String alias, int fitxesInicials, int posicioTaula, String nomAvatar) {
        this.dni = "00000000A";
        this.nomComplet = alias;
        this.edat = 0;
        this.posicioTaula = (byte) posicioTaula;
        this.fitxesInicials = fitxesInicials;
        this.fitxesActuals = fitxesInicials;
        this.alias = alias;
        maActual = new Ma(new ArrayList<Carta>());
        this.torn = new Torn(this);
        try {
            avatar = new ImageIcon(Carta.class.getResource("/serveis/imatges/" + nomAvatar + ".png"));
        } catch (Exception ex) {
            System.out.println("No s'ha trobat la imatge" + " associada amb el valor: avatar");
        }
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

    public int getApostaTotalRonda() {
        return apostaTotalRonda;
    }

    public void setApostaTotalRonda(int apostaTotalRonda) {
        this.apostaTotalRonda = apostaTotalRonda;
    }

    public boolean isHaFetFold() {
        return haFetFold;
    }

    public void setHaFetFold(boolean haFetFold) {
        this.haFetFold = haFetFold;
    }

    public boolean isAllin() {
        return haFetAllin;
    }

    public void setHaFetAllin(boolean haFetAllin) {
        this.haFetAllin = haFetAllin;
    }

    public boolean isEliminat() {
        return eliminat;
    }

    public void setEliminat(boolean eliminat) {
        this.eliminat = eliminat;
    }

    public Torn getTorn() {
        return torn;
    }

    public void setTorn(Torn torn) {
        this.torn = torn;
    }

    /**
     * Retorna el valor de l'atribut imatge.
     * @return imatge associada a la pesa
     */
    public ImageIcon getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageIcon avatar) {
        this.avatar = avatar;
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
