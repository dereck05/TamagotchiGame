/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author derec
 */
public class Juego {

    private PersonajeGame personaje;
    private ArrayList<AbstractDeporte> deportes;
    private ArrayList<AbstractEnfermedad> enfermedades;
    private Bodega huerto;
    private Bodega bodega;
    private Date tiempo;
    private int diasPorYear;
    private int minutosPorYear;

    public PersonajeGame getPersonaje() {
        return personaje;
    }

    public void setPersonaje(PersonajeGame personaje) {
        this.personaje = personaje;
    }

    public ArrayList<AbstractDeporte> getDeportes() {
        return deportes;
    }

    public void setDeportes(ArrayList<AbstractDeporte> deportes) {
        this.deportes = deportes;
    }

    public ArrayList<AbstractEnfermedad> getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(ArrayList<AbstractEnfermedad> enfermedades) {
        this.enfermedades = enfermedades;
    }

    public Bodega getHuerto() {
        return huerto;
    }

    public void setHuerto(Bodega huerto) {
        this.huerto = huerto;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public Date getTiempo() {
        return tiempo;
    }

    public void setTiempo(Date tiempo) {
        this.tiempo = tiempo;
    }

    public int getDiasPorYear() {
        return diasPorYear;
    }

    public void setDiasPorYear(int diasPorYear) {
        this.diasPorYear = diasPorYear;
    }

    public int getMinutosPorYear() {
        return minutosPorYear;
    }

    public void setMinutosPorYear(int minutosPorYear) {
        this.minutosPorYear = minutosPorYear;
    }
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
