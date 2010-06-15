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

    private byte colorFinal;
/**
 *
 * @param jugador
 * @return esFull
 * Algoritme es basa en cercar la condicio d'escala color en uns limits determiants
 */
    public boolean esEscalaReial(Jugador jugador) {
        boolean esEscalaReial = false;
        boolean escalaColor = esEscalaColor(jugador);
        boolean limits = false;
        ArrayList<Carta> cartes = jugador.getMaActual().getCartes();
        int iteracions = cartes.size() - 4;
        for (int i = 0; i < iteracions; i++) {
            if ((cartes.get(i).getValor() == 14 && cartes.get(i + 4).getValor() == 10)) {
                limits = true;
            }
        }

        esEscalaReial = escalaColor && limits;
        if (esEscalaReial) {
            jugador.getMaActual().setCombinacio(10);
            jugador.getMaActual().setValorMesAlt(14);
            //jugador.getMaActual().setValorDesempat(0);
        } else if (!esEscalaReial) {
            jugador.getMaActual().setCombinacio(0);
            jugador.getMaActual().setValorMesAlt(0);
            jugador.getMaActual().setValorDesempat(0);
        }
        return esEscalaReial;
    }
/**
 *
 * @param jugador
 * @return esEscalaColor
 * Algoritme es basa en cercar la condicio d'escala i a mes que siguin del mateix color. Si es compleix
 * Es processen les duplicades i es comprova si les cartes de la mateixa escala son del mateix color
 */
    public boolean esEscalaColor(Jugador jugador) {
        boolean esEscalaColor = false;

        //conte totes les cartes del jugador
        ArrayList<Carta> cartes = new ArrayList<Carta>(jugador.getMaActual().getCartes());
        //Si hi ha escala i hi ha color

        if (sonMateixColor(jugador) && esEscala(jugador)) {
            //Ordenem cartes
            Collections.sort(cartes);
            Collections.reverse(cartes);
            //Busquem les cartes duplicades
            ArrayList<Carta> cartesDuplicades = new ArrayList<Carta>(0);
            for (int i = 0; i < cartes.size() - 1; i++) {
                //Si les dos cartes son la mateixa
                if (cartes.get(i).getValor() == cartes.get(i + 1).getValor()) {
                    //Si la primera es del color i la segona no:
                    if (cartes.get(i).getPal() == colorFinal && cartes.get(i + 1).getPal() != colorFinal) {
                        //Afageix la carta que no es del color a cartes duplicades
                        cartesDuplicades.add(cartes.get(i + 1));
                        //Si la segona es del color i la primera no:
                    } else if (cartes.get(i).getPal() != colorFinal && cartes.get(i + 1).getPal() == colorFinal) {
                        //Afageix la carta que no es del color a cartes duplicades
                        cartesDuplicades.add(cartes.get(i));
                    } else if (cartes.get(i).getPal() != colorFinal && cartes.get(i + 1).getPal() != colorFinal) {
                        cartesDuplicades.add(cartes.get(i));
                        cartesDuplicades.add(cartes.get(i + 1));
                    }
                }
            }
            //Agafa el valor de la crata dublicada mes alta
            Collections.sort(cartesDuplicades);
            Collections.reverse(cartesDuplicades);
            byte valorCartaDuplicada = 0;
            if (cartesDuplicades.size() > 0) {
                valorCartaDuplicada = cartesDuplicades.get(0).getValor();
            }

            //Elimina les cartes repetides
            cartes.removeAll(cartesDuplicades);
            //contindra la posible primer escala
            ArrayList<Carta> escala1 = new ArrayList<Carta>();
            byte numEscala1 = 0;
            boolean esEscala1 = false;
            boolean esEscalaColor1 = false;
            //contindra la posible segona escala
            ArrayList<Carta> escala2 = new ArrayList<Carta>();
            byte numEscala2 = 0;
            boolean esEscala2 = false;
            boolean esEscalaColor2 = false;
            //contindra la posible tercera escala
            ArrayList<Carta> escala3 = new ArrayList<Carta>();
            byte numEscala3 = 0;
            boolean esEscala3 = false;
            boolean esEscalaColor3 = false;
            //crea les 3 posibles escales
            for (int i = 0; i < cartes.size(); i++) {
                //si la carta esta entre la primera i la 5
                if (i <= 4 && cartes.size() >= 5) {
                    escala1.add(cartes.get(i));
                }
                //si la carta esta entre la 2 i la 6
                if (i >= 1 && i <= 5 && cartes.size() >= 6) {
                    escala2.add(cartes.get(i));
                }
                //si la carta esta entre la 3 i la 7
                if (i >= 2 && cartes.size() == 7) {
                    escala3.add(cartes.get(i));
                }
            }

            //Mira si la primera "posible" escala es una escala xD
            if (escala1.size() == 5) {
                if (escala1.get(0).getValor() + 4 == escala1.get(4).getValor()) {
                    esEscala1 = true;
                }

                //Mira si la primera escala es de color
                byte numColorEscala1 = 0;
                for (int i = 0; i < escala1.size() - 1; i++) {
                    if (escala1.get(i).getPal() == escala1.get(i + 1).getPal()) {
                        numColorEscala1++;
                    }
                }
                if (numColorEscala1 > 3) {
                    esEscalaColor1 = true;
                }
            }

            //Mira si la segona "posible" escala es una escala xD
            if (escala2.size() == 5) {

                if (escala2.get(0).getValor() - 4 == escala2.get(4).getValor()) {
                    esEscala2 = true;
                }

                //Mira si la segona escala es de color
                byte numColorEscala2 = 0;
                for (int i = 0; i < escala2.size() - 1; i++) {
                    if (escala2.get(i).getPal() == escala2.get(i + 1).getPal()) {
                        numColorEscala2++;
                    }
                }
                if (numColorEscala2 > 3) {
                    esEscalaColor2 = true;
                }
            }

            //Mira si la tercera "posible" escala es una escala xD
            if (escala3.size() == 5) {

                if (escala3.get(0).getValor() - 4 == escala3.get(4).getValor()) {
                    esEscala2 = true;
                }
                //Mira si la tercera escala es de color
                byte numColorEscala3 = 0;
                for (int i = 0; i < escala3.size() - 1; i++) {
                    if (escala3.get(i).getPal() == escala3.get(i + 1).getPal()) {
                        numColorEscala3++;
                    }
                }
                if (numColorEscala3 > 3) {
                    esEscalaColor3 = true;
                }
            }

            //Retorna el valor de la combinacio, el valor mes alt i el valor de desempat
            if (esEscalaColor1) {
                esEscalaColor = true;
                jugador.getMaActual().setCombinacio((byte) 9);
                jugador.getMaActual().setValorMesAlt(escala1.get(0).getValor());
                //busca la carta de desempat
                //Si el tamany de cartes es 5 la crta de des
                if (cartes.size() == 5) {
                    jugador.getMaActual().setValorDesempat(valorCartaDuplicada);
                } else if (cartes.size() >= 6) {
                    if (cartes.get(5).getValor() > valorCartaDuplicada) {
                        jugador.getMaActual().setValorDesempat(cartes.get(5).getValor());
                    } else {
                        jugador.getMaActual().setValorDesempat(valorCartaDuplicada);
                    }

                }
            } else if (esEscalaColor2) {
                esEscalaColor = true;
                jugador.getMaActual().setCombinacio((byte) 9);
                jugador.getMaActual().setValorMesAlt(escala2.get(0).getValor());
                //Com que es la escala2 la carta de desempat estara a la posicio 0 de larray cartes
                if (cartes.size() >= 6 && escala1.size() == 5) {
                    if (cartes.get(0).getValor() > valorCartaDuplicada) {
                        jugador.getMaActual().setValorDesempat(cartes.get(0).getValor());
                    } else {
                        jugador.getMaActual().setValorDesempat(valorCartaDuplicada);
                    }
                } else if (cartes.size() >= 6 && escala3.size() == 5) {
                    if (cartes.get(6).getValor() > valorCartaDuplicada) {
                        jugador.getMaActual().setValorDesempat(cartes.get(6).getValor());
                    } else {
                        jugador.getMaActual().setValorDesempat(valorCartaDuplicada);
                    }

                }
            } else if (esEscalaColor3) {
                esEscalaColor = true;
                jugador.getMaActual().setCombinacio((byte) 9);
                jugador.getMaActual().setValorMesAlt(escala3.get(0).getValor());
                //Com que es la escala1 la carta de desempat estara a la posicio 0 de larray cartes
                jugador.getMaActual().setValorDesempat(cartes.get(0).getValor());
                if (escala1.size() == 5) {
                    if (cartes.get(0).getValor() > valorCartaDuplicada) {
                        jugador.getMaActual().setValorDesempat(cartes.get(0).getValor());
                    } else {
                        jugador.getMaActual().setValorDesempat(valorCartaDuplicada);
                    }
                } else if (escala2.size() == 5) {
                    if (cartes.get(1).getValor() > valorCartaDuplicada) {
                        jugador.getMaActual().setValorDesempat(cartes.get(1).getValor());
                    } else {
                        jugador.getMaActual().setValorDesempat(valorCartaDuplicada);
                    }
                }
            } else {
                jugador.getMaActual().setCombinacio((byte) 0);
                jugador.getMaActual().setValorMesAlt((byte) 0);
                //Com que es la escala1 la carta de desempat estara a la posicio 0 de larray cartes
                jugador.getMaActual().setValorDesempat((byte) 0);
            }

        }
        return esEscalaColor;
    }
