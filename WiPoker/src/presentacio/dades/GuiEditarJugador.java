/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio.dades;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author wida47716705
 */
public class GuiEditarJugador {

    private JFrame jFrame;
    private JPanel jpFons;
    private JLabel jlTitol;
    private JLabel jlImatgeFons;
    private JLabel jlNom;
    private JLabel jlAlies;
    private JLabel jlEdat;
    private JTextField jtfNom;
    private JTextField jtfAlies2;
    private JTextField jtfEdat;
    private JLabel jlDNI;
    private JTextField jtfDNI;
    private JLabel jlTelefon;
    private JTextField jtfTelefon;
    private JButton jbEditar;

    public GuiEditarJugador() throws InterruptedException {
        iniciarComponents();
    }

    public void iniciarComponents() throws InterruptedException {
        jFrame = new JFrame();
        jFrame.setSize(new Dimension(338, 629));
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Editar jugador");
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

        jlNom = new JLabel();
        jlNom.setBounds(60, 100, 340, 104);
        jlNom.setText("Nom Complet ");
        jlNom.setForeground(Color.red);
        jlNom.setLayout(null);
        jpFons.add(jlNom);

        jtfNom = new JTextField(20);
        jtfNom.setBounds(170, 140, 120, 24);
        jpFons.add(jtfNom);

        jlAlies = new JLabel();
        jlAlies.setBounds(115, 170, 340, 104);
        jlAlies.setText("Alies ");
        jlAlies.setForeground(Color.red);
        jlAlies.setLayout(null);
        jpFons.add(jlAlies);

        jtfAlies2 = new JTextField(20);
        jtfAlies2.setBounds(170, 210, 120, 24);
        jpFons.add(jtfAlies2);

        jlEdat = new JLabel();
        jlEdat.setBounds(115, 240, 340, 104);
        jlEdat.setText("Edat ");
        jlEdat.setForeground(Color.red);
        jlEdat.setLayout(null);
        jpFons.add(jlEdat);

        jtfEdat = new JTextField(20);
        jtfEdat.setBounds(170, 280, 120, 24);
        jpFons.add(jtfEdat);

        jlDNI = new JLabel();
        jlDNI.setBounds(120, 310, 340, 104);
        jlDNI.setText("DNI ");
        jlDNI.setForeground(Color.red);
        jlDNI.setLayout(null);
        jpFons.add(jlDNI);

        jtfDNI = new JTextField(20);
        jtfDNI.setBounds(170, 350, 120, 24);
        jpFons.add(jtfDNI);

        jlTelefon = new JLabel();
        jlTelefon.setBounds(100, 380, 340, 104);
        jlTelefon.setText("Telefon ");
        jlTelefon.setForeground(Color.red);
        jlTelefon.setLayout(null);
        jpFons.add(jlTelefon);

        jtfTelefon = new JTextField(20);
        jtfTelefon.setBounds(170, 420, 120, 24);
        jpFons.add(jtfTelefon);

        jbEditar = new JButton("EDITAR");
        jbEditar.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbEditar.setBorder(null);
        jbEditar.setLayout(null);
        jbEditar.setBounds(100, 500, 120, 24);
        jbEditar.setIconTextGap(-260);
        jbEditar.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbEditar.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbEditar.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbEditar);

        jpFons.add(jlImatgeFons);
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new GuiEditarJugador();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuDades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
