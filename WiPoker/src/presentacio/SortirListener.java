package presentacio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;

/**
 *
 * Clase que implementa els listeners per sortir de l'aplicació.<br>
 * 
 * @author wida53312560
 * @version 0.35x
 * 
 */
@Deprecated
public class SortirListener implements WindowListener, ActionListener {

    private GuiMenu menu;

    public SortirListener(GuiMenu menu) {
        this.menu = menu;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ActionEvent a0 = new ActionEvent(e.getSource(), 0, "");
        actionPerformed(a0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (JOptionPane.showConfirmDialog(menu.getjFrame(), "Esta segur que vol sortir?", "Sortir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            menu.getjFrame().dispose();
        }
    }
}
