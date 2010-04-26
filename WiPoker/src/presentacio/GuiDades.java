/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio;

import java.awt.Color;
import java.awt.Dimension;
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
public class GuiDades {

    private JFrame jFrame;
    private JPanel jpFons;
    private JLabel jlTitol;
    private JLabel jlImatgeFons;
    private JButton jbAfegir;
    private JButton jbEditar;
    private JButton jbEliminar;

    public GuiDades() {
        iniciarComponents();
    }

    public void iniciarComponents() {
        jFrame = new JFrame();
        jFrame.setSize(new Dimension(338, 629));
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Dades del jugador");
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
        jlTitol.setVerticalAlignment(SwingConstants.TOP);
        jpFons.add(jlTitol);

        jlImatgeFons = new JLabel();
        jlImatgeFons.setBounds(0, 104, 340, 499);
        jlImatgeFons.setLayout(null);
        jlImatgeFons.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/Wipokerbackground.jpg")));
        jlImatgeFons.setVerticalAlignment(SwingConstants.BOTTOM);
        jpFons.add(jlImatgeFons);

        jbAfegir = new JButton("Afegir un jugador");
        jbAfegir.setLayout(null);
        jbAfegir.setHorizontalAlignment(SwingConstants.CENTER);
        jbAfegir.setVerticalAlignment(SwingConstants.TOP);
        jbAfegir.setHorizontalTextPosition(SwingConstants.CENTER);
        jbAfegir.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/Wipokerbackground.jpg")));
        jbAfegir.setBounds(140, 124, 30, 20);
        jpFons.add(jbAfegir);


        jFrame.setVisible(true);


    }

    public static void main(String[] args) {
        new GuiDades();
    }
}
