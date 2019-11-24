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
    private int Musculo;
    private int Grasa;
    private int Fuerza;
    private int Estatura;
    private int Rapidez;
    private int Esfuerzo;

    public Apariencia(int musculo, int grasa, int fuerza, int estatura, int rapidez, int esfuerzo) {
        this.Musculo = musculo;
        this.Grasa = grasa;
        this.Fuerza = fuerza;
        this.Estatura = estatura;
        this.Rapidez = rapidez;
        this.Esfuerzo = esfuerzo;
    }

    public Apariencia() {
        this.Musculo = 10;
        this.Grasa = 10;
        this.Fuerza = 10;
        this.Estatura = 10;
        this.Rapidez = 10;
        this.Esfuerzo = 10;
    }

    public int getMusculo() {
        return Musculo;
    }

    public void setMusculo(int musculo) {
        this.Musculo = musculo;
    }

    public int getGrasa() {
        return Grasa;
    }

    public void setGrasa(int grasa) {
        this.Grasa = grasa;
    }

    public int getFuerza() {
        return Fuerza;
    }

    public void setFuerza(int fuerza) {
        this.Fuerza = fuerza;
    }

    public int getEstatura() {
        return Estatura;
    }

    public void setEstatura(int estatura) {
        this.Estatura = estatura;
    }

    public int getRapidez() {
        return Rapidez;
    }

    public void setRapidez(int rapidez) {
        this.Rapidez = rapidez;
    }

    public int getEsfuerzo() {
        return Esfuerzo;
    }

    public void setEsfuerzo(int esfuerzo) {
        this.Esfuerzo = esfuerzo;
    }
}