/**
 *
 * @param jugador
 * @return esPoker
 * Algoritme es basa en cercar 4 cartes iguals
 */
    public boolean esPoker(Jugador jugador) {
        boolean esPoker = false;
        byte valorPoker = 0;
        ArrayList<Carta> cartes = jugador.getMaActual().getCartes();
        Collections.sort(cartes);
        for (int i = 0; i < cartes.size(); i++) {
            int iguals = 0;
            for (int j = i + 1; j < cartes.size(); j++) {
                if (cartes.get(i).getValor() == cartes.get(j).getValor() && valorPoker == 0) {
                    iguals = 1;
                    valorPoker = cartes.get(i).getValor();
                } else if (cartes.get(i).getValor() == cartes.get(j).getValor() && valorPoker == cartes.get(i).getValor()) {
                    iguals++;
                }
                if (iguals >= 3) {
                    esPoker = true;
                    jugador.getMaActual().setValorMesAlt(valorPoker);
                }
            }
            if (!esPoker) {
                valorPoker = 0;
            }
        }
        if (esPoker) {
            esPoker = true;
            jugador.getMaActual().setCombinacio(8);
            byte desempat = 0;
            for (int i = 0; i < cartes.size(); i++) {
                if (cartes.get(i).getValor() != valorPoker && cartes.get(i).getValor() > desempat) {
                    desempat = cartes.get(i).getValor();
                    jugador.getMaActual().setValorDesempat(desempat);
                }
            }
        } else {
            jugador.getMaActual().setValorMesAlt(0);
        }
        return esPoker;
    }
