/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Alimento.Alimento;
import Ejercicios.Ejercicio;
import Enfermedades.Enfermedad;
import Estrategia.IStrategy;
import Factory.SuperFactory;
import Habilidades.Habilidad;
import Medicamentos.Medicamento;

/**
 *
 * @author naty9
 */
public class Facade {
    public SuperFactory factory;
    public Facade(){
        this.factory = new SuperFactory();
    }
    public IStrategy crearComer(String option, String nombreAlimento , int  porcentajeEnergia){
        Alimento alimento = this.factory.crearAlimento(nombreAlimento, porcentajeEnergia);
        IStrategy resul = this.factory.crearEstrategia(option,alimento);
        return resul;
    }
    public IStrategy crearEstrategia(String option){
        IStrategy resul = this.factory.crearEstrategia(option,null);
        return resul;
    }
    
    public Habilidad crearHabilidad(String option){
        Habilidad resul = this.factory.crearHabilidad(option);
        return resul;
    }
    
    public Ejercicio crearEjercicio(String option){
        Ejercicio resul = this.factory.crearEjercicio(option);
        return resul;
    }
    public Enfermedad crearEnfermedad(String option){
        Enfermedad resul = this.factory.crearEnfermedad(option);
        return resul;
    }
    
    public Alimento crearAlimento(String option, int porcentajeEnergia){
        Alimento resul = this.factory.crearAlimento(option, porcentajeEnergia);
        return resul;
    }
    
    public Medicamento crearMedicamento(String option){
        Medicamento resul = this.factory.crearMedicamento(option);
        return resul;
    }
    
    
}
