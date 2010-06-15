
/*
 * Fase.java
 */
package domini;

import java.util.ArrayList;

/**
 * Descripció de la classe concreta Fase.<br>
 * Modela una Fase en el domini suggerit pels requeriments funcionals
 * 
 * @author ula
 */
public class Fase {

    // Atributs, variables membre o camps: Modelen les dades que la classe i les seves instàncies han de saber o conèixer en tot moment
    //Nom de la fase (pre-flop, flop, turn. river)
    private final String nomFase;
    private int apostaMinima;
    private static byte numFase = 0;
    private static final String[] nomFases = {"pre-flop", "flop", "turn", "river"};
    // Pseudoatributs per establir i mantenir connexions permanents entre la instància actual i altres instàncies
    private Ronda ronda;
    private ArrayList<Aposta> apostes = new ArrayList<Aposta>();

    /**
     * Constructor d'instàncies de la classe.<br>
     * pre: Les indicades als paràmetres<br>
     * post: S'ha creat una instància de la classe actual i s'han assignat els valors
     * dels paràmetres als atributs<br>
     *
     */
    public Fase() {
        nomFase = nomFases[numFase];
    }

    /**
     * Constructor d'instàncies de la classe.<br>
     * pre: Les indicades als paràmetres<br>
     * post: S'ha creat una instància de la classe actual i s'han assignat els valors
     * dels paràmetres als atributs<br>
     *
     * @param nomFase
     * @param ronda
     * @param apostaMinima
     */
    public Fase(String nomFase, Ronda ronda, int apostaMinima) {
        numFase++;
        this.nomFase = nomFase;
        this.ronda = ronda;
        this.apostaMinima = apostaMinima;

    }

    /**
     * Constructor d'instàncies de la classe.<br>
     * pre: Les indicades als paràmetres<br>
     * post: S'ha creat una instància de la classe actual i s'han assignat els valors
     * dels paràmetres als atributs<br>
     * 
     * @param nomFase
     * @param aposta
     */
    public Fase(String nomFase, int aposta) {
        numFase++;
        this.nomFase = nomFase;
    }

    /**
     * Retorna el valor de l'atribut nomFase.
     * @return el pat de la carta
     */
    public String getNomFase() {
        return nomFase;
    }

    /**
     * Retorna el valor de l'atribut numFase.
     * @return el pat de la carta
     */
    public static byte getNumFase() {
        return numFase;
    }

    /**
     * Retorna el valor de l'atribut nomFases.
     * @return el pat de la carta
     */
    public static String[] getNomFases() {
        return nomFases;
    }

    public static void setNumFase(int numFase) {
        Fase.numFase = (byte) numFase;
    }

    /**
     * Retorna el valor de l'atribut ronda.
     * @return el pat de la carta
     */
    public Ronda getRonda() {
        return ronda;
    }

    /**
     * El nou valor de ronda
     * @param ronda
     */
    public void setRonda(Ronda ronda) {
        this.ronda = ronda;
    }

    /**
     * Retorna el valor de l'atribut apostes.
     * @return les apostes realitzades pels jugadors
     */
    public ArrayList<Aposta> getApostes() {
        return apostes;
    }

    /**
     * El nou valor d'apostes
     * @param apostes
     */
    public void setApostes(ArrayList<Aposta> apostes) {
        this.apostes = apostes;
    }

    /**
     * Retorna el valor de l'atribut apostaMinima.
     * @return l'aposta minima que han de fer els jugadors, en apostar
     */
    public int getApostaMinima() {
        return apostaMinima;
    }

    /**
     * El nou valor d'apostaMinima
     * @param apostaMinima
     */
    public void setApostaMinima(int apostaMinima) {
        this.apostaMinima = apostaMinima;
    }

    /**
     * Compara la instància actual amb una referència a una altra instància per determinar-ne la seva "igualtat".<br>
     * pre: Les indicades als paràmetres<br>
     * post: Les indicades al return<br>
     * @param obj Una referència a una instància Object
     * @return true si considerem ambdós instàncies "iguals" (mateix objecte a nivell del domini).
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Fase other = (Fase) obj;
        if ((this.nomFase == null) ? (other.nomFase != null) : !this.nomFase.equals(other.nomFase)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + (this.nomFase != null ? this.nomFase.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return ("Fase: " + nomFase + " amb numero: " + numFase);
    }
}
