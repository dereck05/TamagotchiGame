/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Alimento.Alimento;
import Ejercicios.Ejercicio;
import Enfermedades.Enfermedad;
import Estrategia.IStrategy;
import Factory.SuperFactory;
import Habilidades.Habilidad;
import Medicamentos.Medicamento;
import Model.Ataque;
import Model.Personaje;
import Modelo.Facade;

import Modelo.Juego;
import Modelo.PersonajeGame;
import Modelo.Proxy.Proxy;
import Vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
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
public class ControladorJuego implements ActionListener{
    private Vista vista;
    public  Proxy proxy;
    private PersonajeGame personaje;
    private Juego juego;
    public Facade fachada;
    private ArrayList<Enfermedad> enfermedades;
    private ArrayList<Medicamento> medicamentos;
    private ArrayList<Ejercicio> ejercicios;
    private ArrayList<Alimento> alimentos;
    private ArrayList<Personaje> amigos;
    private ArrayList<PersonajeGame> enemigos;
    
    private boolean estado;
    Thread hiloAlimentos;
    Thread hiloMedicamentos;
    Thread hiloTiempo;
    Thread hiloVerEnfermedad;
    Thread hiloEnfermar;
    Thread hiloSocializar;
    private boolean socializar;
    private String horas,minutos,segundos;
    private int h,m,s;
    private String day,month,year;
    private int dayInt,monthInt,yearInt;
    private static String dateInString;
    
