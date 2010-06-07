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
        double valorCombinacio = jugador.getMaActual().getCombinacio();
        double valorMesAlt = jugador.getMaActual().getValorMesAlt();
        Fase ultimafase = fase;
        int apostaMinima = ultimafase.getApostaMinima();
        int primeraApostaMinima = 0;
        //PRE-FLOP
        if (Fase.getNumFase() == 1) {
            if (primeraApostaMinima == 0) {
                primeraApostaMinima = apostaMinima;
            }
            //Pre-Flop-->Parella
            if (valorCombinacio == 2) {
                if (valorMesAlt > 2) {
                    if (jugador.getFitxesActuals() > apostaMinima * 5) {
                        cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, apostaMinima * 4);
                    } else if (jugador.getFitxesActuals() > apostaMinima * 4) {
                        cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, apostaMinima * 3);
                    } else if (jugador.getFitxesActuals() > apostaMinima * 3) {
                        cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, apostaMinima * 2);
                    } else if (jugador.getAposta() != null && jugador.getAposta().getQuantitat() == apostaMinima) {
                        cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                    } else if (jugador.getFitxesActuals() >= apostaMinima) {
                        cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                    } else if (jugador.getFitxesActuals() < apostaMinima) {
                        cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) jugador.getFitxesActuals());
                    }
                } else {
                    if (jugador.getFitxesActuals() > apostaMinima * 3) {
                        cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, apostaMinima * 2);
                    } else if (jugador.getAposta() != null && jugador.getAposta().getQuantitat() == apostaMinima) {
                        cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                    } else if (jugador.getFitxesActuals() >= apostaMinima) {
                        cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                    } else if (jugador.getFitxesActuals() < apostaMinima) {
                        cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) jugador.getFitxesActuals());
                    }
                }
                //Pre-Flop-->Projecte Color i Projecte escala
            } else if (cc.projecteColor(jugador) && cc.projecteEscala(jugador)) {
                if (jugador.getFitxesActuals() > apostaMinima * 3) {
                    cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, apostaMinima * 2);
                } else if (jugador.getAposta() != null && jugador.getAposta().getQuantitat() == apostaMinima) {
                    cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                } else if (jugador.getFitxesActuals() >= apostaMinima) {
                    cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                } else if (jugador.getFitxesActuals() < apostaMinima) {
                    cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) jugador.getFitxesActuals());
                }
                //Pre-Flop-->Projecte color o projecte escala
            } else if (cc.projecteColor(jugador) || cc.projecteEscala(jugador)) {
                if (jugador.getAposta() != null && jugador.getAposta().getQuantitat() == apostaMinima) {
                    cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                } else if (jugador.getFitxesActuals() >= apostaMinima * 3) {
                    cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                } else if (jugador.getFitxesActuals() < apostaMinima) {
                    cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) jugador.getFitxesActuals());
                }
                //Pre-Flop-->Carta alta
            } else if (valorCombinacio == 1) {
                if (valorMesAlt > 10) {
                    if (jugador.getFitxesActuals() > apostaMinima * 3) {
                        cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, apostaMinima * 2);
                    } else if (jugador.getAposta() != null && jugador.getAposta().getQuantitat() == apostaMinima) {
                        cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                    } else if (jugador.getFitxesActuals() >= apostaMinima) {
                        cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                    } else if (jugador.getFitxesActuals() < apostaMinima) {
                        cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) jugador.getFitxesActuals());
                    }
                } else {
                    if (jugador.getAposta() != null && jugador.getAposta().getQuantitat() == apostaMinima) {
                        cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                    } else {
                        cp.controlJoc.ferFold(jugador, fase);
                    }
                }
            }
        } else if (Fase.getNumFase() > 1) {
            if (apostaMinima != 0) {
                if (valorCombinacio > 3) {
                    if (jugador.getFitxesActuals() > apostaMinima * 5) {
                        cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, apostaMinima * 4);
                    } else if (jugador.getFitxesActuals() > apostaMinima * 4) {
                        cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, apostaMinima * 3);
                    } else if (jugador.getFitxesActuals() > apostaMinima * 3) {
                        cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, apostaMinima * 2);
                    } else if (jugador.getAposta() != null && jugador.getAposta().getQuantitat() == apostaMinima) {
                        cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                    } else if (jugador.getFitxesActuals() >= apostaMinima) {
                        cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                    } else if (jugador.getFitxesActuals() < apostaMinima) {
                        cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) jugador.getFitxesActuals());
                    }
                } else if (valorCombinacio == 3) {
                    int num = (int) (Math.random() * 11);
                    if (num >= 5) {
                        if (jugador.getFitxesActuals() > apostaMinima * 3) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, apostaMinima * 2);
                        } else if (jugador.getAposta() != null && jugador.getAposta().getQuantitat() == apostaMinima) {
                            cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                        } else if (jugador.getFitxesActuals() >= apostaMinima) {
                            cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                        } else if (jugador.getFitxesActuals() < apostaMinima) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) jugador.getFitxesActuals());
                        }
                    } else {
                        if (jugador.getAposta() != null && jugador.getAposta().getQuantitat() == apostaMinima) {
                            cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                        } else if (jugador.getFitxesActuals() >= apostaMinima) {
                            cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                        } else if (jugador.getFitxesActuals() < apostaMinima) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) jugador.getFitxesActuals());
                        }
                    }
                } else if (valorCombinacio == 2) {
                    if (valorMesAlt >= 5) {
                        if (jugador.getAposta() != null && jugador.getAposta().getQuantitat() == apostaMinima) {
                            cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                        } else if (jugador.getFitxesActuals() >= apostaMinima) {
                            cp.controlJoc.ferCall(jugador, fase, apostaMinima);
                        } else if (jugador.getFitxesActuals() < apostaMinima) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) jugador.getFitxesActuals());
                        }
                    } else {
                        if (jugador.getAposta() != null && jugador.getAposta().getQuantitat() == apostaMinima) {
                            cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                        } else {
                            cp.controlJoc.ferFold(jugador, fase);
                        }
                    }
                } else {
                    if (jugador.getAposta() != null && jugador.getAposta().getQuantitat() == apostaMinima) {
                        cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                    } else {
                        cp.controlJoc.ferFold(jugador, fase);
                    }
                }
            } else {
                if (valorCombinacio > 3) {
                    int num = (int) (Math.random() * 11);
                    if (num > 5) {
                        cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) jugador.getFitxesActuals());
                    } else {
                        if (jugador.getFitxesActuals() > primeraApostaMinima * 6) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, primeraApostaMinima * 5);
                        } else if (jugador.getFitxesActuals() > primeraApostaMinima * 5) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, primeraApostaMinima * 4);
                        } else if (jugador.getFitxesActuals() > primeraApostaMinima * 4) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, primeraApostaMinima * 3);
                        } else if (jugador.getFitxesActuals() > primeraApostaMinima * 3) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, primeraApostaMinima * 2);
                        } else if (jugador.getFitxesActuals() > primeraApostaMinima * 2) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, primeraApostaMinima);
                        }
                    }


                } else if (valorCombinacio == 3) {
                    int num = (int) (Math.random() * 11);
                    if (num > 5) {
                        cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) jugador.getFitxesActuals() / 2);
                    } else {
                        if (jugador.getFitxesActuals() > primeraApostaMinima * 4) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, primeraApostaMinima * 3);
                        } else if (jugador.getFitxesActuals() > primeraApostaMinima * 3) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, primeraApostaMinima * 2);
                        } else if (jugador.getFitxesActuals() > primeraApostaMinima * 2) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, primeraApostaMinima);
                        }
                    }
                } else if (valorCombinacio == 2) {
                    if (valorMesAlt > 5) {
                        int num = (int) (Math.random() * 11);
                        if (num > 5) {
                            cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, (int) jugador.getFitxesActuals() / 3);
                        } else {
                            if (jugador.getFitxesActuals() > primeraApostaMinima * 3) {
                                cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, primeraApostaMinima * 2);
                            } else if (jugador.getFitxesActuals() > primeraApostaMinima * 2) {
                                cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, primeraApostaMinima);
                            }
                        }
                    } else {
                        int num = (int) (Math.random() * 11);
                        if (num > 5) {
                            if (jugador.getFitxesActuals() >= primeraApostaMinima * 2) {
                                cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, primeraApostaMinima);
                            } else {
                                cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                            }

                        } else {
                            if (jugador.getFitxesActuals() >= primeraApostaMinima * 4) {
                                cp.controlJoc.ferRaise(jugador, fase, (int) apostaMinima, primeraApostaMinima);
                            } else {
                                cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                            }
                        }
                    }
                } else if (valorCombinacio == 1) {
                    cp.controlJoc.ferCheck(jugador, fase, apostaMinima);
                }
            }
        }
    }
}
