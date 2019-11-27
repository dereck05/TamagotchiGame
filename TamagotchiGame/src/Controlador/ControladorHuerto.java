/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Alimento.Alimento;
import Medicamentos.Ibuprofeno;
import Medicamentos.Medicamento;
import Vista.VentanaHuerto;
import Vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Gloriana
 */
public class ControladorHuerto implements  ActionListener {
    VentanaHuerto huerto;
    ControladorJuego controlador;
    VentanaPrincipal vp;
    public ControladorHuerto(VentanaHuerto huerto, ControladorJuego controlador,VentanaPrincipal v) {
        this.vp = v;
        this.huerto = huerto;
        this.controlador = controlador;
        this.huerto.btnSeleccionar1.addActionListener(this);
        this.huerto.btnSeleccionar2.addActionListener(this);
        llenarAlimentos();
        llenarMedicamentos();
        this.huerto.setVisible(true);
    }
    
    public void llenarAlimentos(){
        DefaultListModel modelo = new DefaultListModel();      
        for (Alimento key : controlador.getJuego().getHuerto().getAlimentos().keySet() ) {
            modelo.addElement(key.getNombre());
        }   
        this.huerto.jListAlimentos.setModel(modelo);
    }
    public void llenarMedicamentos(){
        DefaultListModel modelo = new DefaultListModel();      
        for (Medicamento key : controlador.getJuego().getHuerto().getMedicamentos().keySet() ) {
            modelo.addElement(key.getNombre());
        }   
        this.huerto.jListMedicamentos.setModel(modelo);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(huerto.btnSeleccionar1)){
            agregarAlimentos();
        }else{
            agregarMedicamentos();
        }
         //To change body of generated methods, choose Tools | Templates.
    }
    public void agregarAlimentos(){
        if(!this.huerto.jListAlimentos.isSelectionEmpty()){
            String seleccionado=this.huerto.jListAlimentos.getSelectedValue();
            
            Alimento alimento;
            for(Alimento key: controlador.getJuego().getHuerto().getAlimentos().keySet()){
                if(seleccionado.equals(key.getNombre())){
                    alimento=key;
                    
                    controlador.getJuego().getHuerto().getAlimentos().remove(alimento);
                    controlador.getJuego().getBodega().añadirAlimento(alimento);
                    JOptionPane.showMessageDialog(huerto, "Alimento recolectado con éxito");
                    break;
                }
            }
            
        }
    }
    public void agregarMedicamentos(){
        if(!this.huerto.jListMedicamentos.isSelectionEmpty()){
            String seleccionado=this.huerto.jListMedicamentos.getSelectedValue();
            
            Medicamento medicamento;
            for(Medicamento key: controlador.getJuego().getHuerto().getMedicamentos().keySet()){
                if(seleccionado.equals(key.getNombre())){
                    medicamento=key;
                    
                    controlador.getJuego().getHuerto().getMedicamentos().remove(key);
                    controlador.getJuego().getBodega().añadirMedicamento(key);
                    JOptionPane.showMessageDialog(huerto, "Medicamento recolectado con éxito");
                    break;
                }
            }
            
        }
    }
    
}
