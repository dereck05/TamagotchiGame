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
public class Estado {
    private Humor Humor;
    private int Alegria;
    private int Energia;
    private int SaludMental;
    private int SaludFisica;
    private int Comida;
    private int Liquidos;
    
    public static enum Humor {
        FELIZ,
        TRISTE,
    }

    public Estado() {
        this.Humor = Humor.FELIZ;
        this.Alegria = 100;
        this.Energia = 100;
        this.SaludMental = 50;
        this.SaludFisica = 50;
        this.Comida =50;
        this.Liquidos = 50;
    }

    public Humor getHumor() {
        return Humor;
    }

    public Estado(Humor humor, int alegria,int energia, int saludMental, int saludFisica,int comida,int liquid) {
        this.Humor = humor;
        this.Energia = energia;
        this.SaludMental = saludMental;
        this.SaludFisica = saludFisica;
        this.Comida = comida;
        this.Liquidos = liquid;
        this.Alegria=alegria;
    }

    public int getComidaIngerida() {
        return Comida;
    }

    public void setComidaIngerida(int comidaIngerida) {
        this.Comida = comidaIngerida;
    }

    public int getAlegria() {
        return Alegria;
    }

    public void setAlegria(int Alegria) {
        this.Alegria = Alegria;
    }

    public int getComida() {
        return Comida;
    }

    public void setComida(int Comida) {
        this.Comida = Comida;
    }

    public int getLiquidos() {
        return Liquidos;
    }

    public void setLiquidos(int Liquidos) {
        this.Liquidos = Liquidos;
    }

    public void setHumor(Humor humor) {
        this.Humor = humor;
    }

    public int getEnergia() {
        return Energia;
    }

    public void setEnergia(int energia) {
        this.Energia = energia;
    }

    public int getSaludMental() {
        return SaludMental;
    }

    public void setSaludMental(int saludMental) {
        this.SaludMental = saludMental;
    }

    public int getSaludFisica() {
        return SaludFisica;
    }

    public void setSaludFisica(int saludFisica) {
        this.SaludFisica = saludFisica;
    }
    
    
}
