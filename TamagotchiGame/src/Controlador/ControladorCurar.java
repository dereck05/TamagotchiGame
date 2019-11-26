/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Enfermedades.Enfermedad;
import Medicamentos.Medicamento;
import Vista.VentanaCurar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author maryp
 */
public class ControladorCurar implements ActionListener {
    VentanaCurar curar;
    ControladorJuego controlador;
    Enfermedad enfermedadSeleccionada;

    public ControladorCurar(VentanaCurar curar, ControladorJuego controlador) {
        this.curar = curar;
        this.controlador = controlador;
        this.curar.btnCurar.addActionListener(this);
        this.curar.btnEnfermedad.addActionListener(this);
        this.curar.listCura.setVisible(false);
        llenarEnfermedades();
        this.curar.setVisible(true);
    }
    public void llenarEnfermedades(){
        DefaultListModel modelo = new DefaultListModel();
        for(int i = 0; i<controlador.getJuego().getPersonaje().getEnfermedadesActivas().size(); i++){
              modelo.addElement(controlador.getJuego().getPersonaje().getEnfermedadesActivas().get(i).getNombre());
                
        }
        this.curar.listEnfermedad.setModel(modelo);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.curar.btnEnfermedad)){
            seleccionarEnfermedad();
        }
        else if(e.getSource().equals(this.curar.btnCurar)){
            seleccionarCura();
        }
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void seleccionarEnfermedad(){
        if(!curar.listEnfermedad.isSelectionEmpty()){
            int seleccionado = curar.listEnfermedad.getSelectedIndex();
            enfermedadSeleccionada=controlador.getJuego().getPersonaje().getEnfermedadesActivas().get(seleccionado);
            llenarCuras(enfermedadSeleccionada);
            
        }
    }
    public void llenarCuras(Enfermedad enfermedad){
        this.curar.listCura.setVisible(true);
        DefaultListModel modelo = new DefaultListModel();
        for(int i = 0; i<enfermedad.getCuras().size(); i++){
              if(enfermedad.getCuras().get(i) instanceof Medicamento){
                  Medicamento m= (Medicamento)enfermedad.getCuras().get(i);
                  if(controlador.getJuego().getBodega().getMedicamentos().containsKey(m)){
                    modelo.addElement(enfermedad.getCuras().get(i).getNombre());
                  }
              }
              else{
                 modelo.addElement(enfermedad.getCuras().get(i).getNombre());
              }
                     
        }
        this.curar.listCura.setModel(modelo);
    
    }
    public void seleccionarCura(){
        if(!curar.listCura.isSelectionEmpty()){
            int seleccionado = curar.listCura.getSelectedIndex();
            controlador.medicarse(enfermedadSeleccionada.getCuras().get(seleccionado));
            controlador.getJuego().getPersonaje().getEnfermedadesActivas().remove(seleccionado);
            JOptionPane.showMessageDialog(curar, "Enfermedad curada con éxito");
            
        }
    }
    
}