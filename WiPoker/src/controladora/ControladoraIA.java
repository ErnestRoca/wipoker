/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Bot;
import domini.Fase;
import domini.Partida;
import domini.Ronda;

/**
 *
 * @author wida45787385
 */
public class ControladoraIA {

    public Partida partida = null;
    public ControladoraCartes cc;
    public ControladoraPartida cp;

    public ControladoraIA(Partida partida, ControladoraCartes cc, ControladoraPartida cp) {
        this.partida = partida;
        this.cc = cc;
    }

    public void calcularAposta(Bot jugador, Fase fase) {
        double aposta = 0.0;
        double valorMa = jugador.getMaActual().getCombinacio();
        double valorCarta = jugador.getMaActual().getValorMesAlt();
        //Ronda ultimaRonda = cp.partida.getRondes().get(cp.partida.getRondes().size() -1);
        Fase ultimafase = fase;
        double apostaMinima = ultimafase.getApostaMinima();
        System.out.println("hola");
        //PRE-FLOP
        if (Fase.getNumFase() == 1) {
            //Pre-flop--->Parella
            if (valorMa == 2) {
                if (valorCarta >= 10) {
                    //fer RISE totes les fitxes actuals
                    cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, jugador.getFitxesActuals());
                } else if (valorCarta >= 7) {
                    //fer RISE 3 o 4 vegades l'aposta minima
                    if (jugador.getFitxesActuals() >= apostaMinima * 4) {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, (int) apostaMinima * 4);
                    } else if (jugador.getFitxesActuals() == apostaMinima * 3) {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, (int) apostaMinima * 3);
                    } else if (jugador.getFitxesActuals() == apostaMinima * 2) {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, (int) apostaMinima * 2);
                    } else if (jugador.getFitxesActuals() > apostaMinima) {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, jugador.getFitxesActuals());
                    }
                } else {
                    //fer RISE doblar l'aposta minima
                    if (jugador.getFitxesActuals() >= apostaMinima * 2) {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, (int) apostaMinima * 2);
                    } else {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, jugador.getFitxesActuals());
                    }
                }
                //Pre-flop--->Projecte escala color
            } else if (cc.projecteColor(jugador) && cc.projecteEscala(jugador)) {
                //fer RISE doblar o triplicar l'aposta minima
                if (jugador.getFitxesActuals() == apostaMinima * 3) {
                    cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, (int) apostaMinima * 3);
                } else if (jugador.getFitxesActuals() == apostaMinima * 2) {
                    cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, (int) apostaMinima * 2);
                } else if (jugador.getFitxesActuals() > apostaMinima) {
                    cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, jugador.getFitxesActuals());
                }
                //Pre-flop-->Projecte color
            } else if (cc.projecteColor(jugador)) {
                //fer RISE doblar l'aposta minima
                if (jugador.getFitxesActuals() == apostaMinima * 2) {
                    cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, (int) apostaMinima * 2);
                } else if (jugador.getFitxesActuals() > apostaMinima) {
                    cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, jugador.getFitxesActuals());
                }
                //Pre-flop--->Projecte escala
            } else if (cc.projecteEscala(jugador)) {
                //fer RISE foblar l'aposta minima
                if (jugador.getFitxesActuals() == apostaMinima * 2) {
                    cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, (int) apostaMinima * 2);
                } else if (jugador.getFitxesActuals() > apostaMinima) {
                    cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, jugador.getFitxesActuals());
                }
                //Pre-flop--->Carta alta
            } else {
                if (valorCarta > 10) {
                    //fer RISE doblar l'aposta minima
                    if (jugador.getFitxesActuals() == apostaMinima * 2) {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, (int) apostaMinima * 2);
                    } else if (jugador.getFitxesActuals() > apostaMinima) {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, jugador.getFitxesActuals());
                    }
                } else if (valorCarta > 7) {
                    //fer CALL
                    if (jugador.getFitxesActuals() == apostaMinima - jugador.getAposta().getQuantitat()) {
                        cp.controlJoc.ferCall(jugador, ultimafase, (int) apostaMinima);
                    } else {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, jugador.getFitxesActuals());
                    }

                } else {
                    //fer FOLD
                    cp.controlJoc.ferFold(jugador);
                }
            }
            //FLOP
        } else if (Fase.getNumFase() == 2) {
            //Flop-->Combinacio mes alta que parella
            if (valorMa >= 3) {
                //fer RISE totes les fitxes actuals
                cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, jugador.getFitxesActuals());
                //Flop-->Parella
            } else if (valorMa == 2) {
                if (valorCarta > 10) {
                    //fer RISE doblar aposta minima
                    if (jugador.getFitxesActuals() == apostaMinima * 2) {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, (int) apostaMinima * 2);
                    } else if (jugador.getFitxesActuals() > apostaMinima) {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, jugador.getFitxesActuals());
                    }
                } else {
                    //fer CALL
                    if (jugador.getFitxesActuals() == apostaMinima - jugador.getAposta().getQuantitat()) {
                        cp.controlJoc.ferCall(jugador, ultimafase, (int) apostaMinima);
                    } else {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, jugador.getFitxesActuals());
                    }
                }
                //Flop-->Carta alta
            } else {
                //fer FOLD
                cp.controlJoc.ferFold(jugador);
            }
            //TURN
        } else if (Fase.getNumFase() == 3) {
            //TURN-->Combinacio mes alta que doble parella
            if (valorMa >= 4) {
                //fer RISE totes les fitxes actuals
                cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, jugador.getFitxesActuals());
                //TURN-->Doble Parella
            } else if (valorMa == 3) {
                if (valorCarta > 10) {
                    //fer RISE triplicar aposta minima
                    if (jugador.getFitxesActuals() == apostaMinima * 3) {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, (int) apostaMinima * 3);
                    } else if (jugador.getFitxesActuals() == apostaMinima * 2) {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, (int) apostaMinima * 2);
                    } else if (jugador.getFitxesActuals() > apostaMinima) {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, jugador.getFitxesActuals());
                    }
                } else {
                    //fer RISE doblar aposta minima
                    if (jugador.getFitxesActuals() == apostaMinima * 2) {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, (int) apostaMinima * 2);
                    } else if (jugador.getFitxesActuals() > apostaMinima) {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, jugador.getFitxesActuals());
                    }
                }
                //TURN--->Parella
            } else if (valorMa == 2) {
                if (valorCarta >= 11) {
                    //fer CALL
                    if (jugador.getFitxesActuals() == apostaMinima - jugador.getAposta().getQuantitat()) {
                        cp.controlJoc.ferCall(jugador, ultimafase, (int) apostaMinima);
                    } else {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, jugador.getFitxesActuals());
                    }
                } else {
                    //fer FOLD
                    cp.controlJoc.ferFold(jugador);
                }
                //TURN-->Carta alta
            } else {
                //fer FOLD
                cp.controlJoc.ferFold(jugador);
            }
            //RIVER
        } else if (Fase.getNumFase() == 4) {
            //RIVER-->Combinacio mes alta que doble parella
            if (valorMa >= 4) {
                //fer RISE totes les fitxes actuals
                cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, jugador.getFitxesActuals());
                //RIVER-->Doble Parella
            } else if (valorMa == 3) {
                if (valorCarta >= 11) {
                    //fer RISE triplicar aposta minima
                    if (jugador.getFitxesActuals() == apostaMinima * 3) {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, (int) apostaMinima * 3);
                    } else if (jugador.getFitxesActuals() == apostaMinima * 2) {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, (int) apostaMinima * 2);
                    } else if (jugador.getFitxesActuals() > apostaMinima) {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, jugador.getFitxesActuals());
                    }
                } else {
                    //fer CALL
                    if (jugador.getFitxesActuals() == apostaMinima - jugador.getAposta().getQuantitat()) {
                        cp.controlJoc.ferCall(jugador, ultimafase, (int) apostaMinima);
                    } else {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, jugador.getFitxesActuals());
                    }
                }
                //RIVER--->Parella
            } else if (valorMa == 2) {
                if (valorCarta >= 12) {
                    //fer CALL
                    if (jugador.getFitxesActuals() == apostaMinima - jugador.getAposta().getQuantitat()) {
                        cp.controlJoc.ferCall(jugador, ultimafase, (int) apostaMinima);
                    } else {
                        cp.controlJoc.ferRaise(jugador, ultimafase, (int) apostaMinima, jugador.getFitxesActuals());
                    }
                } else {
                    //fer FOLD
                    cp.controlJoc.ferFold(jugador);
                }
                //RIVER-->Carta alta
            } else {
                //fer FOLD
                cp.controlJoc.ferFold(jugador);
            }
        }
    }
}
