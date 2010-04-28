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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author wida45787385
 */
public class GuiNouUsuariJabber {

    private JFrame jFrame;
    private JPanel jpFons;
    private JLabel jlTitol;
    private JLabel jlImatgeFons;
    private JLabel jlNom;
    private JLabel jlPassword;
    private JLabel jlPassword2;
    private JTextField jtfNom;
    private JTextField jtfPassword;
    private JTextField jtfPassword2;
    private JLabel jlCorreu;
    private JTextField jtfCorreu;
    
    private JButton jbAfegir;

    public GuiNouUsuariJabber() throws InterruptedException {
        iniciarComponents();

    }

    public void iniciarComponents() throws InterruptedException {
        jFrame = new JFrame();
        jFrame.setSize(new Dimension(338, 629));
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("Crear compte Jabber");
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

        jlPassword = new JLabel();
        jlPassword.setBounds(115, 170, 340, 104);
        jlPassword.setText("Alies ");
        jlPassword.setForeground(Color.red);
        jlPassword.setLayout(null);
        jpFons.add(jlPassword);

        jtfPassword = new JTextField(20);
        jtfPassword.setBounds(170, 210, 120, 24);
        jpFons.add(jtfPassword);

        jlPassword2 = new JLabel();
        jlPassword2.setBounds(115, 240, 340, 104);
        jlPassword2.setText("Edat ");
        jlPassword2.setForeground(Color.red);
        jlPassword2.setLayout(null);
        jpFons.add(jlPassword2);

        jtfPassword2 = new JTextField(20);
        jtfPassword2.setBounds(170, 280, 120, 24);
        jpFons.add(jtfPassword2);

        jlCorreu = new JLabel();
        jlCorreu.setBounds(120, 310, 340, 104);
        jlCorreu.setText("DNI ");
        jlCorreu.setForeground(Color.red);
        jlCorreu.setLayout(null);
        jpFons.add(jlCorreu);

        jtfCorreu = new JTextField(20);
        jtfCorreu.setBounds(170, 350, 120, 24);
        jpFons.add(jtfCorreu);

        jlTelefon = new JLabel();
        jlTelefon.setBounds(100, 380, 340, 104);
        jlTelefon.setText("Telefon ");
        jlTelefon.setForeground(Color.red);
        jlTelefon.setLayout(null);
        jpFons.add(jlTelefon);

        jtfTelefon = new JTextField(20);
        jtfTelefon.setBounds(170, 420, 120, 24);
        jpFons.add(jtfTelefon);

        jbAfegir = new JButton("CREAR");
        jbAfegir.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbAfegir.setBorder(null);
        jbAfegir.setLayout(null);
        jbAfegir.setBounds(100, 500, 120, 24);
        jbAfegir.setIconTextGap(-260);
        jbAfegir.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbAfegir.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbAfegir.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbAfegir);

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

