/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Alimento.Alimento;
import Medicamentos.Medicamento;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author derec
 */
public class Bodega {
    private HashMap<Medicamento,Integer> medicamentos;
    private HashMap<Alimento,Integer> alimentos;
    
    public Bodega(){
        this.alimentos = new HashMap<>();
        this.medicamentos = new HashMap<>();
    }
    
    public void añadirAlimento(Alimento a){
        if(alimentos.containsKey(a)){
            int cant = alimentos.get(a);
            alimentos.replace(a, cant+1);
        }else{
            alimentos.put(a, 1);
        }
        
    }
    public void añadirMedicamento(Medicamento m){
        if(medicamentos.containsKey(m)){
            int cant = medicamentos.get(m);
            
            medicamentos.replace(m, cant+1);
        }else{
            medicamentos.put(m, 1);
        }
    }

    public HashMap<Medicamento, Integer> getMedicamentos() {
        
        return medicamentos;
    }
   
    public void setMedicamentos(HashMap<Medicamento, Integer> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public HashMap<Alimento, Integer> getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(HashMap<Alimento, Integer> alimentos) {
        this.alimentos = alimentos;
    }
    
}
