/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Alimento.Alimento;
import Medicamentos.Medicamento;
import java.util.ArrayList;

/**
 *
 * @author derec
 */
public class Bodega {
    private ArrayList<Medicamento> medicamentos;
    private ArrayList<Alimento> alimentos;

    public ArrayList<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(ArrayList<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(ArrayList<Alimento> alimentos) {
        this.alimentos = alimentos;
    }
}
