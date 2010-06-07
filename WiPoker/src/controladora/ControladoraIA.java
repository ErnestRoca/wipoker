/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import domini.Bot;
import domini.Fase;
import domini.Partida;

/**
 *
 * @author wida45787385
 */
public class ControladoraIA {

    public Partida partida = null;
    public ControladoraCartes cc;
    public ControladoraPartida cp;
    static int primeraApostaMinima = 0;

    public ControladoraIA(Partida partida, ControladoraCartes cc, ControladoraPartida cp) {
        this.partida = partida;
        this.cc = cc;
        this.cp = cp;
    }

    public void calcularAposta(Bot jugador, Fase fase, int numVegadesFase) {
        double valorCombinacio = jugador.getMaActual().getCombinacio();
        double valorMesAlt = jugador.getMaActual().getValorMesAlt();
        Fase ultimafase = fase;
        int apostaMinima = ultimafase.getApostaMinima();
        
        int ultimAposta = (int) (jugador.getAposta() != null ? jugador.getAposta().getQuantitat() : 0);
        //PRE-FLOP
        if (Fase.getNumFase() == 1) {
            //SI no es la primera vegada que aposta a la fase
            if (numVegadesFase > 1) {
                //Si el jugador te menys fitxes que l'aposta actual
                if (apostaMinima - ultimAposta > jugador.getFitxesActuals()) {
                    cp.controlJoc.ferRaise(jugador, fase, apostaMinima, jugador.getFitxesActuals());
                } else {
                    if (valorCombinacio == 2) {
                        //Si te parella fer CALL
                        cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                    } else if (cc.projecteColor(jugador) && cc.projecteEscala(jugador)) {
                        //Si te projecte color i projecte escala fes call
                        cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                    } else if (cc.projecteColor(jugador)) {
                        //Si te projecte color fes CALL
                        cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                    } else if (cc.projecteEscala(jugador)) {
                        //Si laposta minima es menor que tres vegades l'aposta anterior
                        if (apostaMinima < ultimAposta * 3) {
                            //fes CALL
                            cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                        } else {
                            //fes FOLD
                            cp.controlJoc.ferFold(jugador, fase);
                        }
                    } else if (valorCombinacio == 1) {
                        //Si te carta alta major que 10
                        if (valorMesAlt > 10) {
                            //Si laposta minima es menor que tres vegades l'aposta anterior
                            if (apostaMinima < ultimAposta * 3) {
                                //fes CALL
                                cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                            }
                        } else {
                            //fes FOLD
                            cp.controlJoc.ferFold(jugador, fase);
                        }
                    }
                }
            } else { //SI es la primera vegada que aposta a la fase
                if (primeraApostaMinima == 0) {
                    primeraApostaMinima = apostaMinima;
                }
                if (apostaMinima - ultimAposta > jugador.getFitxesActuals()) {
                    cp.controlJoc.ferRaise(jugador, fase, apostaMinima, jugador.getFitxesActuals());
                } else {
                    if (valorCombinacio == 2) {
                        if (valorMesAlt > 10) {
                            if (jugador.getFitxesActuals() > apostaMinima * 6) {
                                cp.controlJoc.ferRaise(jugador, fase, apostaMinima, apostaMinima * 5);
                            } else if (jugador.getFitxesActuals() > apostaMinima * 5) {
                                cp.controlJoc.ferRaise(jugador, fase, apostaMinima, apostaMinima * 4);
                            } else if (jugador.getFitxesActuals() > apostaMinima * 4) {
                                cp.controlJoc.ferRaise(jugador, fase, apostaMinima, apostaMinima * 3);
                            } else if (jugador.getFitxesActuals() > apostaMinima * 2) {
                                cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                            } else if (jugador.getFitxesActuals() == apostaMinima) {
                                cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                            }
                        } else {
                            if (jugador.getFitxesActuals() > apostaMinima * 4) {
                                cp.controlJoc.ferRaise(jugador, fase, apostaMinima, apostaMinima * 3);
                            } else if (jugador.getFitxesActuals() > apostaMinima * 2) {
                                cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                            } else if (jugador.getFitxesActuals() == apostaMinima) {
                                cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                            }
                        }
                    } else if (cc.projecteColor(jugador) && cc.projecteEscala(jugador)) {
                        if (jugador.getFitxesActuals() > apostaMinima * 3) {
                            cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                        } else if (jugador.getFitxesActuals() == apostaMinima) {
                            cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                        }
                    } else if (cc.projecteColor(jugador)) {
                        if (jugador.getFitxesActuals() > apostaMinima * 4) {
                            cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                        } else if (jugador.getFitxesActuals() == apostaMinima) {
                            cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                        }
                    } else if (cc.projecteEscala(jugador)) {
                        if (jugador.getFitxesActuals() > apostaMinima * 5) {
                            cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                        } else if (jugador.getFitxesActuals() == apostaMinima) {
                            cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                        }
                    } else if (valorCombinacio == 1) {
                        if (valorMesAlt > 10) {
                            if (jugador.getFitxesActuals() > apostaMinima * 4) {
                                cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                            } else if (jugador.getFitxesActuals() == apostaMinima) {
                                cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                            }
                        } else {
                            if (jugador.getFitxesActuals() == apostaMinima) {
                                cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                            } else {
                                cp.controlJoc.ferFold(jugador, fase);
                            }
                        }
                    }
                }
            }
        } else if (Fase.getNumFase() > 1) {
            if (numVegadesFase > 1) {
                //Si el jugador te menys fitxes que l'aposta actual
                if (apostaMinima - ultimAposta > jugador.getFitxesActuals()) {
                    cp.controlJoc.ferRaise(jugador, fase, apostaMinima, jugador.getFitxesActuals());
                } else {
                    if (valorCombinacio > 2) {
                        cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                    } else if (valorCombinacio == 2) {
                        if (valorMesAlt > 10) {
                            cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                        } else {
                            if (jugador.getFitxesActuals() > apostaMinima * 2) {
                                cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                            } else {
                                cp.controlJoc.ferFold(jugador, fase);
                            }
                        }
                    } else {
                        cp.controlJoc.ferFold(jugador, fase);
                    }
                }
            } else { //SI es la primera vegada que aposta a la fase
                if (apostaMinima - ultimAposta > jugador.getFitxesActuals()) {
                    cp.controlJoc.ferRaise(jugador, fase, apostaMinima, jugador.getFitxesActuals());
                } else {
                    if (apostaMinima == 0) {
                        if (valorCombinacio > 2) {
                            cp.controlJoc.ferRaise(jugador, fase, apostaMinima, jugador.getFitxesActuals());
                        } else if (valorCombinacio == 2) {
                            if (valorMesAlt > 10) {
                                if (jugador.getFitxesActuals() > primeraApostaMinima * 4) {
                                    cp.controlJoc.ferRaise(jugador, fase, apostaMinima, primeraApostaMinima * 3);
                                } else if (jugador.getFitxesActuals() > primeraApostaMinima * 2) {
                                    cp.controlJoc.ferRaise(jugador, fase, apostaMinima, primeraApostaMinima);
                                } else {
                                    cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                                }
                            } else {
                                if (jugador.getFitxesActuals() > primeraApostaMinima * 2) {
                                    cp.controlJoc.ferRaise(jugador, fase, apostaMinima, primeraApostaMinima);
                                } else {
                                    cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                                }
                            }
                        } else if (valorCombinacio == 1) {
                            cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                        }
                    } else {
                        if (valorCombinacio > 2) {
                            if (jugador.getFitxesActuals() > apostaMinima * 6) {
                                cp.controlJoc.ferRaise(jugador, fase, apostaMinima, apostaMinima * 5);
                            } else if (jugador.getFitxesActuals() > apostaMinima * 5) {
                                cp.controlJoc.ferRaise(jugador, fase, apostaMinima, apostaMinima * 4);
                            } else if (jugador.getFitxesActuals() > apostaMinima * 4) {
                                cp.controlJoc.ferRaise(jugador, fase, apostaMinima, apostaMinima * 3);
                            } else if (jugador.getFitxesActuals() > apostaMinima * 2) {
                                cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                            } else if (jugador.getFitxesActuals() == apostaMinima) {
                                cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                            }
                        } else if (valorCombinacio == 2) {
                            if (valorMesAlt > 10) {
                                if (jugador.getFitxesActuals() > apostaMinima * 4) {
                                    cp.controlJoc.ferRaise(jugador, fase, apostaMinima, apostaMinima * 3);
                                } else if (jugador.getFitxesActuals() > apostaMinima * 2) {
                                    cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                                } else if (jugador.getFitxesActuals() == apostaMinima) {
                                    cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                                }
                            } else {
                                if (jugador.getFitxesActuals() > apostaMinima * 4) {
                                    cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                                } else if (jugador.getFitxesActuals() == apostaMinima) {
                                    cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                                }
                            }
                        } else if (valorCombinacio == 1) {
                            if (jugador.getFitxesActuals() == apostaMinima) {
                                cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                            } else {
                                cp.controlJoc.ferFold(jugador, fase);
                            }
                        }
                    }
                }
            }
            if (Fase.getNumFase() == 4 && primeraApostaMinima != 0) {
                primeraApostaMinima = 0;
            }
        }
    }
}
