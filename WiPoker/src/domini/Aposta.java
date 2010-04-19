package domini;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Andrés
 */
public class Aposta {

    private double quantitat;

    public Aposta() {
    }

    public Aposta(double quantitat) {
        this.quantitat = quantitat;
    }

    public double getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(double quantitat) {
        this.quantitat = quantitat;
    }

    @Override
    public String toString() {
        return String.valueOf(quantitat);
    }
}
