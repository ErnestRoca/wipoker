//Coooooooorfejwksrtsarhgohaerjgfdsgn

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Carta;
import domini.Jugador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        byte valorPoker = 0;
        ArrayList<Carta> cartes = jugador.getMaActual().getCartes();
        for (int i = 0; i < cartes.size(); i++) {
            int valor = 0;
            int iguals = 0;
            for (int j = i + 1; j < cartes.size(); j++) {
                if (cartes.get(i).getValor() == cartes.get(j).getValor() && valor == 0) {
                    iguals = 1;
                    valor = cartes.get(i).getValor();
                    valorPoker = (byte) valor;
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
            byte desempat = 0;
            for (int i = 0; i < cartes.size(); i++) {
                if (cartes.get(i).getValor() != valorPoker && cartes.get(i).getValor() > desempat) {
                    desempat = cartes.get(i).getValor();
                    jugador.getMaActual().setValorDesempat(desempat);
                }
            }
        } else {
            jugador.getMaActual().setValorMesAlt((byte) 0);
        }
        return esPoker;
    }

    public boolean esFull(Jugador jugador) {
        boolean esTrio = esTrio(jugador);
        byte valorTrio = jugador.getMaActual().getValorDesempat();
        //System.out.println("parella: " + valorParella + " trio: " + valorTrio);
        int iguals = 0;
        ArrayList<Carta> cartes = jugador.getMaActual().getCartes();
        int valor = -1;
        for (int i = 0; i < cartes.size(); i++) {
            for (int j = i + 1; j < cartes.size(); j++) {
                if (cartes.get(i).getValor() == cartes.get(j).getValor() && cartes.get(i).getValor() != valorTrio) {
                    valor = cartes.get(i).getValor();
                    iguals++;
                }
            }
        }
        boolean esParella = iguals >= 1;

        boolean full = esParella(jugador) && esTrio;
        if (full) {
            jugador.getMaActual().setCombinacio((byte) 7);
            jugador.getMaActual().setValorMesAlt(valorTrio);
            jugador.getMaActual().setValorDesempat((byte) 0);
        } else {
            jugador.getMaActual().setCombinacio((byte) 0);
            jugador.getMaActual().setValorMesAlt((byte) 0);
            jugador.getMaActual().setValorDesempat((byte) 0);
        }
        return full;
    }

    public boolean sonMateixColor(Jugador jugador) {
        ArrayList<Carta> cartes = jugador.getMaActual().getCartes();
        Collections.sort(cartes, new Comparator() {

            public int compare(Object o1, Object o2) {
                Carta c1 = (Carta) o1;
                Carta c2 = (Carta) o1;
                return c1.getPal() - c2.getPal();
            }
        });

        ArrayList<Carta> cartesColor = new ArrayList<Carta>();
        ArrayList<Carta> cartesNoColor = new ArrayList<Carta>();
        boolean mateixColor = false;
        Collections.sort(cartes);
        Collections.reverse(cartes);
        byte color = 0;
        for (int col = 0; col <= 3; col++) {
            int igualColor = 0;
            color = (byte) col;
            for (int i = 0; i < cartes.size(); i++) {
                System.out.println("col = " + col);
                System.out.println(" cond " + (cartes.get(i).getPal() == col));
                if (cartes.get(i).getPal() == col) {
                    igualColor++;
                    color = cartes.get(i).getPal();
                    cartesColor.add(cartes.get(i));
                } else {
                    cartesNoColor.add(cartes.get(i));
                }
            }
            if (igualColor >= 5) {
                mateixColor = true;
                jugador.getMaActual().setCombinacio((byte) 6);
                break;
            } else {
                color = 0;
                cartesColor.clear();
                cartesNoColor.clear();
            }
        }
        if (mateixColor) {
            Collections.sort(cartesColor);
            Collections.reverse(cartesColor);
            if (mateixColor) {
                byte desempat = 0;
                for (Carta c : cartesNoColor) {
                    if (c.getPal() != color && c.getValor() > desempat) {
                        desempat = c.getValor();
                    }
                }

                jugador.getMaActual().setValorMesAlt(cartesColor.get(0).getValor());
                jugador.getMaActual().setValorDesempat(desempat);
            }
        }

        return mateixColor;
    }

    public boolean esEscala(Jugador jugador) {
        ArrayList<Carta> cartes = jugador.getMaActual().getCartes();
        ArrayList<Carta> comb1 = new ArrayList<Carta>();
        ArrayList<Carta> comb2 = new ArrayList<Carta>();
        ArrayList<Carta> comb3 = new ArrayList<Carta>();
        Collections.sort(cartes);
        Collections.reverse(cartes);
        int iteracions = cartes.size() - 4;
        for (int i = 0; i < cartes.size() - 1; i++) {
            if (cartes.get(i).equals(cartes.get(i + 1))) {
                cartes.remove(i);
            }
        }
        boolean esEscala = false;
        int consecutives = 0;
        int valor = 0;
        iteracions = cartes.size() - 4;
        boolean escalaTrencada = false;
        int valors = 0;
        for (int i = 0; i < cartes.size(); i++) {
            if (cartes.get(i).getValor() == 14 || cartes.get(i).getValor() == 2 ||
                    cartes.get(i).getValor() == 3 || cartes.get(i).getValor() == 4 || cartes.get(i).getValor() == 5) {
                valors++;
            }
        }
        if (valors == 5) {
            escalaTrencada = true;
            esEscala = true;
            jugador.getMaActual().setValorMesAlt((byte) (14));
            jugador.getMaActual().setCombinacio((byte) 5);
        }
        if (!escalaTrencada) {
            for (int i = 0; i < iteracions; i++) {
                for (int j = i; j < (i + 4); j++) {
                    if ((cartes.get(j).getValor() - cartes.get(j + 1).getValor()) == 1) {
                        if (i == 0) {
                            comb1.add(cartes.get(j));
                            consecutives++;
                        } else if (i == 1) {
                            comb2.add(cartes.get(j));
                            consecutives++;
                        } else if (i == 2) {
                            comb3.add(cartes.get(j));
                            consecutives++;
                        }
                    }
                    System.out.println("iteracio " + i + " num " + j + " carta " + cartes.get(j) + "cons= " + consecutives);
                }
                if (consecutives <= 3) {
                    consecutives = 0;
                    valor = 0;
                }
            }
            System.out.println(comb1.size() + " / " + comb2.size() + " / " + comb3.size());
            if (consecutives >= 4) {
                esEscala = true;
                jugador.getMaActual().setCombinacio((byte) 5);
                if (comb1.size() == 4) {
                    valor = comb1.get(0).getValor();
                } else if (comb2.size() == 4) {
                    valor = comb2.get(0).getValor();
                } else if (comb3.size() == 4) {
                    valor = comb3.get(0).getValor();
                }
                jugador.getMaActual().setValorMesAlt((byte) (valor));
                consecutives = 0;
            }
        }
        byte desempat = 0;
        for (int i = 0; i < cartes.size(); i++) {
            if (cartes.get(i).getValor() > valor && cartes.get(i).getValor() > desempat) {
                desempat = cartes.get(i).getValor();
            } else if (cartes.get(i).getValor() < (valor - 5) && cartes.get(i).getValor() > desempat) {
                desempat = cartes.get(i).getValor();
            }
        }
        jugador.getMaActual().setValorDesempat(desempat);



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
                    valorTrio1 = cartes.get(i).getValor();
                } else if (cartes.get(i).getValor() == cartes.get(j).getValor() && numCartes1 > 1 && cartes.get(i).getValor() == valorTrio1) {
                    numCartes1++;
                } else if (cartes.get(i).getValor() == cartes.get(j).getValor() && numCartes2 == 1 && cartes.get(i).getValor() != valorTrio1) {
                    numCartes2++;
                    valorTrio2 = cartes.get(j).getValor();
                } else if (cartes.get(i).getValor() == cartes.get(j).getValor() && numCartes2 > 1 && cartes.get(i).getValor() == valorTrio2) {
                    numCartes2++;
                }
            }
        }
        boolean esTrio1 = numCartes1 >= 3;
        boolean esTrio2 = numCartes2 >= 3;
        if (esTrio1 && esTrio2) {
            byte max = (byte) (valorTrio1 > valorTrio2 ? valorTrio1 : valorTrio2);
            jugador.getMaActual().setCombinacio((byte) 4);
            jugador.getMaActual().setValorMesAlt((byte) max);
            byte valor = (byte) (valorTrio1 > valorTrio2 ? valorTrio1 : valorTrio2);
            byte desempat = 0;
            for (int i = 0; i < cartes.size(); i++) {
                if (cartes.get(i).getValor() != valor && cartes.get(i).getValor() > desempat) {
                    desempat = cartes.get(i).getValor();
                    jugador.getMaActual().setValorDesempat(desempat);
                }
            }
        } else if (esTrio1 && !esTrio2) {
            jugador.getMaActual().setCombinacio((byte) 4);
            jugador.getMaActual().setValorMesAlt((byte) valorTrio1);
            byte desempat = 0;
            for (int i = 0; i < cartes.size(); i++) {
                if (cartes.get(i).getValor() != valorTrio1 && cartes.get(i).getValor() > desempat) {
                    desempat = cartes.get(i).getValor();
                    jugador.getMaActual().setValorDesempat(desempat);
                }
            }
        } else if (!esTrio1 && esTrio2) {
            jugador.getMaActual().setCombinacio((byte) 4);
            jugador.getMaActual().setValorMesAlt((byte) valorTrio2);
            byte desempat = 0;
            for (int i = 0; i < cartes.size(); i++) {
                if (cartes.get(i).getValor() != valorTrio2 && cartes.get(i).getValor() > desempat) {
                    desempat = cartes.get(i).getValor();
                    jugador.getMaActual().setValorDesempat(desempat);
                }
            }
        }

        return esTrio1 || esTrio2;
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
            byte desempat = 0;
            for (int i = 0; i < cartes.size(); i++) {
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
            for (int i = 0; i < cartes.size(); i++) {
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