/**
 *
 * @param jugador
 * @return esFull
 * Algoritme es basa en cercar la condicio de trio i a mes recorrer les cartes en busca d'una parella
 * que no tingui el valor del trio.
 */
    public boolean esFull(Jugador jugador) {

        ArrayList<Integer> valors = new ArrayList<Integer>();
        ArrayList<Carta> c = jugador.getMaActual().getCartes();

        esTrio(jugador);
        byte valorTrio = jugador.getMaActual().getValorMesAlt();
        byte valorParella = 0;

        for (int i = 0; i < c.size(); i++) {
            for (int j = i + 1; j < c.size(); j++) {
                if (c.get(i).getValor() == c.get(j).getValor() && c.get(i).getValor() != valorTrio) {
                    valorParella = c.get(i).getValor();
                }
            }
        }

        for (int i = 0; i < c.size(); i++) {
            int valor = (int) c.get(i).getValor();
            valors.add(valor);
        }
        int contador = 0;
        int iguals = 0;
        boolean full = false;

        for (int i = 0; i < valors.size(); i++) {
            for (Carta carta : c) {
                if (valors.get(i) == carta.getValor()) {
                    contador++;
                }
            }
            if (contador > 1) {
                iguals++;
            }
            contador = 0;
        }
        if (iguals == 5) {
            full = true;
        }

        if (full) {
            jugador.getMaActual().setCombinacio(7);
            jugador.getMaActual().setValorMesAlt(valorTrio);
            byte desempat = 0;
            for (Carta a : c) {
                if (a.getValor() != valorTrio && a.getValor() != valorParella && a.getValor() > desempat) {
                    desempat = a.getValor();
                    jugador.getMaActual().setValorDesempat(desempat);
                }
            }
        } else {
            jugador.getMaActual().setCombinacio(0);
            jugador.getMaActual().setValorMesAlt(0);
            jugador.getMaActual().setValorDesempat(0);
        }

        return full;


    }
