/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.com.rah.test;

import javax.swing.SwingUtilities;
import tr.com.rah.fe.AnaPencereFE;
import tr.com.rah.fe.LoginFE;

/**
 *
 * @author rahimgng
 */
public class Run {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // AnaPencereFE anaPencereFE = new AnaPencereFE();
            LoginFE loginFE = new LoginFE();
        });

    }
}
