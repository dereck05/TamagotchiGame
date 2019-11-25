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
    private PersonajeGame personaje;
    private Juego juego;
    public Facade fachada;
    
    public ControladorJuego(Vista v){
        this.vista = v;
        
        this.vista.btnComer.addActionListener(this);
        this.vista.btnEjercicio.addActionListener(this);
        this.vista.btnEnfermar.addActionListener(this);
        this.vista.btnHabilidad.addActionListener(this);
        this.vista.btnMedicina.addActionListener(this);
        this.juego = new Juego();
        this.fachada = new Facade();
        this.proxy = new Proxy();
        proxy.setFilename("dia");
        this.personaje = new PersonajeGame();
        this.personaje.inicializar();
        this.personaje.imprimirEstado();
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
    
    public void comer(String option){
        IStrategy resul = this.fachada.crearEstrategia(option);
        
        proxy.setActivity(resul.toString());
        proxy.guardar();
        //return resul;
    }
    public void ejercitarse(String option){
        Ejercicio resul = this.fachada.crearEjercicio(option);
        HashMap<String,Integer> valores = resul.ejercitarse();
        this.personaje.actualizar(valores);
        this.personaje.imprimirEstado();
        
        //proxy.setActivity(resul.);
        proxy.guardar();
    }
    public void enfermarse(String option){
        Enfermedad resul = this.fachada.crearEnfermedad(option);
        HashMap<String,Integer> valores = resul.Enfermarse();
        this.personaje.actualizar(valores);
        this.personaje.imprimirEstado();
        
        //proxy.setActivity(resul);
        proxy.guardar();
    }
    public void estrategia(String option){
        IStrategy resul = this.fachada.crearEstrategia(option);
        HashMap<String,Integer> valores = resul.ejecutar();
        this.personaje.actualizar(valores);
        this.personaje.imprimirEstado();
        
        proxy.setActivity(resul.toString());
        proxy.guardar();
    }
    public void habilidad(String option){
        Habilidad resul = this.fachada.crearHabilidad(option);
        HashMap<String,Integer> valores = resul.atacar();
        this.personaje.actualizar(valores);
        this.personaje.imprimirEstado();
        
        proxy.setActivity(resul.toString());
        proxy.guardar();
    }
    public void medicarse(String option){
        Medicamento resul = this.fachada.crearMedicamento(option);
        HashMap<String,Integer> valores = resul.curar();
        this.personaje.actualizar(valores);
        this.personaje.imprimirEstado();
        
        proxy.setActivity("Medicarse");
        proxy.guardar();
    }
  
    public static void main(String[] args){
        Vista vista = new Vista();
        ControladorJuego c = new ControladorJuego(vista);
        
        
    }
}
