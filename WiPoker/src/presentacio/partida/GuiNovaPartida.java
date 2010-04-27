/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio.partida;

import presentacio.dades.GuiDades;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author wida45787385
 */
public class GuiNovaPartida {

    private JFrame jFrame;
    private JPanel jpFons;
    private JLabel jlTitol;
    private JLabel jlImatgeFons;
    private JButton jbUnirsePartida;
    private JButton jbContraMaquina;
    private JButton jbCrearPartida;
    private JButton jbTornar;

    public GuiNovaPartida() throws InterruptedException {
        iniciarComponents();
    }

    public void iniciarComponents() throws InterruptedException {
        jFrame = new JFrame();
        jFrame.setSize(new Dimension(338, 629));
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Nova Partida");
        jFrame.setLayout(null);
        jFrame.setBackground(Color.WHITE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

        jpFons = new JPanel();
        jpFons.setLayout(null);
        jpFons.setOpaque(false);
        jpFons.setBounds(0, 0, 340, 950);
        jFrame.add(jpFons);

        jlTitol = new JLabel();
        jlTitol.setBounds(0, 0, 340, 104);
        jlTitol.setLayout(null);
        jlTitol.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/WiPokerLogo2.gif")));
        jpFons.add(jlTitol);

        jlImatgeFons = new JLabel();
        jlImatgeFons.setBounds(0, 104, 340, 499);
        jlImatgeFons.setLayout(null);
        jlImatgeFons.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/Wipokerbackground.jpg")));
        jlImatgeFons.setOpaque(false);


        jbCrearPartida = new JButton("Crear nova partida internet");
        jbCrearPartida.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbCrearPartida.setBorder(null);
        jbCrearPartida.setLayout(null);
        jbCrearPartida.setBounds(40, 135, 260, 40);
        jbCrearPartida.setIconTextGap(-260);
        jbCrearPartida.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbCrearPartida.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbCrearPartida.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbCrearPartida);

        jbUnirsePartida = new JButton("Unir-se a partida a internet");
        jbUnirsePartida.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbUnirsePartida.setBorder(null);
        jbUnirsePartida.setLayout(null);
        jbUnirsePartida.setBounds(40, 225, 260, 40);
        jbUnirsePartida.setIconTextGap(-260);
        jbUnirsePartida.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbUnirsePartida.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbUnirsePartida.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbUnirsePartida);

        jbContraMaquina = new JButton("Jugar contra la màquina");
        jbContraMaquina.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbContraMaquina.setBorder(null);
        jbContraMaquina.setLayout(null);
        jbContraMaquina.setBounds(40, 315, 260, 40);
        jbContraMaquina.setIconTextGap(-260);
        jbContraMaquina.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbContraMaquina.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbContraMaquina.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbContraMaquina);

        jbTornar = new JButton("Tornar enrere");
        jbTornar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbTornar.setBorder(null);
        jbTornar.setLayout(null);
        jbTornar.setBounds(40, 405, 260, 40);
        jbTornar.setIconTextGap(-260);
        jbTornar.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbTornar.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbTornar.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbTornar);

        jpFons.add(jlImatgeFons);

        jFrame.setVisible(true);



    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new GuiNovaPartida();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiDades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
