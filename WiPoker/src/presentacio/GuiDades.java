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
        jFrame.setSize(new Dimension(340, 490));
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Wipoker");
        jFrame.setLayout(null);
        jFrame.setBackground(Color.WHITE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

        jpFons = new JPanel();
        jpFons.setLayout(null);
        jpFons.setOpaque(true);
        jpFons.setBounds(0, 0, 340, 490);
        jFrame.add(jpFons);

        jlTitol = new JLabel();
        jlTitol.setBounds(0, 0, 340, 104);
        jlTitol.setLayout(null);
        jlTitol.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/WiPokerLogo2.gif")));
        jlTitol.setVerticalAlignment(SwingConstants.TOP);
        jpFons.add(jlTitol);

        jlImatgeFons = new JLabel();
        jlImatgeFons.setBounds(0, 104, 333, 499);
        jlTitol.setLayout(null);
        
        jlTitol.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/Wipokerbackground.jpg")));
        jlTitol.setVerticalAlignment(SwingConstants.BOTTOM);
        //jpFons.add(jlImatgeFons);
        jFrame.setVisible(true);


    }

    public static void main(String[] args) {
        new GuiDades();
    }
}
