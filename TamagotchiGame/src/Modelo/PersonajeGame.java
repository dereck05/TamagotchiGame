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
import java.util.HashMap;


public class PersonajeGame extends Personaje  {
    private int edad;
    private ArrayList<IStrategy> comportamientos;
    private ArrayList<Habilidad> ataques;
    private Estado estado;
    private Apariencia apariencia;
    private String tipo;
    private String enfermedadActual;
    private Personaje amigoActual;
    private PersonajeGame enemigoActual;

    

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
    public PersonajeGame(){
        
    }
    
    public void inicializar(){
        this.edad = 0;
        this.comportamientos = new ArrayList<IStrategy>();
        this.ataques = new ArrayList<Habilidad>() ;
        this.estado = new Estado();
        this.apariencia = new Apariencia();
        this.tipo = ""; // nose que es
        this.enfermedadActual = "";
    }
    
    public void actualizar(HashMap<String,Integer> val){
       
            
        if(val.containsKey("Musculo")){
            int num = val.get("Musculo");
            
            this.apariencia.setMusculo(this.apariencia.getMusculo()+num >100 ? 100:(this.apariencia.getMusculo()+num < 0 ? 0 :this.apariencia.getMusculo()+num ));
        }
        if(val.containsKey("Grasa")){
            int num = val.get("Grasa");
            this.apariencia.setGrasa(this.apariencia.getGrasa()+num > 100 ? 100:(this.apariencia.getGrasa()+num < 0 ? 0 :this.apariencia.getGrasa()+num ));
        }
        if(val.containsKey("Fuerza")){
            int num = val.get("Fuerza");
            this.apariencia.setFuerza(this.apariencia.getFuerza()+num > 100 ? 100:(this.apariencia.getFuerza()+num < 0 ? 0 : this.apariencia.getFuerza()+num));
        }
        if(val.containsKey("Estatura")){
            int num = val.get("Estatura");
            this.apariencia.setEstatura(this.apariencia.getEstatura()+num > 100 ? 100:(this.apariencia.getEstatura()+num < 0 ? 0 : this.apariencia.getEstatura()+num));
        }
        if(val.containsKey("Rapidez")){
            int num = val.get("Rapidez");
            this.apariencia.setRapidez(this.apariencia.getRapidez()+num > 100 ? 100:(this.apariencia.getRapidez()+num < 0 ? 0 : this.apariencia.getRapidez()+num));
        }
        if(val.containsKey("Esfuerzo")){
            int num = val.get("Esfuerzo");
            this.apariencia.setEsfuerzo(this.apariencia.getEsfuerzo()+num > 100 ? 100:(this.apariencia.getEsfuerzo()+num < 0 ? 0 : this.apariencia.getEsfuerzo()+num));
        }
        if(val.containsKey("Energia")){
            int num = val.get("Energia");
            this.estado.setEnergia(this.estado.getEnergia()+num > 100 ? 100:(this.estado.getEnergia()+num < 0 ? 0 : this.estado.getEnergia()+num));
        }
        if(val.containsKey("Salud fisica")){
            int num = val.get("Salud fisica");
            this.estado.setSaludFisica(this.estado.getSaludFisica()+num > 100 ? 100:(this.estado.getSaludFisica()+num < 0 ? 0 : this.estado.getSaludFisica()+num));
        }
        if(val.containsKey("Salud mental")){
            int num = val.get("Salud mental");
            this.estado.setSaludMental(this.estado.getSaludMental()+num > 100 ? 100:(this.estado.getSaludMental()+num < 0 ? 0 : this.estado.getSaludMental()+num));
        }
        if(val.containsKey("comida injerida")){
            int num = val.get("comida injerida");
            this.estado.setComidaIngerida(this.estado.getComidaIngerida()+num > 100 ? 100:(this.estado.getComidaIngerida()+num < 0 ? 0 : this.estado.getComidaIngerida()+num));
        }
        if(val.containsKey("Liquidos")){
            int num = val.get("Liquidos");
            this.estado.setLiquidos(this.estado.getLiquidos()+num > 100 ? 100:(this.estado.getLiquidos()+num < 0 ? 0 : this.estado.getLiquidos()+num));
        }
        if(val.containsKey("Alegria")){
            int num = val.get("Alegria");
            this.estado.setAlegria(this.estado.getAlegria()+num > 100 ? 100:(this.estado.getAlegria()+num < 0 ? 0 : this.estado.getAlegria()+num));
        }
        
    }
    public void imprimirEstado(){
        System.out.println("***************Apariencia***************");
        System.out.println("Musculo: " + this.apariencia.getMusculo());
        System.out.println("Grasa: " + this.apariencia.getGrasa());
        System.out.println("Fuerza: " + this.apariencia.getFuerza());
        System.out.println("Estatura: " + this.apariencia.getEstatura());
        System.out.println("Rapidez: " + this.apariencia.getRapidez());
        System.out.println("Esfuerzo: " + this.apariencia.getEsfuerzo());
        System.out.println("***************Estado***************");
        System.out.println("Energia: " + this.estado.getEnergia());
        System.out.println("Salud Fisica: " + this.estado.getSaludFisica());
        System.out.println("Salud Mental: " + this.estado.getSaludMental());
        System.out.println("Comida Ingerida: " + this.estado.getComidaIngerida());
        System.out.println("Liquidos: " + this.estado.getLiquidos());
        System.out.println("Alegria: " + this.estado.getAlegria());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

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

    public Personaje getAmigoActual() {
        return amigoActual;
    }

    public void setAmigoActual(Personaje amigoActual) {
        this.amigoActual = amigoActual;
    }

    public PersonajeGame getEnemigoActual() {
        return enemigoActual;
    }

    public void setEnemigoActual(PersonajeGame enemigoActual) {
        this.enemigoActual = enemigoActual;
    }
    
}
