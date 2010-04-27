/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio.jabber;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 *
 * @author wida45787385
 */
public class GuiNouUsuariJabber {

    private JFrame jFrame;
    private JPanel jpFons;
    private JLabel jlTitol;
    private JLabel jlImatgeFons;
    private JLabel jlUsuari;
    private JLabel jlPassword;
    private JLabel jlPasswordRepeat;
    private JLabel jlCorreu;
    private JTextField jftfUsuari;
    private JTextField jftfPassword;
    private JTextField jftfPasswordRepeat;
    private JTextField jftfCorreur;

    public GuiNouUsuariJabber() throws InterruptedException {
        iniciarComponents();
    }

    public void iniciarComponents() throws InterruptedException {
        jFrame = new JFrame();
        jFrame.setSize(new Dimension(338, 629));
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Crea el teu compte Jabber");
        jFrame.setLayout(null);
        jFrame.setBackground(Color.black);
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

        jlUsuari = new JLabel("Nom d'usuari");
        jlUsuari.setBorder(BorderFactory.createEtchedBorder());
        jlUsuari.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jlUsuari.setForeground(Color.red);        
        jlUsuari.setLayout(null);        
        jlUsuari.setBounds(40, 135, 120, 40);        
        jpFons.add(jlUsuari);

        jftfUsuari = new JTextField();
        jftfUsuari.setBorder(BorderFactory.createEtchedBorder());
        jftfUsuari.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jftfUsuari.setForeground(Color.red);
        jftfUsuari.setLayout(null);
        jftfUsuari.setBounds(170, 135, 120, 40);
        jpFons.add(jftfUsuari);
        /*
        jbAfegir = new JButton("Afegir nou jugador");
        jbAfegir.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbAfegir.setBorder(null);
        jbAfegir.setLayout(null);
        jbAfegir.setBounds(40, 225, 260, 40);
        jbAfegir.setIconTextGap(-260);
        jbAfegir.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbAfegir.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbAfegir.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbAfegir);

        jbEditar = new JButton("Editar un jugador");
        jbEditar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbEditar.setBorder(null);
        jbEditar.setLayout(null);
        jbEditar.setBounds(40, 315, 260, 40);
        jbEditar.setIconTextGap(-260);
        jbEditar.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbEditar.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbEditar.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbEditar);

        jbEliminar = new JButton("Eliminir dades jugador");
        jbEliminar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbEliminar.setBorder(null);
        jbEliminar.setLayout(null);
        jbEliminar.setBounds(40, 405, 260, 40);
        jbEliminar.setIconTextGap(-260);
        jbEliminar.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbEliminar.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbEliminar);

        jbTornar = new JButton("Tornar enrere");
        jbTornar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbTornar.setBorder(null);
        jbTornar.setLayout(null);
        jbTornar.setBounds(40, 495, 260, 40);
        jbTornar.setIconTextGap(-260);
        jbTornar.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbTornar.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbTornar.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbTornar);
         */
        
        jpFons.add(jlImatgeFons);
        jFrame.setVisible(true);

    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new GuiNouUsuariJabber();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiNouUsuariJabber.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
