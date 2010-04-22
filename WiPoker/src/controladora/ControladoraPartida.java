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
        for (byte i = 1; i <=13; i++) {
            cartes.add(new Carta((byte) 0,i));
        }
        for (byte i = 1; i <=13; i++) {
            cartes.add(new Carta((byte) 1,i));
        }
        for (byte i = 1; i <=13; i++) {
            cartes.add(new Carta((byte) 2,i));
        }
        for (byte i = 1; i <=13; i++) {
            cartes.add(new Carta((byte) 3,i));
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
    }

    public void gestionarFase(Ronda ronda) {
        //Clase fase te dos static: array string nom fases i byte amb el numero de fase
        //Passem al constructor l'string de l'index de la fase
        Fase novaFase = new Fase(Fase.getNomFases()[Fase.getNumFase()]);
        ronda.getFases().add(novaFase);
        novaFase.setRonda(ronda);
        if (Fase.getNumFase() == 1) {
            repartirCartesPrivades();
            determinarCombinacio();
            apostar(null, 0, ronda);
        } else if (Fase.getNumFase() == 2) {
            cremarCartes();
            aixecarCartes((byte) 3);
            apostar(null, 0, ronda);
        } else if (Fase.getNumFase() == 3) {
            cremarCartes();
            aixecarCartes((byte) 1);
            apostar(null, 0, ronda);
        } else if (Fase.getNumFase() == 4) {
            cremarCartes();
            aixecarCartes((byte) 1);
            apostar(null, 0, ronda);
            Fase.setNumFase((byte) 0);
        }

        

        //Al finalitzar la fase afegir potFase al pot de la ronda
    }

    public void repartirCartesPrivades() {
        for (Jugador j: jugadors) {
            j.getMaActual().getCartes().add(baralla.getCartes().get(baralla.getCartesActuals()));
            baralla.setCartesActuals((byte)(baralla.getCartesActuals() - 1));
            j.getMaActual().getCartes().add(baralla.getCartes().get(baralla.getCartesActuals()));
            baralla.setCartesActuals((byte)(baralla.getCartesActuals() - 1));
        }        
    }

    public void aixecarCartes(byte numCartes) {
        for (int i = 0; i < numCartes; i++) {
            for (Jugador j: jugadors) {
                j.getMaActual().getCartes().add(baralla.getCartes().get(baralla.getCartesActuals()));
                baralla.setCartesActuals((byte)(baralla.getCartesActuals() - 1));
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
        jugador.setAposta(new Aposta(quantitat));
        jugador.setFitxesActuals(jugador.getFitxesActuals() - quantitat);
        ronda.setPot(ronda.getPot() + quantitat);
    }

    private void determinarCombinacio() {
        for (Jugador j: jugadors) {
            algoritmeMa(j);
        }
    }

    public void algoritmeMa(Jugador jugador) {
        int numCartes = jugador.getMaActual().getCartes().size();
        ArrayList<Carta> cartes = jugador.getMaActual().getCartes();
        if ( numCartes == 2) {
            byte combinacio = (byte) (cartes.get(0).equals(cartes.get(1)) ? 1 : 0);
            jugador.getMaActual().setCombinacio(combinacio);
        } else if (numCartes == 5) {
            boolean reial = true;
            boolean mateixColor = true;
            boolean escala = true;
            byte color = cartes.get(0).getPal();
            for (Carta c: cartes) {
                if (color != c.getPal()) {
                    mateixColor = false;
                    break;
                }
            }            
            for (int i = 0; i < cartes.size(); i++) {
                if (!(cartes.get(i).getValor() < cartes.get(i+1).getValor())) {
                    escala = false;
                    break;
                }
            }
            for (int i = 10; i < 13; i++) {
                if (!(cartes.get(i).getValor() < cartes.get(i+1).getValor())) {
                    reial = false;
                    break;
                }
            }
            if (escala && mateixColor && reial) {
                jugador.getMaActual().setCombinacio((byte)10);
            } else if (escala && mateixColor) {
                jugador.getMaActual().setCombinacio((byte)9);
            }

        }

    }

    public void ordenar() {

    }
}
