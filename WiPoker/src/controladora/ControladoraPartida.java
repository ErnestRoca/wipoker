/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Aposta;
import domini.Baralla;
import domini.Carta;
import domini.Fase;
import domini.Jugador;
import domini.Partida;
import domini.Ronda;
import domini.Taula;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author wida45787385
 */
public class ControladoraPartida {

    private Taula taula;
    private Baralla baralla;
    private Partida partida;
    private ArrayList<Jugador> jugadors;

    public ControladoraPartida(byte maxJugadors) {
        baralla = new Baralla();
        crearBaralla();
        taula = new Taula(maxJugadors, baralla);
        partida = new Partida(Calendar.getInstance());
        taula.setPartidaActual(partida);
        jugadors = partida.getJugadors();
    }

    public void crearBaralla() {
        ArrayList<Carta> cartes = new ArrayList<Carta>();
        for (byte i = 1; i <= 13; i++) {
            cartes.add(new Carta((byte) 0, i));
        }
        for (byte i = 1; i <= 13; i++) {
            cartes.add(new Carta((byte) 1, i));
        }
        for (byte i = 1; i <= 13; i++) {
            cartes.add(new Carta((byte) 2, i));
        }
        for (byte i = 1; i <= 13; i++) {
            cartes.add(new Carta((byte) 3, i));
        }
    }

    public boolean taulaIsFull() {
        boolean completa = taula.getPlaces() - taula.getCadiresOcupades() == 0;
        return completa;
    }

    public void afegirJugador(Jugador nouJugador) {
        if (!taulaIsFull()) {
            jugadors.add(nouJugador);
            taula.setCadiresOcupades((byte) (taula.getCadiresOcupades() + 1));
        } else {
            //implementar metodo no quedanPlazas
        }
    }

    public void iniciarRonda() {
        Ronda novaRonda = new Ronda(0);
        novaRonda.setPartida(partida);
        partida.getRondes().add(novaRonda);
        barallar();
        for (int i = 0; i < 4; i++) {
            gestionarFase(novaRonda);
        }
        determinarGuanyador();
        Fase.setNumFase((byte) 0);
    }

    public void gestionarFase(Ronda ronda) {
        //Clase fase te dos static: array string nom fases i byte amb el numero de fase
        //Passem al constructor l'string de l'index de la fase
        Fase novaFase = new Fase(Fase.getNomFases()[Fase.getNumFase()]);
        ronda.getFases().add(novaFase);
        novaFase.setRonda(ronda);
        if (Fase.getNumFase() == 1) {
            repartirCartesPrivades();
            determinarCombinacioPreFlop();
            for (Jugador j : jugadors) {
                apostar(j, 0, ronda);
            }
        } else if (Fase.getNumFase() == 2) {
            cremarCartes();
            aixecarCartes((byte) 3);
            for (Jugador j : jugadors) {
                apostar(j, 0, ronda);
            }
        } else if (Fase.getNumFase() == 3) {
            cremarCartes();
            aixecarCartes((byte) 1);
            for (Jugador j : jugadors) {
                apostar(j, 0, ronda);
            }
        } else if (Fase.getNumFase() == 4) {
            cremarCartes();
            aixecarCartes((byte) 1);
            for (Jugador j : jugadors) {
                apostar(j, 0, ronda);
            }
        }

        //Al finalitzar la fase afegir potFase al pot de la ronda
    }

    public void repartirCartesPrivades() {
        for (int i = 0; i <= 1; i++) {
            for (Jugador j : jugadors) {
                j.getMaActual().getCartes().add(baralla.getCartes().get(baralla.getCartesActuals()));
                baralla.setCartesActuals((byte) (baralla.getCartesActuals() - 1));
            }
        }
    }

    public void aixecarCartes(byte numCartes) {
        for (int i = 0; i < numCartes; i++) {
            for (Jugador j : jugadors) {
                j.getMaActual().getCartes().add(baralla.getCartes().get(baralla.getCartesActuals()));
                baralla.setCartesActuals((byte) (baralla.getCartesActuals() - 1));
            }
        }
    }

    public void cremarCartes() {
        baralla.getCartes().remove(baralla.getCartes().size());
    }

    public void barallar() {
        Collections.shuffle(baralla.getCartes());
    }

    public void apostar(Jugador jugador, int quantitat, Ronda ronda) {
        //modificar cuando este hecha gui
        int fase = ronda.getFases().size();
        if (quantitat > ronda.getFases().get(fase).getApostes().get(ronda.getFases().size()).getQuantitat()) {
            jugador.setAposta(new Aposta(jugador, quantitat));
            jugador.setFitxesActuals(jugador.getFitxesActuals() - quantitat);
            ronda.setPot(ronda.getPot() + quantitat);
        }
    }

    private void determinarCombinacioPreFlop() {
        for (Jugador j : jugadors) {
            ArrayList<Carta> cartes = j.getMaActual().getCartes();
            byte combinacio = (byte) (cartes.get(0).equals(cartes.get(1)) ? 1 : 0);
            j.getMaActual().setCombinacio(combinacio);
        }
    }

    private void determinarCombinacioFlop() {
        boolean reial = true;
        boolean mateixColor = true;
        boolean escala = true;
        for (Jugador j : jugadors) {
            ArrayList<Carta> cartes = j.getMaActual().getCartes();
            if (esEscalaReial(cartes)) {
            }

        }

    }

