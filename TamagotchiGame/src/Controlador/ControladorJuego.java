/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Ejercicios.Ejercicio;
import Enfermedades.Enfermedad;
import Estrategia.IStrategy;
import Factory.SuperFactory;
import Habilidades.Habilidad;
import Medicamentos.Medicamento;
import Modelo.Facade;

import Modelo.Juego;
import Modelo.PersonajeGame;
import Modelo.Proxy.Proxy;
import Vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author derec
 */
public class ControladorJuego implements ActionListener,Runnable{
    private Vista vista;
    public Proxy proxy;
    private PersonajeGame personaje;
    private Juego juego;
    public Facade fachada;
    private ArrayList<Enfermedad> enfermedades;
    
    private boolean estado;
    Thread hilo;
    private String horas,minutos,segundos;
    private int h,m,s;
    private String day,month,year;
    private int dayInt,monthInt,yearInt;
    private static String dateInString;
    
    public ControladorJuego(Vista v){
        this.vista = v;
        this.vista.btnComer.addActionListener(this);
        this.vista.btnEjercicio.addActionListener(this);
        this.vista.btnEnfermar.addActionListener(this);
        this.vista.btnHabilidad.addActionListener(this);
        this.vista.btnMedicina.addActionListener(this);
        this.juego = new Juego();
        this.fachada = new Facade();
        this.proxy = new Proxy();
        this.personaje = new PersonajeGame();
        this.personaje.inicializar();
        this.personaje.imprimirEstado();
        this.addEnfermedades();
        this.vista.setVisible(true);
        this.h = 0;
        this.m = 0;
        this.s = 0;
        this.day = "";
        this.month = "";
        this.year = "";
        this.dayInt = 1;
        this.monthInt = 1;
        this.dateInString = "1-1-2019";
        this.proxy.setFilename("dia " + this.dateInString);
        iniciar();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Comer":
                comer(this.vista.txtComer.getText());
                break;
            case "Ejercitarse":
                ejercitarse(this.vista.txtEjercicio.getText());
                break;
            case "Enfermarse":
                enfermarse(this.vista.txtEnfermedad.getText());
                break;
            case "Estrategia":
                estrategia(this.vista.txtEstrategia.getText());
                break;
            case "Habilidad":
                habilidad(this.vista.txtHabilidad.getText());
                break;
            case "Medicarse":
                medicarse(this.vista.txtMedicina.getText());
                break;
            default:
                JOptionPane.showMessageDialog(vista, "Opción no registrada");
                break;
        }
    }
    
    public void comer(String option){
        IStrategy resul = this.fachada.crearEstrategia(option);
        
        proxy.setActivity(resul.toString());
        proxy.guardar();
        //return resul;
    }
    public void ejercitarse(String option){
        Ejercicio resul = this.fachada.crearEjercicio(option);
        HashMap<String,Integer> valores = resul.ejercitarse();
        this.personaje.actualizar(valores);
        this.personaje.imprimirEstado();
        
        proxy.setActivity(resul.toStringEjercitarse());
        proxy.guardar();
    }
    public void enfermarse(String option){
        Enfermedad resul = this.fachada.crearEnfermedad(option);
        HashMap<String,Integer> valores = resul.Enfermarse();
        this.personaje.actualizar(valores);
        this.personaje.imprimirEstado();
        
        proxy.setActivity(resul.toString());
        proxy.guardar();
    }
    public void estrategia(String option){
        IStrategy resul = this.fachada.crearEstrategia(option);
        HashMap<String,Integer> valores = resul.ejecutar();
        this.personaje.actualizar(valores);
        this.personaje.imprimirEstado();
        
        proxy.setActivity(resul.toString());
        proxy.guardar();
    }
    public void habilidad(String option){
        Habilidad resul = this.fachada.crearHabilidad(option);
        HashMap<String,Integer> valores = resul.atacar();
        this.personaje.actualizar(valores);
        this.personaje.imprimirEstado();
        
        proxy.setActivity(resul.toString());
        proxy.guardar();
    }
    public void medicarse(String option){
        Medicamento resul = this.fachada.crearMedicamento(option);
        HashMap<String,Integer> valores = resul.curar();
        this.personaje.actualizar(valores);
        this.personaje.imprimirEstado();
        
        proxy.setActivity(resul.toStringCurar());
        proxy.guardar();
    }
    
    public void iniciar(){
        hilo = new Thread(this);
        estado = true;
        hilo.start();
	setDate(1,1,2019);
    }
    
    public void setDate(int pDay, int pMonth, int pYear){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
        try{
            if(pDay >9 && pMonth>9){
                this.dateInString = "0" + Integer.toString(pDay) + "-" + "0" + Integer.toString(pMonth) + "-" + Integer.toString(pYear);
                Date date = sdf.parse(this.dateInString);
                this.juego.setTiempo(date);
                System.out.println(date);
            }
            if(pDay >9 && pMonth<10){
                this.dateInString = "0" + Integer.toString(pDay) + "-" + Integer.toString(pMonth) + "-" + Integer.toString(pYear);
                Date date = sdf.parse(this.dateInString);
                this.juego.setTiempo(date);
                System.out.println(date);
            }
            if(pDay <10 && pMonth>9){
                this.dateInString = Integer.toString(pDay) + "-" + "0" + Integer.toString(pMonth) + "-" + Integer.toString(pYear);
                Date date = sdf.parse(this.dateInString);
                this.juego.setTiempo(date);
                System.out.println(date);
            }
            if(pDay <10 && pMonth<10){
                this.dateInString = Integer.toString(pDay) + "-" + Integer.toString(pMonth) + "-" + Integer.toString(pYear);
                Date date = sdf.parse(this.dateInString);
                this.juego.setTiempo(date);
                System.out.println(date);
            }    
        } catch(Exception e){
            
        }
    }
    public void addEnfermedades(){
        Enfermedad resul ;
        resul = this.fachada.crearEnfermedad("Depresion");
        this.enfermedades.add(resul);
        resul = this.fachada.crearEnfermedad("Diarrea");
        this.enfermedades.add(resul);
        resul = this.fachada.crearEnfermedad("Fiebre");
        this.enfermedades.add(resul);
        resul = this.fachada.crearEnfermedad("Gripe");
        this.enfermedades.add(resul);
        resul = this.fachada.crearEnfermedad("Obesidad");
        this.enfermedades.add(resul);
        resul = this.fachada.crearEnfermedad("Vomito");
        this.enfermedades.add(resul);
    }
       
    public void run(){
        while(estado == true){
            Calendar fecha = new GregorianCalendar();
            horas = Integer.toString(h);
            minutos = Integer.toString(m);
            segundos = Integer.toString(s);
            s++;
            //System.out.println(s);
            if(s>59){
                s = 0;
                m++;
            }
            if(s<9){
                vista.jlabelSegundos.setText("0" + segundos);
            } else{
                vista.jlabelSegundos.setText(segundos);
            }
            if(m>59){
                m = 0;
                h++;
            }
            if(m<9){
                vista.jlabelMinutos.setText("0" + minutos);
            } else{
                vista.jlabelMinutos.setText(minutos);
            }
            if(h == 23 && m == 45){
                h=0;
                if(monthInt == 2 || monthInt == 4 || monthInt == 6 || monthInt == 9 || monthInt == 11){
                    if(dayInt > 30){
                        dayInt = 1;
                        monthInt++;
                        setDate(dayInt,monthInt,2019);
                    } else{
                        dayInt++;
                        setDate(dayInt,monthInt,2019);
                    }
                } else{
                    if(dayInt > 31){
                        dayInt = 1;
                        monthInt++;
                        setDate(dayInt,monthInt,2019);
                    } else{
                        dayInt++;
                        setDate(dayInt,monthInt,2019);
                    }
                }
                this.proxy.setFilename("dia " + this.dateInString);
            }
            if(h<9){
                vista.jlabelHora.setText("0" + horas);
            } else{
                vista.jlabelHora.setText(horas);
            }
                   
            try{
                Thread.sleep(1);
            } catch(InterruptedException e){
                System.out.println("Error: " + e);
            }
        }
    }
  
    public static void main(String[] args){
        Vista vista = new Vista();
        ControladorJuego c = new ControladorJuego(vista); 
        
    }
}
