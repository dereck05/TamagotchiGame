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
public class Bodega {
    private ArrayList<AbstractMedicamento> medicamentos;
    private ArrayList<Alimento> alimentos;

    public ArrayList<AbstractMedicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(ArrayList<AbstractMedicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(ArrayList<Alimento> alimentos) {
        this.alimentos = alimentos;
    }
}
