/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Alimento.Alimento;
import Medicamentos.Medicamento;
import Vista.VentanaBodega;
import Vista.VentanaPrincipal;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gloriana
 */
public class ControladorBodega {
    ControladorJuego controlador;
    VentanaBodega bodega;
    VentanaPrincipal vp;
    
    public ControladorBodega(ControladorJuego controlador, VentanaBodega bodega,VentanaPrincipal v) {
        this.vp = v;
        this.controlador = controlador;
        this.bodega = bodega;
        llenarAlimentos();
        llenarMedicamentos();
    }
    public void llenarAlimentos(){
         DefaultTableModel dtm= new DefaultTableModel();
        dtm.addColumn("Nombre");
        dtm.addColumn("Puntos");
        dtm.addColumn("Cantidad");
        for(HashMap.Entry<Alimento,Integer> entry : controlador.getJuego().getBodega().getAlimentos().entrySet()){
            String[] row = new String[3];
            row[0]=entry.getKey().getNombre();
            row[1]=Integer.toString(entry.getKey().getEnergia());
            row[2]=Integer.toString(entry.getValue());
            dtm.addRow(row);
            
        }
        this.bodega.tablaAlimentos.setModel(dtm);
    }
    public void llenarMedicamentos(){
        DefaultTableModel dtm= new DefaultTableModel();
        dtm.addColumn("Nombre");
        dtm.addColumn("Cantidad");
        for(HashMap.Entry<Medicamento,Integer> entry : controlador.getJuego().getBodega().getMedicamentos().entrySet()){
            String[] row = new String[2];
            row[0]=entry.getKey().getNombre();
            row[2]=Integer.toString(entry.getValue());
            dtm.addRow(row);
            
        }
        this.bodega.tablaMedicamentos.setModel(dtm);
    }
    
    
    
    
}
