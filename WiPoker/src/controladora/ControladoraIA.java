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

    public ControladoraIA(Partida partida, ControladoraCartes cc, ControladoraPartida cp) {
        this.partida = partida;
        this.cc = cc;
        this.cp = cp;
    }

    public void calcularAposta(Bot jugador, Fase fase, int numVegadesFase) {
        double aposta = 0.0;
        double valorCombinacio = jugador.getMaActual().getCombinacio();
        double valorMesAlt = jugador.getMaActual().getValorMesAlt();
        //Ronda ultimaRonda = cp.partida.getRondes().get(cp.partida.getRondes().size() -1);
        Fase ultimafase = fase;
        double apostaMinima = ultimafase.getApostaMinima();
        //PRE-FLOP
        if (numVegadesFase > 0) {
            if (Fase.getNumFase() == 1) {
                if (valorCombinacio == 2) {
                    cp.controlJoc.ferCall(jugador, fase, (int) apostaMinima);
                } else if (cc.projecteColor(jugador) && cc.projecteEscala(jugador)) {
                    cp.controlJoc.ferCall(jugador, fase, (int) apostaMinima);
                } else if (cc.projecteColor(jugador)) {
                    if (jugador.getFitxesActuals() > apostaMinima * 3) {
                        cp.controlJoc.ferCall(jugador, fase, (int) apostaMinima);
                    } else {
                        cp.controlJoc.ferFold(jugador, fase);
                    }
                } else if (cc.projecteEscala(jugador)) {
                    if (jugador.getFitxesActuals() > apostaMinima * 4) {
                        cp.controlJoc.ferCall(jugador, fase, (int) apostaMinima);
                    } else {
                        cp.controlJoc.ferFold(jugador, fase);
                    }
                } else if (valorCombinacio == 1) {
                    if (valorMesAlt > 9) {
                        if (jugador.getFitxesActuals() > apostaMinima * 4) {
                            cp.controlJoc.ferCall(jugador, fase, (int) apostaMinima);
                        } else {
                            cp.controlJoc.ferFold(jugador, fase);
                        }
                    } else {
                        cp.controlJoc.ferFold(jugador, fase);
                    }
                }
            } else if (Fase.getNumFase() > 1 ) {
                if (valorCombinacio >= 3) {
                    cp.controlJoc.ferCall(jugador, fase, (int) apostaMinima);
                } else if (valorCombinacio == 2) {
                    if (valorMesAlt > 4) {
                        cp.controlJoc.ferCall(jugador, fase, (int) apostaMinima);
                    } else {
                        cp.controlJoc.ferFold(jugador, fase);
                    }
                } else if (valorCombinacio == 1) {
                    cp.controlJoc.ferFold(jugador, fase);
                }
            }
        } else {
            if (Fase.getNumFase() == 1) {
                if (valorCombinacio == 2) {
                    if (valorMesAlt >= 9) {
                        if (jugador.getFitxesActuals() >= apostaMinima * 4) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) (apostaMinima * 4));
                        } else if (jugador.getFitxesActuals() >= apostaMinima * 3) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) (apostaMinima * 3));
                        } else if (jugador.getFitxesActuals() >= apostaMinima * 2) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) (apostaMinima * 2));
                        } else if (jugador.getFitxesActuals() >= apostaMinima) {
                            cp.controlJoc.ferCall(jugador, fase, (int) apostaMinima);
                        }
                    } else {
                        if (jugador.getFitxesActuals() >= apostaMinima * 2) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) (apostaMinima * 2));
                        } else if (jugador.getFitxesActuals() >= apostaMinima) {
                            cp.controlJoc.ferCall(jugador, fase, (int) apostaMinima);
                        }
                    }
                } else if (cc.projecteColor(jugador) && cc.projecteEscala(jugador)) {
                    if (jugador.getFitxesActuals() >= apostaMinima * 3) {
                        cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) (apostaMinima * 3));
                    } else if (jugador.getFitxesActuals() >= apostaMinima * 2) {
                        cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) (apostaMinima * 2));
                    } else if (jugador.getFitxesActuals() >= apostaMinima) {
                        cp.controlJoc.ferCall(jugador, fase, (int) apostaMinima);
                    }
                } else if (cc.projecteColor(jugador)) {
                    if (jugador.getFitxesActuals() >= apostaMinima * 2) {
                        cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) (apostaMinima * 2));
                    } else if (jugador.getFitxesActuals() >= apostaMinima) {
                        cp.controlJoc.ferCall(jugador, fase, (int) apostaMinima);
                    }
                } else if (cc.projecteEscala(jugador)) {
                    if (jugador.getFitxesActuals() >= apostaMinima) {
                        cp.controlJoc.ferCall(jugador, fase, (int) apostaMinima);
                    }
                } else {
                    if (apostaMinima < jugador.getFitxesActuals() / 5) {
                        if (valorMesAlt >= 10) {
                            cp.controlJoc.ferCall(jugador, fase, (int) apostaMinima);
                        } else {
                            cp.controlJoc.ferFold(jugador, fase);
                        }
                    } else {
                        cp.controlJoc.ferFold(jugador, fase);
                    }
                }
                //FLOP
            } else if (Fase.getNumFase() > 1) {
                if (valorCombinacio >= 4) {
                    if (apostaMinima > 0) {
                        if (jugador.getFitxesActuals() >= apostaMinima * 5) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) (apostaMinima * 5));
                        } else if (jugador.getFitxesActuals() >= apostaMinima * 4) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) (apostaMinima * 4));
                        } else if (jugador.getFitxesActuals() >= apostaMinima * 3) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) (apostaMinima * 3));
                        } else if (jugador.getFitxesActuals() >= apostaMinima * 2) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) (apostaMinima * 2));
                        } else if (jugador.getFitxesActuals() >= apostaMinima) {
                            cp.controlJoc.ferCall(jugador, fase, (int) apostaMinima);
                        }
                    } else {
                        cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, jugador.getFitxesActuals());
                    }
                } else if (valorCombinacio == 3) {
                    if (apostaMinima > 0) {
                        if (jugador.getFitxesActuals() >= apostaMinima * 3) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) (apostaMinima * 3));
                        } else if (jugador.getFitxesActuals() >= apostaMinima * 2) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) (apostaMinima * 2));
                        } else if (jugador.getFitxesActuals() >= apostaMinima) {
                            cp.controlJoc.ferCall(jugador, fase, (int) apostaMinima);
                        }
                    } else {
                        cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, jugador.getFitxesActuals() / 2);
                    }

                } else if (valorCombinacio == 2) {
                    //Si algu ha apostat
                    if (apostaMinima > 0) {
                        //SI tens una parella per sobre del 9
                        if (valorMesAlt > 9) {
                            if (jugador.getFitxesActuals() >= apostaMinima * 3) {
                                cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) apostaMinima);
                            } else {
                                cp.controlJoc.ferFold(jugador, fase);
                            }
                        } else {
                            if (jugador.getFitxesActuals() >= apostaMinima * 5) {
                                cp.controlJoc.ferCall(jugador, fase, (int) apostaMinima);
                            } else {
                                cp.controlJoc.ferFold(jugador, fase);
                            }
                        }
                    } else {
                        if (valorMesAlt > 9) {
                            if (jugador.getFitxesActuals() >= apostaMinima) {
                                cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, jugador.getFitxesActuals() / 5);
                            }
                        } else {
                            cp.controlJoc.ferCheck(jugador, fase, (int) apostaMinima);
                        }
                    }
                } else {
                    if (apostaMinima > 0) {
                        cp.controlJoc.ferFold(jugador, fase);
                    } else {
                        cp.controlJoc.ferCheck(jugador, fase, (int) apostaMinima);
                    }
                }
            }
        }
    }
}
