/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.ControladorVentanaPrincipal.vp;
import Vista.VentanaBaño;
import Vista.VentanaPrincipal;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author maryp
 */
public class ControladorVentanaBaño implements ActionListener {
    VentanaBaño vb;
    ControladorJuego controlador;
    VentanaPrincipal vp;
    public ControladorVentanaBaño(VentanaBaño vb, ControladorJuego controlador,VentanaPrincipal v) {
        this.vb = vb;
        this.vp = v;
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
        if(baño.equals("Orinar")){
            ImageIcon imageIcon = new javax.swing.ImageIcon(getClass().getResource("/Vista/orinar.png"));
            Image image = imageIcon.getImage(); // transform it 
            Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimg);  // transform it back
            vp.lblPersonaje.setIcon(imageIcon);
        }
        if(baño.equals("Defecar")){
            ImageIcon imageIcon = new javax.swing.ImageIcon(getClass().getResource("/Vista/defecar.jpg"));
            Image image = imageIcon.getImage(); // transform it 
            Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimg);  // transform it back
            vp.lblPersonaje.setIcon(imageIcon);
        }
        
    }

    
}
