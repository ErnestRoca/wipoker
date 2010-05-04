/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domini;

/**
 *
 * @author ula
 */
public class IA {

    public IA() {
    }

    public double calcularAposta(Bot jugador) {
        double aposta = 0.0;
        double valorMa = jugador.getMaActual().getValorMesAlt();
        if (valorMa == 10) {
            aposta = jugador.getFitxesActuals();
        } else if (valorMa == 9) {
            aposta = jugador.getFitxesActuals();
        } else if (valorMa == 8) {
            aposta = jugador.getFitxesActuals();
        } else if (valorMa == 7) {
            aposta = jugador.getFitxesActuals();
        } else if (valorMa == 6) {
            aposta = jugador.getFitxesActuals();
        } else if (valorMa == 5) {
            aposta = jugador.getFitxesActuals();
        } else if (valorMa == 4) {
            aposta = jugador.getFitxesActuals() / 2;
        } else if (valorMa == 3) {
            aposta = jugador.getFitxesActuals() / 3;
        } else if (valorMa == 2) {
            aposta = jugador.getFitxesActuals() / 4;
        } else if (valorMa == 1) {
            aposta = 10;
        }

        return aposta;
    }
}
