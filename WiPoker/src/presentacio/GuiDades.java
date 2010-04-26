/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentacio;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;
import javax.swing.Icon;
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

    private JFrame jfsdi;
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
        jfsdi = new JFrame();
        jfsdi.setLayout(null);
        jfsdi.setTitle("Wipoker");
        jfsdi.setSize(new Dimension(1024, 768));
        jfsdi.setResizable(false);
        jfsdi.setLocationRelativeTo(null);

        jpFons = new JPanel();
        jpFons.setSize(1024, 768);
        jpFons.setLayout(null);

        jlTitol = new JLabel();
        jlTitol.setAlignmentX(SwingConstants.CENTER);
        jlTitol.setOpaque(true);
        jlTitol.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/WiPokerLogo2.gif")));
        jpFons.add(jlTitol);
        jfsdi.add(jpFons);
        jfsdi.setVisible(true);
    }

    public static void main(String[] args) {
        new GuiDades();
    }


}
