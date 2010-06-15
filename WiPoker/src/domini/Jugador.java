/*
 * Jugador.java
 */
package domini;

import controladora.Torn;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * Descripció de la classe concreta Jugador.<br>
 * Modela un jugador en el domini suggerit pels requeriments funcionals
 * 
 * @author Andrés
 */
public class Jugador {

    // Atributs, variables membre o camps: Modelen les dades que la classe i les seves instàncies han de saber o conèixer en tot moment
    
    private final String dni;
    private final String nomComplet;
    private String alias;
    private byte edat;
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

    /**
     * Constructor d'instàncies de la classe.<br>
     * pre: Les indicades als paràmetres<br>
     * post: S'ha creat una instància de la classe actual i s'han assignat els valors dels paràmetres als atributs<br>
     * 
     * @param dni
     * @param nomComplet
     * @param alias
     * @param edat
     * @param telefon
     * @param posicioTaula
     * @param nomAvatar
     */
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

    /**
     * Constructor d'instàncies de la classe.<br>
     * pre: Les indicades als paràmetres<br>
     * post: S'ha creat una instància de la classe actual i s'han assignat els valors dels paràmetres als atributs<br>
     *
     * @param dni
     * @param nomComplet
     * @param alias
     * @param edat
     * @param partidesGuanyades
     * @param puntuacio
     * @param telefon
     * @param fitxesInicials
     * @param posicioTaula
     * @param avatar
     */
    public Jugador(String dni, String nomComplet, String alias, int edat, int partidesGuanyades, int puntuacio, String telefon, int fitxesInicials, int posicioTaula, String avatar) {
        this(dni, nomComplet, alias, edat, telefon, posicioTaula, avatar);
        this.partidesGuanyades = (short) partidesGuanyades;
        this.puntuacio = puntuacio;
        this.fitxesInicials = fitxesInicials;
        this.fitxesActuals = fitxesInicials;
        maActual = new Ma(new ArrayList<Carta>());
        this.torn = new Torn(this);
    }

    /**
     * Constructor d'instàncies de la classe.<br>
     * pre: Les indicades als paràmetres<br>
     * post: S'ha creat una instància de la classe actual i s'han assignat els valors dels paràmetres als atributs<br>
     * 
     * @param alias
     * @param fitxesInicials
     * @param posicioTaula
     * @param nomAvatar
     */
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

    /**
     * Retorna el valor de l'atribut alies.
     * @return alies del jugador
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Retorna el valor de l'atribut dni.
     * @return dni del jugador
     */
    public String getDni() {
        return dni;
    }

    /**
     * Retorna el valor de l'atribut edat.
     * @return edat del jugador
     */
    public byte getEdat() {
        return edat;
    }

    /**
     * Retorna el valor de l'atribut nomComplet.
     * @return nomComplet del jugador
     */
    public String getNomComplet() {
        return nomComplet;
    }

    /**
     * Retorna el valor de l'atribut posicioTaula.
     * @return posicio del jugador la taula
     */
    public byte getPosicioTaula() {
        return posicioTaula;
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

    /**
     * Retorna el valor de l'atribut aposta.
     * @return aposta feta pel jugador
     */
    public Aposta getAposta() {
        return aposta;
    }

    public void setAposta(Aposta aposta) {
        this.aposta = aposta;
    }

    /**
     * Retorna el valor de l'atribut apostaTotalRonda.
     * @return la quantitat total d'apostes en una ronda
     */
    public int getApostaTotalRonda() {
        return apostaTotalRonda;
    }

    public void setApostaTotalRonda(int apostaTotalRonda) {
        this.apostaTotalRonda = apostaTotalRonda;
    }

    /**
     * Retorna el valor de l'atribut eliminat.
     * @return true, si el jugador s'ha eliminat
     */
    public boolean isEliminat() {
        return eliminat;
    }

    public void setEliminat(boolean eliminat) {
        this.eliminat = eliminat;
    }

    /**
     * Retorna el valor de l'atribut fitxesActuals.
     * @return la quantitat de fitxes de que disposa en aquest moment
     */
    public int getFitxesActuals() {
        return fitxesActuals;
    }

    public void setFitxesActuals(int fitxesActuals) {
        this.fitxesActuals = fitxesActuals;
    }

    /**
     * Retorna el valor de l'atribut fitxesInicials.
     * @return quantitat de fitxes amb les que el jugador comença la partida
     */
    public int getFitxesInicials() {
        return fitxesInicials;
    }

    public void setFitxesInicials(int fitxesInicials) {
        this.fitxesInicials = fitxesInicials;
    }

    /**
     * Retorna el valor de l'atribut haFetAllin.
     * @return true, si el jugador ha fet all-in
     */
    public boolean HaFetAllin() {
        return haFetAllin;
    }

    public void setHaFetAllin(boolean haFetAllin) {
        this.haFetAllin = haFetAllin;
    }

    /**
     * Retorna el valor de l'atribut haFetFold.
     * @return true, si el jugador ha fet fold
     */
    public boolean isHaFetFold() {
        return haFetFold;
    }

    public void setHaFetFold(boolean haFetFold) {
        this.haFetFold = haFetFold;
    }

    /**
     * Retorna el valor de l'atribut maActual.
     * @return la ma de cartes del jugador
     */
    public Ma getMaActual() {
        return maActual;
    }

    public void setMaActual(Ma maActual) {
        this.maActual = maActual;
    }

    /**
     * Retorna el valor de l'atribut partidesGuanyades.
     * @return la quantitde opartides guanyades
     */
    public short getPartidesGuanyades() {
        return partidesGuanyades;
    }

    public void setPartidesGuanyades(int partidesGuanyades) {
        this.partidesGuanyades = (short) partidesGuanyades;
    }

    /**
     * Retorna el valor de l'atribut puntuacio.
     * @return la puntiacio del jugador
     */
    public int getPuntuacio() {
        return puntuacio;
    }

    public void setPuntuacio(int puntuacio) {
        this.puntuacio = puntuacio;
    }

    /**
     * Retorna el valor de l'atribut telefon.
     * @return el telefon del jugador
     */
    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    /**
     * Retorna el valor de l'atribut torn.
     * @return el torn en el que es trova el jugador
     */
    public Torn getTorn() {
        return torn;
    }

    public void setTorn(Torn torn) {
        this.torn = torn;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setEdat(int edat) {
        this.edat = (byte) edat;
    }

    /**
     * Compara la instància actual amb una referència a una altra instància per determinar-ne la seva "igualtat".<br>
     * pre: Les indicades als paràmetres<br>
     * post: Les indicades al return<br>
     * @param obj Una referència a una instància Object
     * @return true si considerem ambdós instàncies "iguals" (mateix objecte a nivell del domini)
     */
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
