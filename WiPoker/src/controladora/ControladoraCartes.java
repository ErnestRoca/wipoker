/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Carta;
import domini.Jugador;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author wida45787385
 */
public class ControladoraCartes {

    public boolean esEscalaReial(Jugador jugador) {
        boolean esEscalaReial = false;
        boolean escalaColor = esEscalaColor(jugador);
        boolean limits = false;
        ArrayList<Carta> cartes = jugador.getMaActual().getCartes();
        Collections.sort(cartes);
        Collections.reverse(cartes);
        int iteracions = cartes.size() - 4;
        for (int i = 0; i < iteracions; i++) {
            if ((cartes.get(i).getValor() == 14 && cartes.get(i + 4).getValor() == 10)) {
                limits = true;
            }
        }
        esEscalaReial = escalaColor && limits;
        return esEscalaReial;
    }

    public boolean esEscalaColor(Jugador jugador) {
        ArrayList<Carta> cartes = new ArrayList<Carta>(jugador.getMaActual().getCartes());
        Collections.sort(cartes);
        Collections.reverse(cartes);
        boolean mateixColor = sonMateixColor(jugador);
        boolean esEscalaColor = false;
        boolean esEscala = esEscala(jugador);
        esEscalaColor = mateixColor && esEscala;
        if (esEscalaColor) {
            jugador.getMaActual().setCombinacio((byte) 9);
            //jugador.getMaActual().setValorMesAlt((byte) valor);
        }
        return esEscalaColor;

    }

    public boolean esPoker(Jugador jugador) {
        boolean esPoker = false;
        ArrayList<Carta> cartes = jugador.getMaActual().getCartes();
        for (int i = 0; i < cartes.size(); i++) {
            int valor = 0;
            int iguals = 0;
            for (int j = i + 1; j < cartes.size(); j++) {
                if (cartes.get(i).getValor() == cartes.get(j).getValor() && valor == 0) {
                    iguals = 1;
                    valor = cartes.get(i).getValor();
                } else if (cartes.get(i).getValor() == cartes.get(j).getValor() && valor == cartes.get(i).getValor()) {
                    iguals++;
                }
                if (iguals >= 3) {
                    esPoker = true;
                    jugador.getMaActual().setValorMesAlt((byte) valor);
                }
            }
        }
        if (esPoker) {
            esPoker = true;
            jugador.getMaActual().setCombinacio((byte) 8);
        } else {
            jugador.getMaActual().setValorMesAlt((byte) 0);
        }
        return esPoker;
    }

    public boolean esFull(Jugador jugador) {
        boolean full = esParella(jugador) && esTrio(jugador);
        if (full) {
            jugador.getMaActual().setCombinacio((byte) 7);
        }
        return full;
    }

    public boolean sonMateixColor(Jugador jugador) {
        ArrayList<Carta> cartes = jugador.getMaActual().getCartes();
        boolean mateixColor = false;
        Collections.sort(cartes);
        Collections.reverse(cartes);
        int valor = 0;
        for (int i = 0; i < cartes.size(); i++) {
            int igualColor = 0;
            byte color = cartes.get(i).getPal();
            valor = cartes.get(i).getValor();
            for (int j = i + 1; j < cartes.size(); j++) {
                if (cartes.get(j).getPal() == color) {
                    igualColor++;
                    valor = cartes.get(j).getValor();
                }
            }
            if (igualColor >= 4) {
                mateixColor = true;
                jugador.getMaActual().setCombinacio((byte) 6);
                jugador.getMaActual().setValorMesAlt((byte) valor);
            }
        }
        if (!mateixColor) {
            jugador.getMaActual().setValorMesAlt((byte) 0);
        }
        return mateixColor;
    }

    public boolean esEscala(Jugador jugador) {
        ArrayList<Carta> cartes = new ArrayList<Carta>(jugador.getMaActual().getCartes());
        Collections.sort(cartes);
        Collections.reverse(cartes);
        boolean esEscala = false;
        int consecutives = 0;
        int valor = 0;
        int iteracions = cartes.size() - 4;

        for (int i = 0; i < iteracions; i++) {
            for (int j = iteracions - 1; j < (iteracions + 3); j++) {
                if ((cartes.get(j).getValor() - cartes.get(j + 1).getValor()) == 1) {
                    valor = cartes.get(j).getValor();
                    consecutives++;
                }
            }
            if (consecutives <= 3) {
                consecutives = 0;
                valor = 0;
            }
        }
        if (consecutives >= 4) {
            esEscala = true;
            jugador.getMaActual().setCombinacio((byte) 5);
            jugador.getMaActual().setValorMesAlt((byte) valor);
        }
        if (!esEscala && cartes.get(0).getValor() == 14) {
            cartes.add(new Carta((byte) cartes.get(0).getPal(), (byte) 1));
            cartes.remove(0);
            for (int i = 0; i < iteracions; i++) {
                for (int j = iteracions - 1; j < (iteracions + 3); j++) {
                    if ((cartes.get(j).getValor() - cartes.get(j + 1).getValor()) == 1) {
                        valor = cartes.get(j).getValor();
                        consecutives++;
                    }
                }
                if (consecutives <= 3) {
                    consecutives = 0;
                    valor = 0;
                }
            }
        }
        if (consecutives >= 4) {
            esEscala = true;
            jugador.getMaActual().setCombinacio((byte) 5);
            jugador.getMaActual().setValorMesAlt((byte) valor);
        }
        return esEscala;
    }

