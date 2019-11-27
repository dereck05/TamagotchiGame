/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Habilidades.Habilidad;
import Vista.VentanaPelear;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author maryp
 */
public class ControladorPelear implements ActionListener
{
    ControladorJuego controlador;
    VentanaPelear ventana;
    ArrayList<Habilidad> misHabilidades;
    int contador;

    public ControladorPelear(ControladorJuego controlador, VentanaPelear ventana, ArrayList<Habilidad> misHabilidades) {
        this.controlador = controlador;
        this.ventana = ventana;
        contador=0;
        this.misHabilidades = misHabilidades;
        this.ventana.btnPelear.addActionListener(this);
        actualizarMisPorcentajes();
        actualizarPorcentajesEnemigo();
        llenarHabilidades();
        this.ventana.setVisible(true);
        
    }
    public void llenarHabilidades(){
        DefaultListModel modelo = new DefaultListModel();
        for(int i = 0; i<misHabilidades.size(); i++){
               modelo.addElement(controlador.getJuego().getPersonaje().getAtaque().get(i));
        }
        this.ventana.listHabilidades1.setModel(modelo);
        modelo = new DefaultListModel();
        for(int i=0;i<this.controlador.getJuego().getPersonaje().getEnemigoActual().getAtaque().size();i++){
                    modelo.addElement(this.controlador.getJuego().getPersonaje().getEnemigoActual().getAtaque().get(i));
        }
        this.ventana.listHabilidades2.setModel(modelo);
    }
     public void actualizarMisPorcentajes(){
                ventana.lblAlegrial1.setText(Integer.toString(controlador.getJuego().getPersonaje().getEstado().getAlegria()));
                ventana.lblEnergia1.setText(Integer.toString(controlador.getJuego().getPersonaje().getEstado().getEnergia()));
                ventana.lblSaludMental1.setText(Integer.toString(controlador.getJuego().getPersonaje().getEstado().getSaludMental()));
                ventana.lblSaludFisica1.setText(Integer.toString(controlador.getJuego().getPersonaje().getEstado().getSaludFisica()));
                ventana.lblComida1.setText(Integer.toString(controlador.getJuego().getPersonaje().getEstado().getComida()));
                ventana.lblLiquidos1.setText(Integer.toString(controlador.getJuego().getPersonaje().getEstado().getLiquidos()));
                ventana.lblMusculo1.setText(Integer.toString(controlador.getJuego().getPersonaje().getApariencia().getMusculo()));
                ventana.lblGrasa1.setText(Integer.toString(controlador.getJuego().getPersonaje().getApariencia().getGrasa()));
                ventana.lblFuerza1.setText(Integer.toString(controlador.getJuego().getPersonaje().getApariencia().getFuerza()));
                ventana.lblEstatura1.setText(Integer.toString(controlador.getJuego().getPersonaje().getApariencia().getEstatura()));
                ventana.lblRapidez1.setText(Integer.toString(controlador.getJuego().getPersonaje().getApariencia().getRapidez()));
                ventana.lblEsfuerzo1.setText(Integer.toString(controlador.getJuego().getPersonaje().getApariencia().getEsfuerzo()));
    }
    public void actualizarPorcentajesEnemigo(){
                ventana.lblAlegria2.setText(Integer.toString(controlador.getJuego().getPersonaje().getEnemigoActual().getEstado().getAlegria()));
                ventana.lblEnergia2.setText(Integer.toString(controlador.getJuego().getPersonaje().getEnemigoActual().getEstado().getEnergia()));
                ventana.lblSaludMental2.setText(Integer.toString(controlador.getJuego().getPersonaje().getEnemigoActual().getEstado().getSaludMental()));
                ventana.lblSaludFisica2.setText(Integer.toString(controlador.getJuego().getPersonaje().getEnemigoActual().getEstado().getSaludFisica()));
                ventana.lblComida2.setText(Integer.toString(controlador.getJuego().getPersonaje().getEnemigoActual().getEstado().getComida()));
                ventana.lblLiquidos2.setText(Integer.toString(controlador.getJuego().getPersonaje().getEnemigoActual().getEstado().getLiquidos()));
                ventana.lblMusculo2.setText(Integer.toString(controlador.getJuego().getPersonaje().getEnemigoActual().getApariencia().getMusculo()));
                ventana.lblGrasa2.setText(Integer.toString(controlador.getJuego().getPersonaje().getEnemigoActual().getApariencia().getGrasa()));
                ventana.lblFuerza2.setText(Integer.toString(controlador.getJuego().getPersonaje().getEnemigoActual().getApariencia().getFuerza()));
                ventana.lblEstatura2.setText(Integer.toString(controlador.getJuego().getPersonaje().getEnemigoActual().getApariencia().getEstatura()));
                ventana.lblRapidez2.setText(Integer.toString(controlador.getJuego().getPersonaje().getEnemigoActual().getApariencia().getRapidez()));
                ventana.lblEsfuerzo2.setText(Integer.toString(controlador.getJuego().getPersonaje().getEnemigoActual().getApariencia().getEsfuerzo()));
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        pelear();
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void pelear() {
        if(contador<misHabilidades.size()){
            controlador.pelear(misHabilidades, contador);
            contador++;
            actualizarPorcentajesEnemigo();
            actualizarMisPorcentajes();
        }
        else{
            if(controlador.getJuego().getPersonaje().getEstado().getEnergia()<controlador.getJuego().getPersonaje().getEnemigoActual().getEstado().getEnergia()){
                JOptionPane.showMessageDialog(ventana, "Has perdido");
            }
            else{
                JOptionPane.showMessageDialog(ventana, "Has ganado");
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
