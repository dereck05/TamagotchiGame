/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Alimento.Alimento;
import static Controlador.ControladorVentanaPrincipal.vp;
import Vista.VentanaComedor;
import Vista.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Gloriana
 */
public class ControladorComedor implements ActionListener {
    VentanaComedor comedor;
    ControladorJuego controlador;
    DefaultTableModel dtm;
    VentanaPrincipal vp;
    public ControladorComedor(VentanaComedor comedor, ControladorJuego controlador,VentanaPrincipal v) {
        this.comedor = comedor;
        this.vp = v;
        this.controlador = controlador;
        this.comedor.btnComer.addActionListener(this);
        llenarTabla();
        this.comedor.setVisible(true);
    }
    public void llenarTabla(){
        dtm= new DefaultTableModel();
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
        this.comedor.jTable.setModel(dtm);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        comer();
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void comer(){
        if(this.comedor.jTable.getSelectedRow()!=-1){
            String nombre = (String) dtm.getValueAt(this.comedor.jTable.getSelectedRow(), 0); 
            
            for(Alimento alimento: controlador.getJuego().getBodega().getAlimentos().keySet()){
                if(alimento.getNombre().equals(nombre)){
                    controlador.comer("Comer", alimento);
                    int cantidad= controlador.getJuego().getBodega().getAlimentos().get(alimento);
                    if (cantidad==1){
                        controlador.getJuego().getBodega().getAlimentos().remove(alimento);
                    }else{
                    controlador.getJuego().getBodega().getAlimentos().replace(alimento, cantidad-1);}
                    controlador.actualizarPorcentajes("Estoy comiendo: "+ nombre);
                    break;
                }
            }
        }
        
    }

    
}