/**
 *
 * @param jugador
 * @return sonMateixColor
 * Algoritme esBasa en cercar si les cartes son del mateix color, buscant les que siguin del mateix
 * color i les que no
 */
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
        int color = 0;
        for (int col = 0; col <= 3; col++) {
            int igualColor = 0;
            color = col;
            for (int i = 0; i < cartes.size(); i++) {
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
                jugador.getMaActual().setCombinacio(6);
                colorFinal = (byte) color;
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
/**
 *
 * @param jugador
 * @return esEscala
 * algoritme es basa en la cerca de les 3 possibles escales en una ma de maxim 7 cartes
 * Eliminant les cartes duplicades es comproven els valors de les cartes per veure si son consecutives
 * En cas d'haver-hi mes d'una escala, ens quedem amb la de valor mes alt
 */
    public boolean esEscala(Jugador jugador) {
        boolean esEscala = false;
        boolean esEscala1 = false;
        boolean esEscala2 = false;
        boolean esEscala3 = false;
        //Cartes del jugador
        ArrayList<Carta> cartes = new ArrayList<Carta>(jugador.getMaActual().getCartes());
        //Cartes duplicades que te el jugador
        ArrayList<Carta> cartesDuplicades = new ArrayList<Carta>();
        Collections.sort(cartes);
        Collections.reverse(cartes);
        //busquem les cartes duplicades
        for (int i = 0; i < cartes.size() - 1; i++) {
            if (cartes.get(i).getValor() == cartes.get(i + 1).getValor()) {
                cartesDuplicades.add(cartes.get(i));
            }
        }
        //La carta duplicada mes alta
        byte valorCartaDuplicada = 0;
        if (cartesDuplicades.size() > 0) {
            valorCartaDuplicada = cartesDuplicades.get(0).getValor();
        }

        //eliminem les cartes duplicades
        cartes.removeAll(cartesDuplicades);
        //SI enacara hi han 5 cartes
        if (cartes.size() >= 5) {
            ArrayList<Carta> escala1 = new ArrayList<Carta>();
            ArrayList<Carta> escala2 = new ArrayList<Carta>();
            ArrayList<Carta> escala3 = new ArrayList<Carta>();
            //crea les 3 posibles escales
            for (int i = 0; i < cartes.size(); i++) {
                //si la carta esta entre la primera i la 5
                if (i <= 4) {
                    escala1.add(cartes.get(i));
                }
                //si la carta esta entre la 2 i la 6
                if (i >= 1 && i <= 5 && cartes.size() >= 6) {                
                    escala2.add(cartes.get(i));
                }
                //si la carta esta entre la 3 i la 7
                if (i >= 2 && cartes.size() == 7) {
                    escala3.add(cartes.get(i));
                }
            }
            //Mira si la primera escala es escala
            if (escala1.size() == 5) {
                if (escala1.get(0).getValor() - 4 == escala1.get(4).getValor()) {
                    esEscala1 = true;
                    esEscala = true;
                }
            }
            //Mira si la segona escala es escala
            if (escala2.size() == 5) {
                if (escala2.get(0).getValor() - 4 == escala2.get(4).getValor()) {
                    esEscala2 = true;
                    esEscala = true;
                }
            }
            //Mira si la tercera escala es escala
            if (escala3.size() == 5) {
                if (escala3.get(0).getValor() - 4 == escala3.get(4).getValor()) {
                    esEscala3 = true;
                    esEscala = true;
                }
            }
            //Si la primera es escala
            if (esEscala1) {
                jugador.getMaActual().setCombinacio(5);
                jugador.getMaActual().setValorMesAlt(escala1.get(0).getValor());
                if (cartes.size() > 5) {
                    if (valorCartaDuplicada > escala2.get(0).getValor()) {
                        jugador.getMaActual().setValorDesempat(valorCartaDuplicada);
                    } else {
                        jugador.getMaActual().setValorDesempat(escala2.get(4).getValor());
                    }
                } else {
                    jugador.getMaActual().setValorDesempat(valorCartaDuplicada);
                }
            } else if (esEscala2) {
                jugador.getMaActual().setCombinacio(5);
                jugador.getMaActual().setValorMesAlt(escala2.get(0).getValor());
                if (valorCartaDuplicada > escala1.get(0).getValor()) {
                    jugador.getMaActual().setValorDesempat(valorCartaDuplicada);
                } else {
                    jugador.getMaActual().setValorDesempat(escala1.get(0).getValor());
                }
            } else if (esEscala3) {
                jugador.getMaActual().setCombinacio(5);
                jugador.getMaActual().setValorMesAlt(escala3.get(0).getValor());
                if (valorCartaDuplicada > escala1.get(0).getValor()) {
                    jugador.getMaActual().setValorDesempat(valorCartaDuplicada);
                } else {
                    jugador.getMaActual().setValorDesempat(escala1.get(0).getValor());
                }
            } else {
                jugador.getMaActual().setCombinacio(0);
                jugador.getMaActual().setValorMesAlt(0);
                
                jugador.getMaActual().setValorDesempat(0);
            }
        }
        return esEscala;
    }
/**
 *
 * @param jugador
 * @return esTrio
 * Algoritme es basa en buscar els dos possibles trios en una ma de maxim 7 cartes
 * si hi ha 2 trios ens quedem amb el de valor mes alt
 */
    public boolean esTrio(Jugador jugador) {
        ArrayList<Carta> cartes = jugador.getMaActual().getCartes();
        int numCartes1 = 1;
        int numCartes2 = 1;
        int valorTrio1 = 0;
        int valorTrio2 = 0;
        for (int i = 0; i < cartes.size(); i++) {
            for (int j = i + 1; j < cartes.size(); j++) {
                if (cartes.get(i).getValor() == cartes.get(j).getValor() && numCartes1 == 1 && numCartes2 == 1) {
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
            int max = (valorTrio1 > valorTrio2 ? valorTrio1 : valorTrio2);
            jugador.getMaActual().setCombinacio(4);
            jugador.getMaActual().setValorMesAlt(max);
            int valor = (valorTrio1 > valorTrio2 ? valorTrio1 : valorTrio2);
            byte desempat = 0;
            for (int i = 0; i < cartes.size(); i++) {
                if (cartes.get(i).getValor() != valor && cartes.get(i).getValor() > desempat) {
                    desempat = cartes.get(i).getValor();
                    jugador.getMaActual().setValorDesempat(desempat);
                }
            }
        } else if (esTrio1 && !esTrio2) {
            jugador.getMaActual().setCombinacio(4);
            jugador.getMaActual().setValorMesAlt(valorTrio1);
            byte desempat = 0;
            for (int i = 0; i < cartes.size(); i++) {
                if (cartes.get(i).getValor() != valorTrio1 && cartes.get(i).getValor() > desempat) {
                    desempat = cartes.get(i).getValor();
                    jugador.getMaActual().setValorDesempat(desempat);
                }
            }
        } else if (!esTrio1 && esTrio2) {
            jugador.getMaActual().setCombinacio(4);
            jugador.getMaActual().setValorMesAlt(valorTrio2);
            byte desempat = 0;
            for (int i = 0; i < cartes.size(); i++) {
                if (cartes.get(i).getValor() != valorTrio2 && cartes.get(i).getValor() > desempat) {
                    desempat = cartes.get(i).getValor();
                    jugador.getMaActual().setValorDesempat(desempat);
                }
            }
        }
        return (esTrio1 || esTrio2);
    }
/**
 *
 * @param jugador
 * @return si es dobleParella
 * Algoritme es basa en la busqueda de les 3 posibles parelles en una ma de maxim 7 cartes
 * guardant el valor de cadascuna de les parelles trobades. En cas d'haver-hi dues parelles iguals
 * retornara true. Si haguessin 3 parelles s'agafen les dues de valor mes alt
 */
    public boolean esDobleParella(Jugador jugador) {
        ArrayList<Carta> cartes = jugador.getMaActual().getCartes();
        int valorParella1 = -1;
        int valorParella2 = -1;
        int valorParella3 = -1;
        int numParelles = 0;
        for (int i = 0; i < cartes.size(); i++) {
            for (int j = i + 1; j < cartes.size(); j++) {
                if (cartes.get(i).getValor() == cartes.get(j).getValor()) {
                    if (valorParella1 == -1 ) {
                        valorParella1 = cartes.get(i).getValor();
                        numParelles++;

                    } else if (valorParella2 == -1 && valorParella1 != -1 && cartes.get(i).getValor() != valorParella1) {
                        valorParella2 = cartes.get(i).getValor();
                        numParelles++;

                    } else if (valorParella3 == -1 && valorParella2 != -1 && cartes.get(i).getValor() != valorParella2) {
                        valorParella3 = cartes.get(i).getValor();
                        numParelles++;
                    }
                }
            }
        }

        boolean dobleParella = numParelles >= 2 ? true : false;
        int valor1 = 0;
        int valor2 = 0;
        if (dobleParella) {
            if ((valorParella1 > valorParella2) && valorParella1 > valorParella3) {
                jugador.getMaActual().setCombinacio(3);
                jugador.getMaActual().setValorMesAlt(valorParella1);
                valor1 = valorParella1;
                valor2 = (valorParella2 > valorParella3 ? valorParella2 : valorParella3);
            } else if (valorParella2 > valorParella1 && valorParella2 > valorParella3) {
                jugador.getMaActual().setCombinacio(3);
                valor1 = valorParella2;
                jugador.getMaActual().setValorMesAlt(valorParella2);
                valor2 = (valorParella1 > valorParella3 ? valorParella1 : valorParella3);
            } else if (valorParella3 > valorParella1 && valorParella3 > valorParella2) {
                jugador.getMaActual().setCombinacio(3);
                jugador.getMaActual().setValorMesAlt(valorParella3);
                valor1 = valorParella3;
                valor2 = (valorParella1 > valorParella2 ? valorParella1 : valorParella2);
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
/**
 *
 * @param jugador
 * @return si es parella
 * Algoritme es basa en comprovar el valor d'una carta amb la seguent per mirar si tenen el mateix
 * valor. En cas que es trobi mes d'una coincidencia es comprova el valor mes alt
 */
    public boolean esParella(Jugador jugador) {
        int iguals = 0;
        ArrayList<Carta> cartes = jugador.getMaActual().getCartes();
        int valor = -1;
        for (int i = 0; i < cartes.size(); i++) {
            for (int j = i + 1; j < cartes.size(); j++) {
                if (cartes.get(i).getValor() == cartes.get(j).getValor()) {
                    valor = cartes.get(i).getValor();
                    iguals++;
                }
            }
        }
        boolean esParella = iguals >= 1;
        if (esParella) {
            jugador.getMaActual().setCombinacio(2);
            jugador.getMaActual().setValorMesAlt(valor);
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
        jugador.getMaActual().setCombinacio(1);
        jugador.getMaActual().setValorMesAlt(num);
        return true;
    }

    //PROJECTES/////////////////////////////////////////////////////////////////

    public boolean projecteColor(Jugador jugador) {
        boolean esProjecteColor = false;
        //SI tens dos cartes PRE-FLOP
        if (jugador.getMaActual().getCartes().size() == 2) {
            if (jugador.getMaActual().getCartes().get(0).getPal() == jugador.getMaActual().getCartes().get(1).getPal()) {
                esProjecteColor = true;
            }
        }
        return esProjecteColor;
    }

    public boolean projecteEscala(Jugador jugador) {
        boolean esProjecteEscala = false;
        ArrayList<Carta> cartes = new ArrayList<Carta>(jugador.getMaActual().getCartes());
        Collections.sort(cartes);
        Collections.reverse(cartes);
        if (cartes.size() == 2) {
            if (cartes.get(0).getValor() == cartes.get(1).getValor() - 1) {
                esProjecteEscala = true;
            }
        }
        return esProjecteEscala;
    }
}
