/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.ControladorVentanaPrincipal.vp;
import Vista.VentanaBaño;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author maryp
 */
public class ControladorVentanaBaño implements ActionListener {
    VentanaBaño vb;
    ControladorJuego controlador;

    public ControladorVentanaBaño(VentanaBaño vb, ControladorJuego controlador) {
        this.vb = vb;
        this.controlador = controlador;
        this.vb.btnSeleccionar.addActionListener(this);
        this.vb.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        seleccionar();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void seleccionar(){
        String baño =(String)vb.comboBaño.getSelectedItem();
        controlador.estrategia(baño);
        controlador.actualizarPorcentajes(baño);
        
    }

    
}
