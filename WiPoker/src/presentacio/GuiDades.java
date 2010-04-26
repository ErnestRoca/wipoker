/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        jpFons.add(jlTitol);

        jlImatgeFons = new JLabel();
        jlImatgeFons.setBounds(0, 104, 340, 499);
        jlImatgeFons.setLayout(null);
        jlImatgeFons.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/Wipokerbackground.jpg")));
        jpFons.add(jlImatgeFons);

        jbAfegir = new JButton("Afegir");
        jbAfegir.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbAfegir.setBorder(null);
        jbAfegir.setLayout(null);               
        jbAfegir.setBounds(80, 135, 180, 40);
        jbAfegir.setIconTextGap(-182);
        jbAfegir.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jpFons.add(jbAfegir);

        jbEditar = new JButton("Editar dades", new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbEditar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbEditar.setBorder(null);
        jbEditar.setLayout(null);
        jbEditar.setBounds(80, 180, 180, 40);
        //jbEditar.setIconTextGap(-182);
        //jbEditar.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jpFons.add(jbEditar);

        jFrame.setVisible(true);


    }

    public static void main(String[] args) {
        new GuiDades();
    }
}
