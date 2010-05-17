/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

/**
 *
 * @author ula
 */
public class ControladoraGui {

    private boolean login;

    public ControladoraGui() {
        login = false;
    }

    public void setLoginTrue() {
        login = true;
    }

    public void setLoginFalse() {
        login = false;
    }

    public boolean isLogin() {
        return login;
    }

    


}
