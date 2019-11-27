/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Habilidades.Habilidad;
import Vista.VentanaEscogerHabilidades;
import Vista.VentanaPelear;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author maryp
 */
public class ControladorEscogerHabilidades implements ActionListener {
    ControladorJuego controlador;
    int numHabilidades;
    int habilidadesEscogidas;
    ArrayList<Habilidad> habilidades;
    VentanaEscogerHabilidades ventana;

    public ControladorEscogerHabilidades(ControladorJuego controlador, int numHabilidades, VentanaEscogerHabilidades ventana) {
        this.controlador = controlador;
        this.numHabilidades = numHabilidades;
        this.ventana = ventana;
        this.habilidadesEscogidas=0;
        this.habilidades = new ArrayList<Habilidad>();
        this.ventana.btnSeleccionar1.addActionListener(this);
        this.ventana.lblTitulo.setText("Escoja "+numHabilidades+" habilidades");
        llenarList();
        this.ventana.setVisible(true);
    }
    public void llenarList(){
        DefaultListModel modelo = new DefaultListModel();
        for(int i = 0; i<controlador.getJuego().getPersonaje().getAtaque().size(); i++){
               modelo.addElement(controlador.getJuego().getPersonaje().getAtaque().get(i));
        }
        this.ventana.listHabilidades.setModel(modelo);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        seleccionar();
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void seleccionar(){
        if(habilidadesEscogidas<=numHabilidades){
            if(!this.ventana.listHabilidades.isSelectionEmpty()){
                int seleccionado = this.ventana.listHabilidades.getSelectedIndex();
                habilidades.add(controlador.getJuego().getPersonaje().getAtaque().get(seleccionado));
                habilidadesEscogidas++;
            }
        }
        if(habilidadesEscogidas==numHabilidades){
            VentanaPelear ventana = new VentanaPelear();
            ControladorPelear cp = new ControladorPelear(controlador,ventana,habilidades);
        }
    }
    
}
