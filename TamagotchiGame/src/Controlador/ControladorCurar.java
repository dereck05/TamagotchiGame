/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Enfermedades.Enfermedad;
import Medicamentos.ICura;
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
        for(Enfermedad enfermedad: controlador.getJuego().getPersonaje().getEnfermedadesActivas().keySet()){
              modelo.addElement(enfermedad.getNombre());
                
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
            String seleccionado = curar.listEnfermedad.getSelectedValue();
            for(Enfermedad enfermedad: controlador.getJuego().getPersonaje().getEnfermedadesActivas().keySet()){
                if(seleccionado.equals(enfermedad.getNombre())){
                    enfermedadSeleccionada = enfermedad;
                    llenarCuras(enfermedadSeleccionada);
                    break;
                }
            }

            
        }
    }
    public void llenarCuras(Enfermedad enfermedad){
        this.curar.listCura.setVisible(true);
        DefaultListModel modelo = new DefaultListModel();
        for(int i = 0; i<enfermedad.getCuras().size(); i++){
              if(enfermedad.getCuras().get(i) instanceof Medicamento){
                  
                  Medicamento m= (Medicamento)enfermedad.getCuras().get(i);
                  System.out.println(m.getNombre());
                  for(Medicamento medicamento: controlador.getJuego().getBodega().getMedicamentos().keySet()){
                       if(medicamento.getNombre().equals(m.getNombre())){
                           modelo.addElement(enfermedad.getCuras().get(i).getNombre());
                           break;
                       }
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
            String seleccionado = curar.listCura.getSelectedValue();
            ICura curaSeleccionada;
            for(ICura cura: enfermedadSeleccionada.getCuras()){
                    if(seleccionado.equals(cura.getNombre())){
                        controlador.medicarse(cura);
                        controlador.getJuego().getPersonaje().getEnfermedadesActivas().remove(enfermedadSeleccionada);
                        JOptionPane.showMessageDialog(curar, "Enfermedad curada con éxito");
                        break;
                    }
            }
            
            
        }
    }
    
}
