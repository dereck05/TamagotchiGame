/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.VentanaBaño;
import Vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author maryp
 */
public class ControladorVentanaPrincipal implements ActionListener {
    public static VentanaPrincipal vp;
    private ControladorJuego controlador;

    public ControladorVentanaPrincipal(ControladorJuego controlador) {
        this.controlador = controlador;
        this.vp.btnOpciones.addActionListener(this);
        inicializar();
    }
    public void inicializar(){
       vp.lblAlegria.setText(Integer.toString(this.controlador.getPersonaje().getEstado().getAlegria()));
       vp.lblEnergia.setText(Integer.toString(this.controlador.getPersonaje().getEstado().getEnergia()));
       vp.lblSaludMental.setText(Integer.toString(this.controlador.getPersonaje().getEstado().getSaludMental()));
       vp.lblSaludFisica.setText(Integer.toString(this.controlador.getPersonaje().getEstado().getSaludFisica()));
       vp.lblComida.setText(Integer.toString(this.controlador.getPersonaje().getEstado().getComida()));
       vp.lblLiquidos.setText(Integer.toString(this.controlador.getPersonaje().getEstado().getLiquidos()));
       vp.lblMusculo.setText(Integer.toString(this.controlador.getPersonaje().getApariencia().getMusculo()));
       vp.lblGrasa.setText(Integer.toString(this.controlador.getPersonaje().getApariencia().getGrasa()));
       vp.lblFuerza.setText(Integer.toString(this.controlador.getPersonaje().getApariencia().getFuerza()));
       vp.lblEstatura.setText(Integer.toString(this.controlador.getPersonaje().getApariencia().getEstatura()));
       vp.lblRapidez.setText(Integer.toString(this.controlador.getPersonaje().getApariencia().getRapidez()));
       vp.lblEsfuerzo.setText(Integer.toString(this.controlador.getPersonaje().getApariencia().getEsfuerzo()));
       vp.lblAuxiliar.setText("Sin moverme");
       vp.lblEdad.setText(Integer.toString(this.controlador.getPersonaje().getEdad()));
       //FALTA AGREGAR LA IMAGEN
                
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        VentanaBaño vb = new VentanaBaño();
        ControladorVentanaBaño cvb = new ControladorVentanaBaño(vb,controlador);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
