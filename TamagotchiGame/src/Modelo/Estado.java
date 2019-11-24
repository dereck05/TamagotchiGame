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
    private int Energia;
    private int SaludMental;
    private int SaludFisica;
    
    public static enum Humor {
        FELIZ,
        TRISTE,
    }

    public Estado() {
        this.Humor = Humor.FELIZ;
        this.Energia = 10;
        this.SaludMental = 10;
        this.SaludFisica = 10;
    }

    public Humor getHumor() {
        return Humor;
    }

    public Estado(Humor humor, int energia, int saludMental, int saludFisica) {
        this.Humor = humor;
        this.Energia = energia;
        this.SaludMental = saludMental;
        this.SaludFisica = saludFisica;
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
