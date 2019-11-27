/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.ControladorVentanaPrincipal.vp;
import Vista.VentanaGimnasio;
import Vista.VentanaPrincipal;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;

/**
 *
 * @author Gloriana
 */
public class ControladorGimnasio implements ActionListener {
    VentanaGimnasio gimnasio;
    ControladorJuego controlador;
    VentanaPrincipal vp;
    public ControladorGimnasio(VentanaGimnasio gimnasio, ControladorJuego contralador,VentanaPrincipal v) {
        this.gimnasio = gimnasio;
        this.vp = v;
        this.controlador = contralador;
        this.gimnasio.btnSeleccionar.addActionListener(this);
        llenarLista();
        this.gimnasio.setVisible(true);
       
        
    }
    public void llenarLista(){
        DefaultListModel modelo = new DefaultListModel();
        for(int i = 0; i<controlador.getEjercicios().size(); i++){
                System.out.println(controlador.getEjercicios().get(i).getNombre().equals("Natacion"));
                if((!controlador.getEjercicios().get(i).getNombre().equals("Natacion"))){
                    if(!controlador.getEjercicios().get(i).getNombre().equals("Futbol")){
                        modelo.addElement(controlador.getEjercicios().get(i).getNombre());
                    }
                }
                
        }
        this.gimnasio.listDeporte.setModel(modelo);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ejercitarse();
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void ejercitarse(){
        if(!this.gimnasio.listDeporte.isSelectionEmpty()){
            int seleccionado = this.gimnasio.listDeporte.getSelectedIndex();
            controlador.ejercitarse(controlador.getEjercicios().get(seleccionado));
            controlador.actualizarPorcentajes("Estoy haciendo: "+controlador.getEjercicios().get(seleccionado).getNombre());
        }
    }

    
}
