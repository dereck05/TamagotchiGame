/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author derec
 */
public class Apariencia {
    private int musculo;
    private int grasa;
    private int fuerza;
    private int estatura;
    private int rapidez;
    private int esfuerzo;

    public Apariencia(int musculo, int grasa, int fuerza, int estatura, int rapidez, int esfuerzo) {
        this.musculo = musculo;
        this.grasa = grasa;
        this.fuerza = fuerza;
        this.estatura = estatura;
        this.rapidez = rapidez;
        this.esfuerzo = esfuerzo;
    }

    public int getMusculo() {
        return musculo;
    }

    public void setMusculo(int musculo) {
        this.musculo = musculo;
    }

    public int getGrasa() {
        return grasa;
    }

    public void setGrasa(int grasa) {
        this.grasa = grasa;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getEstatura() {
        return estatura;
    }

    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    public int getRapidez() {
        return rapidez;
    }

    public void setRapidez(int rapidez) {
        this.rapidez = rapidez;
    }

    public int getEsfuerzo() {
        return esfuerzo;
    }

    public void setEsfuerzo(int esfuerzo) {
        this.esfuerzo = esfuerzo;
    }
}
