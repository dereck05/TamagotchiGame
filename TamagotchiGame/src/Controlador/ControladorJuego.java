/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Ejercicios.Ejercicio;
import Enfermedades.Enfermedad;
import Estrategia.IStrategy;
import Factory.SuperFactory;
import Habilidades.Habilidad;
import Medicamentos.Medicamento;
import Modelo.Facade;

import Modelo.Juego;
import Modelo.PersonajeGame;
import Modelo.Proxy.Proxy;
import Vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author derec
 */
public class ControladorJuego implements ActionListener{
    private Vista vista;
    public Proxy proxy;
    public Facade fachada;
    public ControladorJuego(Vista v){
        this.vista = v;
        this.vista.btnComer.addActionListener(this);
        this.vista.btnEjercicio.addActionListener(this);
        this.vista.btnEnfermar.addActionListener(this);
        this.vista.btnHabilidad.addActionListener(this);
        this.vista.btnMedicina.addActionListener(this);
        this.fachada = new Facade();
        this.proxy = new Proxy();
        this.vista.setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Comer":
                comer(this.vista.txtComer.getText());
                break;
            case "Ejercitarse":
                ejercitarse(this.vista.txtEjercicio.getText());
                break;
            case "Enfermarse":
                enfermarse(this.vista.txtEnfermedad.getText());
                break;
            case "Estrategia":
                estrategia(this.vista.txtEstrategia.getText());
                break;
            case "Habilidad":
                habilidad(this.vista.txtHabilidad.getText());
                break;
            case "Medicarse":
                medicarse(this.vista.txtMedicina.getText());
                break;
     
            default:
                JOptionPane.showMessageDialog(vista, "Opción no registrada");
                break;
        }
    }
    
    public IStrategy comer(String option){
        IStrategy resul = this.fachada.crearEstrategia(option);
        
        proxy.setActivity("Comer");
        proxy.guardar();
        return resul;
    }
    public Ejercicio ejercitarse(String option){
        Ejercicio resul = this.fachada.crearEjercicio(option);
        HashMap<String,Integer> valores = resul.ejercitarse();
        System.out.println(valores.get("Musculo"));

        proxy.setActivity("Ejercitarse");
        proxy.guardar();
        return resul;
    }
    public Enfermedad enfermarse(String option){
        Enfermedad resul = this.fachada.crearEnfermedad(option);
        
        proxy.setActivity("Enfermarse");
        proxy.guardar();
        return resul;
    }
    public IStrategy estrategia(String option){
        IStrategy resul = this.fachada.crearEstrategia(option);
        
        proxy.setActivity("Estrategia");
        proxy.guardar();
        return resul;
    }
    public Habilidad habilidad(String option){
        Habilidad resul = this.fachada.crearHabilidad(option);
        
        proxy.setActivity("Habilidad");
        proxy.guardar();
        return resul;
    }
    public Medicamento medicarse(String option){
        Medicamento resul = this.fachada.crearMedicamento(option);
        
        proxy.setActivity("Medicarse");
        proxy.guardar();
        return resul;
    }
  
    public static void main(String[] args){
        Juego juego =new Juego();
        PersonajeGame personaje = new PersonajeGame();
        personaje.inicializar();
        Vista vista = new Vista();
        ControladorJuego c = new ControladorJuego(vista);
        
        
    }
}
