/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.tienda.sventasropa;

import com.tienda.sventasropa.UI.MainFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author Santy
 */
public class SVentasRopa {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