    private boolean sonMateixColor(ArrayList<Carta> cartes) {
        boolean mateixColor = true;
        byte color = cartes.get(0).getPal();
        for (Carta c : cartes) {
            if (color != c.getPal()) {
                mateixColor = false;
                break;
            }
        }
        return mateixColor;
    }

    private boolean esEscala(ArrayList<Carta> cartes) {
        boolean esEscala = true;
        for (int i = 0; i < cartes.size() - 1; i++) {
            if (!(cartes.get(i).getValor() < cartes.get(i + 1).getValor())) {
                esEscala = false;
                break;
            }
        }
        return esEscala;
    }

    private boolean esEscalaReial(ArrayList<Carta> cartes) {
        boolean esEscala = true;
        for (int i = 10; i <= 13; i++) {
            if (!(cartes.get(i).getValor() < cartes.get(i + 1).getValor())) {
                esEscala = false;
                break;
            }
        }
        esEscala = esEscala && (cartes.get(cartes.size() - 1).getValor() == 13);
        return esEscala;
    }

    private boolean esPoker(ArrayList<Carta> cartes) {
        boolean esPoker = true;
        int condicioNoEsPoker = 0;
        int diferents = 0;
        if (cartes.size() == 5) {
            condicioNoEsPoker = 2;
        } else if (cartes.size() == 6) {
            condicioNoEsPoker = 3;
        } else if (cartes.size() == 7) {
            condicioNoEsPoker = 4;
        }
        int indexCarta = 0;
        for (Carta carta : cartes) {
            indexCarta = cartes.indexOf(carta);
            for (int i = 0; i < cartes.size(); i++) {
                if (!carta.equals(cartes.get(i)) && (i != indexCarta)) {
                    diferents++;
                    if (diferents == condicioNoEsPoker) {
                        esPoker = false;
                        break;
                    }
                }
            }
        }
        return esPoker;
    }

    private boolean esTrio(ArrayList<Carta> cartes) {
        boolean esTrio = true;
        int condicioNoEsTrio = 0;
        int diferents = 0;
        if (cartes.size() == 5) {
            condicioNoEsTrio = 3;
        } else if (cartes.size() == 6) {
            condicioNoEsTrio = 4;
        } else if (cartes.size() == 7) {
            condicioNoEsTrio = 5;
        }
        int indexCarta = 0;
        for (Carta carta : cartes) {
            indexCarta = cartes.indexOf(carta);
            for (int i = 0; i < cartes.size(); i++) {
                if (!carta.equals(cartes.get(i)) && (i != indexCarta)) {
                    diferents++;
                    if (diferents == condicioNoEsTrio) {
                        esTrio = false;
                        break;
                    }
                }
            }
        }
        return esTrio;
    }

    private boolean esDobleParella(ArrayList<Carta> cartes) {
        boolean hiHaParella = esParella(cartes);
        if (!hiHaParella) {
            return false;
        }
        int numParelles = 0;
        int indexCarta = 0;
        int valorParella = -1;
        for (Carta carta : cartes) {
            indexCarta = cartes.indexOf(carta);
            for (int i = 0; i < cartes.size(); i++) {
                if (carta.equals(cartes.get(i)) && (i != indexCarta) && carta.getValor() != valorParella) {
                    numParelles++;
                }
            }
        }

        boolean esDobleParella = numParelles >= 2;
        return esDobleParella;
    }

    private boolean esParella(ArrayList<Carta> cartes) {
        boolean esParella = true;
        int condicioNoEsParella = 0;
        int diferents = 0;
        if (cartes.size() == 5) {
            condicioNoEsParella = 4;
        } else if (cartes.size() == 6) {
            condicioNoEsParella = 5;
        } else if (cartes.size() == 7) {
            condicioNoEsParella = 6;
        }
        int indexCarta = 0;
        for (Carta carta : cartes) {
            indexCarta = cartes.indexOf(carta);
            for (int i = 0; i < cartes.size(); i++) {
                if (!carta.equals(cartes.get(i)) && (i != indexCarta)) {
                    diferents++;
                    if (diferents == condicioNoEsParella) {
                        esParella = false;
                        break;
                    }
                }
            }
        }
        return esParella;
    }

    public byte cartaMesAlta(ArrayList<Carta> cartes) {
        byte num = 0;
        for (Carta carta : cartes) {
            if (carta.getValor() > num) {
                num = carta.getValor();
            }
        }
        return num;
    }

    public Jugador desempat() {
        Jugador jugadorAux = jugadors.get(0);
        byte puntuacioMesAlta = cartaMesAlta(jugadorAux.getMaActual().getCartes());
        for (Jugador jugador : jugadors) {
            byte puntuacio = cartaMesAlta(jugador.getMaActual().getCartes());
            if (puntuacio > puntuacioMesAlta) {
                puntuacioMesAlta = puntuacio;
                jugadorAux = jugador;
            }
        }
        return jugadorAux;
    }

    private void determinarGuanyador() {
        ArrayList<Jugador> jugadorsOrdenats = jugadors;
        Comparator c = new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                Jugador j1 = (Jugador) o1;
                Jugador j2 = (Jugador) o2;
                return j1.getMaActual().getCombinacio() - j2.getMaActual().getCombinacio();
            }
        };
        Collections.sort(jugadorsOrdenats, c);
        
    }
}