    public ControladorJuego(Vista v){
        this.vista = v;
        this.enfermedades = new ArrayList<>();
        this.ejercicios=new ArrayList<>();
        this.alimentos=new ArrayList<>();
        this.medicamentos=new ArrayList<>();
        this.amigos=new ArrayList<>();
        this.enemigos= new ArrayList<>();
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
        this.addAlimentos();
        this.addEjercicios();
        this.addMedicamentos();
        this.addAmigos();
        this.vista.setVisible(true);
        this.socializar=false;
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
        iniciarTiempo();
        generarMedicamentos();
        generarAlimentos();
        socializar();
        iniciarVerEnfermedad();
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Comer":
                //el get 0 se tiene que cambiar por el alimento seleccionado
                comer(this.vista.txtComer.getText(), alimentos.get(0));
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
    
    public void comer(String option,Alimento a){
        IStrategy resul = this.fachada.crearComer(option,a);
        HashMap<String,Integer> valores = resul.ejecutar();
        this.personaje.actualizar(valores);
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

    public void generarAlimentos(){
        hiloAlimentos = new Thread(){
            @Override
            public void run(){
                int i=0;
                while(estado==true){
                    if(i>=alimentos.size()){
                        i=0;
                    }
                    juego.getHuerto().añadirAlimento(alimentos.get(i));
                    i++;
                    try{
                        sleep(9000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        hiloAlimentos.start();
    }
        public void generarMedicamentos(){
        hiloMedicamentos = new Thread(){
            @Override
            public void run(){
                int i=0;
                while(estado==true){
                    if(i>=medicamentos.size()){
                        i=0;
                    }
                    
                    juego.getHuerto().añadirMedicamento(medicamentos.get(i));
                    i++;
                    try{
                        sleep(9000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        hiloMedicamentos.start();
    }
    public void socializar(){
        hiloSocializar = new Thread(){
            @Override
            public void run(){
                while(estado==true){
                    try {
                        sleep(31000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(!socializar){
                        int input = JOptionPane.showConfirmDialog(null, "¿Desea socilizar?");
                        // 0=yes, 1=no, 2=cancel
                        if(input==0){
                            int i= (int)Math.floor(Math.random()*amigos.size()+1);
                            personaje.setAmigoActual(amigos.get(i));
                            estrategia("Socializar");
                            socializar=true;
                        }
                        
                    }

                }
            }
        };
        hiloSocializar.start();
    }
    public void iniciarTiempo(){
        hiloTiempo = new Thread(){
            
            @Override
            public void run(){
                while(estado == true){
                    Calendar fecha = new GregorianCalendar();
                    horas = Integer.toString(h);
                    minutos = Integer.toString(m);
                    segundos = Integer.toString(s);
                    s++;
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
                        proxy.setFilename("dia " + dateInString);
                        socializar=false;
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
        };
        estado = true;
        hiloTiempo.start();
	setDate(1,1,2019);
    }
    public void iniciarVerEnfermedad(){
        hiloVerEnfermedad = new Thread(){
            @Override
            public void run(){
                while(true){
                    for(Enfermedad e : enfermedades){
                        HashMap<String,Integer> val = e.generarPorcentajeAparacion();
                        if(val.containsKey("Musculo")){
                            int num = val.get("Musculo");
//                            System.out.println("NUM1: "+num);
//                            System.out.println("NUM2: "+personaje.getApariencia().getMusculo());
                            if(personaje.getApariencia().getMusculo()< num){
                                try {
                                    iniciarEnfermar(e);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                        }
                        if(val.containsKey("Grasa")){
                            int num = val.get("Grasa");
                            if(personaje.getApariencia().getGrasa()< num){
                                try {
                                    iniciarEnfermar(e);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        if(val.containsKey("Fuerza")){
                            int num = val.get("Fuerza");
                            if(personaje.getApariencia().getFuerza()< num){
                                try {
                                    iniciarEnfermar(e);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        if(val.containsKey("Estatura")){
                            int num = val.get("Estatura");
                            if(personaje.getApariencia().getEstatura()< num){
                                try {
                                    iniciarEnfermar(e);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        if(val.containsKey("Rapidez")){
                            int num = val.get("Rapidez");
                            if(personaje.getApariencia().getRapidez()< num){
                                try {
                                    iniciarEnfermar(e);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        if(val.containsKey("Esfuerzo")){
                            int num = val.get("Esfuerzo");
                            if(personaje.getApariencia().getEsfuerzo()< num){
                                try {
                                    iniciarEnfermar(e);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        if(val.containsKey("Energia")){
                            int num = val.get("Energia");
                            if(personaje.getEstado().getEnergia()< num){
                                try {
                                    iniciarEnfermar(e);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        if(val.containsKey("Salud fisica")){
                            int num = val.get("Salud fisica");
                            if(personaje.getEstado().getSaludFisica()< num){
                                try {
                                    iniciarEnfermar(e);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        if(val.containsKey("Salud mental")){
                            int num = val.get("Salud mental");
                            if(personaje.getEstado().getSaludMental()< num){
                                try {
                                    iniciarEnfermar(e);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        if(val.containsKey("comida injerida")){
                            int num = val.get("comida injerida");
                            if(personaje.getEstado().getComidaIngerida()< num){
                                try {
                                    iniciarEnfermar(e);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        if(val.containsKey("Liquidos")){
                            int num = val.get("Liquidos");
                            if(personaje.getEstado().getLiquidos()< num){
                                try {
                                    iniciarEnfermar(e);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        if(val.containsKey("Alegria")){
                            int num = val.get("Alegria");
                            if(personaje.getEstado().getAlegria()< num){
                                try {
                                    iniciarEnfermar(e);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }
                }
                
            }
        };
        hiloVerEnfermedad.start();
                
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
    
    public void iniciarEnfermar(Enfermedad e) throws InterruptedException{
        hiloEnfermar = new Thread(){
            @Override
            public void run(){
                personaje.actualizar(e.Enfermarse());
                personaje.imprimirEstado();
            }
        };
        hiloEnfermar.start();
        Thread.sleep(30000);
                
        
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
    public void addAmigos(){
        Model.SuperFactory sp =new Model.SuperFactory();
        ArrayList<Ataque> n = new ArrayList<>();
        Personaje resul;
        resul=sp.createPersonaje(true, "Bichita1", "", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, n, null);
        this.amigos.add(resul);
        resul=sp.createPersonaje(true, "Bichita2", "", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, n, null);
        this.amigos.add(resul);
        resul=sp.createPersonaje(true, "Bichita3", "", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, n, null);
        this.amigos.add(resul);
        resul=sp.createPersonaje(true, "Bichita4", "", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, n, null);
        this.amigos.add(resul);
        resul=sp.createPersonaje(true, "Bichita5", "", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, n, null);
        this.amigos.add(resul);
        resul=sp.createPersonaje(true, "Bichita6", "", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, n, null);
        this.amigos.add(resul);
        resul=sp.createPersonaje(true, "Bichita7", "", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, n, null);
        this.amigos.add(resul);
        resul=sp.createPersonaje(true, "Bichita8", "", 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, n, null);
        this.amigos.add(resul);
        
        
    }
    public void addMedicamentos(){
        Medicamento resul;
        resul = this.fachada.crearMedicamento("Ibuprofeno");
        this.medicamentos.add(resul);
        resul=this.fachada.crearMedicamento("Lexapro");
        this.medicamentos.add(resul);
        resul= this.fachada.crearMedicamento("Loperamida");
        this.medicamentos.add(resul);
        resul=this.fachada.crearMedicamento("Mucosan");
        this.medicamentos.add(resul);
        resul=this.fachada.crearMedicamento("Panadol");
        this.medicamentos.add(resul);
        resul=this.fachada.crearMedicamento("PeptoBismol");
        this.medicamentos.add(resul);
    }
    public void addEjercicios(){
        Ejercicio resul;
        resul = this.fachada.crearEjercicio("Atletismo");
        this.ejercicios.add(resul);
        resul=this.fachada.crearEjercicio("Boxeo");
        this.ejercicios.add(resul);
        resul=this.fachada.crearEjercicio("Ciclismo");
        this.ejercicios.add(resul);
        resul=this.fachada.crearEjercicio("Danza");
        this.ejercicios.add(resul);
        resul=this.fachada.crearEjercicio("Futbol");
        this.ejercicios.add(resul);
        resul=this.fachada.crearEjercicio("Gimnasia");
        this.ejercicios.add(resul);
        resul=this.fachada.crearEjercicio("Judo");
        this.ejercicios.add(resul);
        resul=this.fachada.crearEjercicio("Karate");
        this.ejercicios.add(resul);
        resul=this.fachada.crearEjercicio("Natacion");
        this.ejercicios.add(resul);
        resul=this.fachada.crearEjercicio("Yoga");
        this.ejercicios.add(resul);
    }
    public void addAlimentos(){
        Alimento resul;
        resul=this.fachada.crearAlimento("Pan", 15);
        this.alimentos.add(resul);
        resul=this.fachada.crearAlimento("Cafe", 20);
        this.alimentos.add(resul);
        resul=this.fachada.crearAlimento("Arroz", 25);
        this.alimentos.add(resul);
        resul=this.fachada.crearAlimento("Frijoles", 15);
        this.alimentos.add(resul);
        resul=this.fachada.crearAlimento("Atún", 10);
        this.alimentos.add(resul);
        resul=this.fachada.crearAlimento("Pollo", 15);
        this.alimentos.add(resul);
        resul=this.fachada.crearAlimento("Res", 20);
        this.alimentos.add(resul);
        resul=this.fachada.crearAlimento("Cerdo",18);
        this.alimentos.add(resul);
        resul=this.fachada.crearAlimento("Jugo de naranja", 19);
        this.alimentos.add(resul);
        resul=this.fachada.crearAlimento("Fideos", 16);
        this.alimentos.add(resul);
        resul=this.fachada.crearAlimento("Maiz dulce", 8);
        this.alimentos.add(resul);
        resul=this.fachada.crearAlimento("Coca cola", 15);
        this.alimentos.add(resul);
        resul=this.fachada.crearAlimento("Galletas", 14);
        this.alimentos.add(resul);
        resul=this.fachada.crearAlimento("Cereal", 20);
        this.alimentos.add(resul);
        resul=this.fachada.crearAlimento("Agua", 15);
        this.alimentos.add(resul);
    }   
    
  
    public static void main(String[] args){
        Vista vista = new Vista();
        ControladorJuego c = new ControladorJuego(vista); 
        
    }
}
