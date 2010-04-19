package domini;


/**
 *
 * @author ernest
 */
public class Carta {

    private byte pal;
    private byte valor;

    public Carta(byte pal, byte valor) {
        this.pal = pal;
        this.valor = valor;
    }

    public byte getPal() {
        return pal;
    }

    public void setPal(byte pal) {
        this.pal = pal;
    }

    public byte getValor() {
        return valor;
    }

    public void setValor(byte valor) {
        this.valor = valor;
    }

    //Una Carta serà diferent quan el seu pal i el seu valor siguin diferents.
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carta other = (Carta) obj;
        if (this.pal != other.pal) {
            return false;
        }
        if (this.valor != other.valor) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.pal;
        hash = 31 * hash + this.valor;
        return hash;
    }

    @Override
    public String toString() {
        return "Carta: " + valor + " de " + pal + "\n";
    }
}
