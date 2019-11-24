/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Enfermedades.Enfermedad;
import Estrategia.IStrategy;
import Habilidades.Habilidad;
import Model.Personaje;
import java.util.ArrayList;


public class PersonajeGame extends Personaje  {
    private int edad;
    private ArrayList<IStrategy> comportamientos;
    private ArrayList<Habilidad> ataques;
    private Estado estado;
    private Apariencia apariencia;
    private String tipo;
    private String enfermedadActual;

    

    public PersonajeGame(String nombre, String imagen,int edad, ArrayList<IStrategy> comportamientos, ArrayList<Habilidad> ataques, Estado estado, Apariencia apariencia, String tipo, String enfermedad) {
        super(nombre,imagen);
        this.edad = edad;
        this.comportamientos = comportamientos;
        this.ataques = ataques;
        this.estado = estado;
        this.apariencia = apariencia;
        this.tipo = tipo;
        this.enfermedadActual = enfermedad;
    }
    public PersonajeGame(){}
    
    public void inicializar(){
        this.edad = 0;
        this.comportamientos = new ArrayList<IStrategy>();
        this.ataques = new ArrayList<Habilidad>() ;
        this.estado = new Estado();
        this.apariencia = new Apariencia();
        this.tipo = ""; // nose que es
        this.enfermedadActual = "";
    }
    public void addComportamiento(IStrategy comp){
        this.comportamientos.add(comp);
    }
    public void addHabilidad(Habilidad hab){
        this.ataques.add(hab);
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ArrayList<IStrategy> getComportamientos() {
        return comportamientos;
    }

    public void setComportamientos(ArrayList<IStrategy> comportamientos) {
        this.comportamientos = comportamientos;
    }

    public ArrayList<Habilidad> getAtaque() {
        return ataques;
    }

    public void setAtaque(ArrayList<Habilidad> ataques) {
        this.ataques = ataques;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Apariencia getApariencia() {
        return apariencia;
    }

    public void setApariencia(Apariencia apariencia) {
        this.apariencia = apariencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEnfermedadActual() {
        return enfermedadActual;
    }

    public void setEnfermedadActual(String enfermedadActual) {
        this.enfermedadActual = enfermedadActual;
    }
    
}