    public boolean esTrio(Jugador jugador) {
        ArrayList<Carta> cartes = jugador.getMaActual().getCartes();
        int numCartes1 = 1;
        int numCartes2 = 1;
        int valorTrio1 = 0;
        int valorTrio2 = 0;
        for (int i = 0; i < cartes.size(); i++) {
            for (int j = i + 1; j < cartes.size(); j++) {
                if (cartes.get(i).equals(cartes.get(j)) && numCartes1 == 1 && numCartes2 == 1) {
                    numCartes1++;
                    valorTrio1 =
                            cartes.get(i).getValor();
                } else if (cartes.get(i).equals(cartes.get(j)) && numCartes1 > 1 && cartes.get(i).getValor() == valorTrio1) {
                    numCartes1++;
                } else if (cartes.get(i).equals(cartes.get(j)) && numCartes2 == 1 && cartes.get(i).getValor() != valorTrio1) {
                    numCartes2++;
                    valorTrio2 =
                            cartes.get(i).getValor();
                } else if (cartes.get(i).equals(cartes.get(j)) && numCartes2 > 1 && cartes.get(i).getValor() == valorTrio2) {
                    numCartes2++;
                }
            }
        }
        boolean esTrio = numCartes1 >= 3 || numCartes2 >= 3;
        byte max = (byte) (valorTrio1 > valorTrio2 ? valorTrio1 : valorTrio2);
        if (esTrio) {
            jugador.getMaActual().setCombinacio((byte) 4);
            jugador.getMaActual().setValorMesAlt((byte) max);
        }

        return esTrio;
    }

    public boolean esDobleParella(Jugador jugador) {
        ArrayList<Carta> cartes = jugador.getMaActual().getCartes();
        int valorParella1 = -1;
        int valorParella2 = -1;
        int valorParella3 = -1;
        int numParelles = 0;
        for (int i = 0; i < cartes.size(); i++) {
            for (int j = i + 1; j < cartes.size(); j++) {
                if (cartes.get(i).equals(cartes.get(j))) {
                    if (valorParella1 == -1) {
                        valorParella1 = cartes.get(i).getValor();
                        numParelles++;

                    } else if (valorParella2 == -1 && valorParella1 != -1) {
                        valorParella2 = cartes.get(i).getValor();
                        numParelles++;

                    } else if (valorParella3 == -1 && valorParella2 != -1) {
                        valorParella3 = cartes.get(i).getValor();
                        numParelles++;

                    }
                }
            }
        }

        boolean dobleParella = numParelles >= 2 ? true : false;
        byte valor1 = 0;
        byte valor2 = 0;
        if (dobleParella) {
            if ((valorParella1 > valorParella2) && valorParella1 > valorParella3) {
                jugador.getMaActual().setCombinacio((byte) 3);
                jugador.getMaActual().setValorMesAlt((byte) valorParella1);
                valor1 = (byte) valorParella1;
                valor2 = (byte) (valorParella2 > valorParella3 ? valorParella2 : valorParella3);
            } else if (valorParella2 > valorParella1 && valorParella2 > valorParella3) {
                jugador.getMaActual().setCombinacio((byte) 3);
                valor1 = (byte) valorParella2;
                jugador.getMaActual().setValorMesAlt((byte) valorParella2);
                valor2 = (byte) (valorParella1 > valorParella3 ? valorParella1 : valorParella3);
            } else if (valorParella3 > valorParella1 && valorParella3 > valorParella2) {
                jugador.getMaActual().setCombinacio((byte) 3);
                jugador.getMaActual().setValorMesAlt((byte) valorParella3);
                valor1 = (byte) valorParella3;
                valor2 = (byte) (valorParella1 > valorParella2 ? valorParella1 : valorParella2);
            }
            for (int i = 0; i < 2; i++) {
                byte desempat = 0;
                if (cartes.get(i).getValor() != valor1 && cartes.get(i).getValor() != valor2 && cartes.get(i).getValor() > desempat) {
                    desempat = cartes.get(i).getValor();
                    jugador.getMaActual().setValorDesempat(desempat);
                }
            }
        }


        return dobleParella;
    }

    public boolean esParella(Jugador jugador) {
        int iguals = 0;
        ArrayList<Carta> cartes = jugador.getMaActual().getCartes();
        int valor = -1;
        for (int i = 0; i < cartes.size(); i++) {
            for (int j = i + 1; j < cartes.size(); j++) {
                if (cartes.get(i).equals(cartes.get(j))) {
                    valor = cartes.get(i).getValor();
                    iguals++;
                }
            }
        }
        boolean esParella = iguals >= 1;
        if (esParella) {
            jugador.getMaActual().setCombinacio((byte) 2);
            jugador.getMaActual().setValorMesAlt((byte) valor);
            byte desempat = 0;
            for (int i = 0; i < 2; i++) {
                if (cartes.get(i).getValor() != valor && cartes.get(i).getValor() > desempat) {
                    desempat = cartes.get(i).getValor();
                    jugador.getMaActual().setValorDesempat(desempat);
                }
            }
        }
        return esParella;
    }

    public boolean valorMesAlt(Jugador jugador) {
        byte num = 0;
        ArrayList<Carta> cartes = jugador.getMaActual().getCartes();
        for (int i = 0; i < cartes.size(); i++) {
            if (cartes.get(i).getValor() > num) {
                num = cartes.get(i).getValor();
            }

        }
        jugador.getMaActual().setCombinacio((byte) 1);
        jugador.getMaActual().setValorMesAlt(num);
        return true;
    }
}
