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
    private int humor;
    private int energia;
    private int saludMental;
    private int saludFisica;

    Estado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getHumor() {
        return humor;
    }

    public Estado(int humor, int energia, int saludMental, int saludFisica) {
        this.humor = humor;
        this.energia = energia;
        this.saludMental = saludMental;
        this.saludFisica = saludFisica;
    }

    public void setHumor(int humor) {
        this.humor = humor;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getSaludMental() {
        return saludMental;
    }

    public void setSaludMental(int saludMental) {
        this.saludMental = saludMental;
    }

    public int getSaludFisica() {
        return saludFisica;
    }

    public void setSaludFisica(int saludFisica) {
        this.saludFisica = saludFisica;
    }
    
    
}
