/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Alimento.Alimento;
import static Controlador.ControladorVentanaPrincipal.vp;
import Ejercicios.Ejercicio;
import Enfermedades.Enfermedad;
import Estrategia.IStrategy;
import Factory.SuperFactory;
import Habilidades.Habilidad;
import Medicamentos.ICura;
import Medicamentos.Medicamento;
import Model.Ataque;
import Model.Personaje;
import Modelo.Apariencia;
import Modelo.Estado;
import Modelo.Estado.Humor;
import Modelo.Facade;

import Modelo.Juego;
import Modelo.PersonajeGame;
import Modelo.Proxy.Proxy;
import Vista.VentanaEscogerHabilidades;
import Vista.VentanaPelear;
import Vista.VentanaPrincipal;
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
import java.util.concurrent.TimeUnit;
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
  //  private PersonajeGame personaje;
    public static Juego juego;
    public Facade fachada;
    private ArrayList<Enfermedad> enfermedades;
    private ArrayList<Enfermedad> enfermedadesActivas;
    private ArrayList<Medicamento> medicamentos;
    private ArrayList<Ejercicio> ejercicios;
    private ArrayList<Alimento> alimentos;
    private ArrayList<Personaje> amigos;
    private ArrayList<PersonajeGame> enemigos;
    private ArrayList<Habilidad> habilidades;
    private int dia;
    private boolean estado;
    private Thread hiloAlimentos;
    private Thread hiloMedicamentos;
    private Thread hiloTiempo;
    private Thread hiloVerEnfermedad;
    private Thread hiloEnfermar;
    private Thread hiloSocializar;
    private Thread hiloPelear;
    private boolean socializar;
    private boolean pelear;
    private String horas,minutos,segundos;
    private int h,m,s;
    private String day,month,year;
    private int dayInt,monthInt,yearInt;
    private static String dateInString;
    
    public ControladorJuego() throws InterruptedException{
        this.enfermedades = new ArrayList<>();
        this.enfermedadesActivas = new ArrayList<>();
        this.ejercicios=new ArrayList<>();
        this.alimentos=new ArrayList<>();
        this.medicamentos=new ArrayList<>();
        this.amigos=new ArrayList<>();
        this.enemigos= new ArrayList<>();
        this.habilidades= new ArrayList<>();
        this.dia=1;
    //    this.vista.btnComer.addActionListener(this);
     //   this.vista.btnEjercicio.addActionListener(this);
     //   this.vista.btnEnfermar.addActionListener(this);
     //   this.vista.btnHabilidad.addActionListener(this);
     //   this.vista.btnMedicina.addActionListener(this);
        this.juego = new Juego();
        this.fachada = new Facade();
        this.proxy = new Proxy();
        
        this.juego.getPersonaje().inicializar();
        this.juego.getPersonaje().imprimirEstado();
        this.addEnfermedades();
        this.addAlimentos();
        this.addEjercicios();
        this.addMedicamentos();
        this.addAmigos();
        this.addHabilidad();
        
        
//        this.vista.setVisible(true);
        this.socializar=false;
        this.pelear=false;
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
        pelearH(this);
        iniciarEnfermar();
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Comer":
                //el get 0 se tiene que cambiar por el alimento seleccionado
                comer(this.vista.txtComer.getText(), alimentos.get(0));
                break;
            case "Ejercitarse":
                //ejercitarse(this.vista.txtEjercicio.getText());
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
             //   medicarse(this.vista.txtMedicina.getText());
                break;
            default:
                JOptionPane.showMessageDialog(vista, "Opción no registrada");
                break;
        }
    }

    public void pelear(ArrayList<Habilidad> habilidadesEscogidas, int contador){

            this.juego.getPersonaje().getEnemigoActual().actualizar(habilidadesEscogidas.get(contador).atacar());
            this.juego.getPersonaje().actualizar(this.juego.getPersonaje().getEnemigoActual().getAtaque().get(contador).atacar());
            
            actualizarPorcentajes("Estoy peleando");

    }
    public void comer(String option,Alimento a){
        IStrategy resul = this.fachada.crearComer(option,a);
        HashMap<String,Integer> valores = resul.ejecutar();
        this.juego.getPersonaje().actualizar(valores);
        proxy.setActivity(resul.toString());
        proxy.guardar();
        //return resul;
    }
    public void ejercitarse(Ejercicio ejercicio){
       // Ejercicio resul = this.fachada.crearEjercicio(option);
        HashMap<String,Integer> valores = ejercicio.ejercitarse();
        this.juego.getPersonaje().actualizar(valores);
        this.juego.getPersonaje().addHabilidad(ejercicio.obtenerHabilidadGenerada());
        this.juego.getPersonaje().imprimirEstado();
        proxy.setActivity(ejercicio.toStringEjercitarse());
        proxy.guardar();
    }
    public void enfermarse(String option){
        Enfermedad resul = this.fachada.crearEnfermedad(option);
        HashMap<String,Integer> valores = resul.Enfermarse();
        this.juego.getPersonaje().actualizar(valores);
        this.juego.getPersonaje().imprimirEstado();
        
        proxy.setActivity(resul.toString());
        proxy.guardar();
    }
    public void estrategia(String option){
        System.out.println(option);
        IStrategy resul = this.fachada.crearEstrategia(option);
        HashMap<String,Integer> valores = resul.ejecutar();
        this.juego.getPersonaje().actualizar(valores);
        this.juego.getPersonaje().imprimirEstado();
        
        proxy.setActivity(resul.toString());
        proxy.guardar();
    }
    public void actualizarPorcentajes(String mensaje){
       vp.lblAlegria.setText(Integer.toString(getPersonaje().getEstado().getAlegria()));
       vp.lblEnergia.setText(Integer.toString(getPersonaje().getEstado().getEnergia()));
       vp.lblSaludMental.setText(Integer.toString(getPersonaje().getEstado().getSaludMental()));
       vp.lblSaludFisica.setText(Integer.toString(getPersonaje().getEstado().getSaludFisica()));
       vp.lblComida.setText(Integer.toString(this.getPersonaje().getEstado().getComida()));
       vp.lblLiquidos.setText(Integer.toString(this.getPersonaje().getEstado().getLiquidos()));
       vp.lblMusculo.setText(Integer.toString(this.getPersonaje().getApariencia().getMusculo()));
       vp.lblGrasa.setText(Integer.toString(this.getPersonaje().getApariencia().getGrasa()));
       vp.lblFuerza.setText(Integer.toString(this.getPersonaje().getApariencia().getFuerza()));
       vp.lblEstatura.setText(Integer.toString(this.getPersonaje().getApariencia().getEstatura()));
       vp.lblRapidez.setText(Integer.toString(this.getPersonaje().getApariencia().getRapidez()));
       vp.lblEsfuerzo.setText(Integer.toString(this.getPersonaje().getApariencia().getEsfuerzo()));
       
       vp.lblAuxiliar.setText(mensaje);
    }
    public void habilidad(String option){
        Habilidad resul = this.fachada.crearHabilidad(option);
        HashMap<String,Integer> valores = resul.atacar();
        this.juego.getPersonaje().actualizar(valores);
        this.juego.getPersonaje().imprimirEstado();
        
        proxy.setActivity(resul.toString());
        proxy.guardar();
    }
    public void medicarse(ICura cura){
      //  Medicamento resul = this.fachada.crearMedicamento(option);
      //  HashMap<String,Integer> valores = resul.curar();
        this.juego.getPersonaje().actualizar(cura.curar());
        this.juego.getPersonaje().imprimirEstado();
        
        proxy.setActivity(cura.toStringCurar());
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
                   // juego.getBodega().añadirAlimento(alimentos.get(i));
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
    public void pelearH(ControladorJuego controlador){
        
        hiloPelear = new Thread(){
            @Override
            public void run(){

                while(estado==true){
                    try {
                        sleep(40000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(!pelear){
                        int input = JOptionPane.showConfirmDialog(null, "¿Desea aceptar la pelea?");
                        // 0=yes, 1=no, 2=cancel
                        if(input==0){
                            
                            //Va al lugar de peleas
                            ControladorVentanaPrincipal.vp.goTo("Peleas");
                            try {
                                TimeUnit.MILLISECONDS.sleep(500);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            
                            
                            addEnemigos();
                            int i= (int)Math.floor(Math.random()*enemigos.size());
                            juego.getPersonaje().setEnemigoActual(enemigos.get(i));
                            VentanaEscogerHabilidades ventana = new VentanaEscogerHabilidades();
                            ControladorEscogerHabilidades cea = new ControladorEscogerHabilidades(controlador,juego.getPersonaje().getEnemigoActual().getAtaque().size(),ventana);
                            
                                
        // System.out.println(controlador.getJuego().getPersonaje().getApariencia().getEsfuerzo());
//pelear();
                            pelear=true;

                        }
                        
                    }

                }
            }
        };
        hiloPelear.start();
    }
    public void socializar(){
        hiloSocializar = new Thread(){
            @Override
            public void run(){
                while(estado==true){
                    try {
                        sleep(60000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(!socializar){
                        int input = JOptionPane.showConfirmDialog(null, "¿Desea socilizar?");
                        // 0=yes, 1=no, 2=cancel
                        if(input==0){
                            int i= (int)Math.floor(Math.random()*amigos.size());
                            juego.getPersonaje().setAmigoActual(amigos.get(i));
                            estrategia("Socializar");
                            actualizarPorcentajes("Estoy socializando con: "+amigos.get(i).getNombre());
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
                    ControladorVentanaPrincipal.vp.lblDia.setText(Integer.toString(dia));
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
                        ControladorVentanaPrincipal.vp.lblSegundos.setText("0" + segundos);
                    } else{
                        ControladorVentanaPrincipal.vp.lblSegundos.setText(segundos);
                    }
                    if(m>59){
                        m = 0;
                        h++;
                    }
                    if(m<9){
                        ControladorVentanaPrincipal.vp.lblMinutos.setText("0" + minutos);
                    } else{
                        ControladorVentanaPrincipal.vp.lblMinutos.setText(minutos);
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
                        dia = dia+1;
                        if(dia%2==0){
                            int edad= juego.getPersonaje().getEdad();
                            juego.getPersonaje().setEdad(edad+1);
                            actualizarPorcentajes("Cumplí "+edad+1+" años");
                        }
                        socializar=false;
                    }
                    if(h<9){
                        ControladorVentanaPrincipal.vp.lblHoras.setText("0" + horas);
                    } else{
                        ControladorVentanaPrincipal.vp.lblHoras.setText(horas);
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
    public int preguntarEnfermedad(String nombre){
        int input = JOptionPane.showConfirmDialog(null, "¿Acepta la enfermedad: "+nombre+"?");
        return input;
    }
    public void iniciarVerEnfermedad(){
        Runnable runnable = new Runnable(){
            @Override
            public void run(){
                while(true){
                    boolean enfermo= false;
                    for(Enfermedad e : enfermedades){
                        HashMap<String,Integer> val = e.generarPorcentajeAparacion();
                        
                        if(val.containsKey("Musculo")){
                            int num = val.get("Musculo");
                            if(juego.getPersonaje().getApariencia().getMusculo()<= num){  
                                enfermo=true;
                               // personaje.getEnfermedadesActivas().add(e);                               
                            }

                        }
                        if(val.containsKey("Grasa")){
                            int num = val.get("Grasa");
                            if(juego.getPersonaje().getApariencia().getGrasa()>= num){
                                enfermo=true;
                                //personaje.getEnfermedadesActivas().add(e); 
                            }
                        }
                        if(val.containsKey("Fuerza")){
                            int num = val.get("Fuerza");
                            if(juego.getPersonaje().getApariencia().getFuerza()<= num){
                                enfermo=true;
                               // personaje.getEnfermedadesActivas().add(e); 
                            }
                        }
                        if(val.containsKey("Estatura")){
                            int num = val.get("Estatura");
                            if(juego.getPersonaje().getApariencia().getEstatura()<= num){
                                enfermo=true;
                                //personaje.getEnfermedadesActivas().add(e); 
                            }
                        }
                        if(val.containsKey("Rapidez")){
                            int num = val.get("Rapidez");
                            if(juego.getPersonaje().getApariencia().getRapidez()<= num){
                                enfermo=true;
                                //personaje.getEnfermedadesActivas().add(e); 
                            }
                        }
                        if(val.containsKey("Esfuerzo")){
                            int num = val.get("Esfuerzo");
                            if(juego.getPersonaje().getApariencia().getEsfuerzo()>= num){
                                enfermo=true;
                              //  personaje.getEnfermedadesActivas().add(e); 
                            }
                        }
                        if(val.containsKey("Energia")){
                            int num = val.get("Energia");
                            if(juego.getPersonaje().getEstado().getEnergia()<= num){
                                enfermo=true;
                              //  personaje.getEnfermedadesActivas().add(e); 
                            }
                        }
                        if(val.containsKey("Salud fisica")){
                            int num = val.get("Salud fisica");
                            if(juego.getPersonaje().getEstado().getSaludFisica()<= num){
                                enfermo=true;
                                //personaje.getEnfermedadesActivas().add(e); 
                            }
                        }
                        if(val.containsKey("Salud mental")){
                            int num = val.get("Salud mental");
                            if(juego.getPersonaje().getEstado().getSaludMental()<= num){
                                enfermo=true;
                               // personaje.getEnfermedadesActivas().add(e); 
                            }
                        }
                        if(val.containsKey("Comida injerida")){
                            int num = val.get("Comida injerida");
                            System.out.println(num);
                            System.out.println(juego.getPersonaje().getEstado().getComidaIngerida());
                            System.out.println(juego.getPersonaje().getEstado().getComidaIngerida()>= num);
                            if(juego.getPersonaje().getEstado().getComidaIngerida()>= num){
                                enfermo=true;
                              //  personaje.getEnfermedadesActivas().add(e); 
                            }
                        }
                        if(val.containsKey("Liquidos")){
                            System.out.println("Liquidos");
                            int num = val.get("Liquidos");
                            if(juego.getPersonaje().getEstado().getLiquidos()>= num){
                                enfermo=true;
                              //  personaje.getEnfermedadesActivas().add(e); 
                            }
                        }
                        if(val.containsKey("Alegria")){
                            int num = val.get("Alegria");
                            if(juego.getPersonaje().getEstado().getAlegria()<= num){
                                enfermo=true;
                               // personaje.getEnfermedadesActivas().add(e); 
                            }
                        }
                        if(enfermo){
                            if(!juego.getPersonaje().getEnfermedadesActivas().containsKey(e)){
                            int resul=preguntarEnfermedad(e.getNombre());
                            enfermo=false;
                            
                            if(resul==0){
                                juego.getPersonaje().getEnfermedadesActivas().put(e,dia);
                                ControladorVentanaPrincipal.vp.btnCurarEnfermedad.setVisible(true);
                                ControladorVentanaPrincipal.vp.btnCurarEnfermedad.setEnabled(true);
                            }
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }}
                    }
                }
                
            }
        };
        hiloVerEnfermedad = new Thread(runnable);
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

    public PersonajeGame getPersonaje() {
        return juego.getPersonaje();
    }

    public ArrayList<Enfermedad> getEnfermedades() {
        return enfermedades;
    }

    public void setEnfermedades(ArrayList<Enfermedad> enfermedades) {
        this.enfermedades = enfermedades;
    }

    public ArrayList<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(ArrayList<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public ArrayList<Ejercicio> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(ArrayList<Ejercicio> ejercicios) {
        this.ejercicios = ejercicios;
    }

    public ArrayList<Alimento> getAlimentos() {
        return alimentos;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public void setAlimentos(ArrayList<Alimento> alimentos) {
        this.alimentos = alimentos;
    }

    public ArrayList<Personaje> getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList<Personaje> amigos) {
        this.amigos = amigos;
    }

    public ArrayList<PersonajeGame> getEnemigos() {
        return enemigos;
    }

    public void setEnemigos(ArrayList<PersonajeGame> enemigos) {
        this.enemigos = enemigos;
    }

    public ArrayList<Habilidad> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(ArrayList<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }


    
    public void iniciarEnfermar() throws InterruptedException{
        Runnable runnable = new Runnable(){
            @Override
            public void run(){
                while(true){
                    try {
                        sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ControladorJuego.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for(HashMap.Entry<Enfermedad,Integer> entry: juego.getPersonaje().getEnfermedadesActivas().entrySet())
                    {
                        //System.out.println("Enfermo: "+juego.getPersonaje().getEnfermedadesActivas().get(i).getNombre());
                        juego.getPersonaje().actualizar(entry.getKey().Enfermarse());
                        juego.getPersonaje().imprimirEstado();
                        if(Math.abs(entry.getValue()-dia)>=3){
                            JOptionPane.showMessageDialog(null, "Usted ha muerto");
                            ControladorVentanaPrincipal.vp.setVisible(false);
    
                        }
                    }
                }
                
               
            }
        };
        hiloEnfermar = new Thread(runnable);
        hiloEnfermar.start();
        
                
        
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
    public void addEnemigos(){
        ArrayList<Habilidad> habilidadesEnemigo;
        Apariencia apariencia;
        Estado estado1;
        PersonajeGame personajeGame;
        apariencia = new Apariencia((int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1));
        estado1 = new Estado(Humor.FELIZ,(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1));
        habilidadesEnemigo = generarHabilidades();
        personajeGame = new PersonajeGame("Juanito8","",habilidadesEnemigo,estado1,apariencia);
        this.enemigos.add(personajeGame);
        apariencia = new Apariencia((int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1));
        estado1 = new Estado(Humor.FELIZ,(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1));
        habilidadesEnemigo = generarHabilidades();
        personajeGame = new PersonajeGame("Juanito7","",habilidadesEnemigo,estado1,apariencia);
        this.enemigos.add(personajeGame);
        apariencia = new Apariencia((int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1));
        estado1 = new Estado(Humor.FELIZ,(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1));
        habilidadesEnemigo = generarHabilidades();
        personajeGame = new PersonajeGame("Juanito6","",habilidadesEnemigo,estado1,apariencia);
        this.enemigos.add(personajeGame);
        apariencia = new Apariencia((int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1));
        estado1 = new Estado(Humor.FELIZ,(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1));
        habilidadesEnemigo = generarHabilidades();
        personajeGame = new PersonajeGame("Juanito5","",habilidadesEnemigo,estado1,apariencia);
        this.enemigos.add(personajeGame);
        apariencia = new Apariencia((int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1));
        estado1 = new Estado(Humor.FELIZ,(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1));
        habilidadesEnemigo = generarHabilidades();
        personajeGame = new PersonajeGame("Juanito4","",habilidadesEnemigo,estado1,apariencia);
        this.enemigos.add(personajeGame);
        apariencia = new Apariencia((int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1));
        estado1 = new Estado(Humor.FELIZ,(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1));
        habilidadesEnemigo = generarHabilidades();
        personajeGame = new PersonajeGame("Juanito3","",habilidadesEnemigo,estado1,apariencia);
        this.enemigos.add(personajeGame);
        apariencia = new Apariencia((int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1));
        estado1 = new Estado(Humor.FELIZ,(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1));
        habilidadesEnemigo = generarHabilidades();
        personajeGame = new PersonajeGame("Juanito2","",habilidadesEnemigo,estado1,apariencia);
        this.enemigos.add(personajeGame);
        apariencia = new Apariencia((int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1));
        estado1 = new Estado(Humor.FELIZ,(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1),(int)Math.floor(Math.random()*100+1));
        habilidadesEnemigo = generarHabilidades();
        personajeGame = new PersonajeGame("Juanito1","",habilidadesEnemigo,estado1,apariencia);
        this.enemigos.add(personajeGame);
    }
    public ArrayList<Habilidad> generarHabilidades(){
        ArrayList<Habilidad> habilidadesEnemigo = new ArrayList<>();
        int numHabilidades= (int)Math.floor(Math.random()*juego.getPersonaje().getAtaque().size()+1);
        System.out.println(numHabilidades);
        int hab;
        for (int i=0; i<numHabilidades;i++){
            hab = (int)Math.floor(Math.random()*habilidades.size());
            habilidadesEnemigo.add(habilidades.get(hab));
        }
        return habilidadesEnemigo;
    }
    public void addHabilidad(){
        Habilidad resul;
        resul = this.fachada.crearHabilidad("AlaDeAcero");
        this.habilidades.add(resul);
        resul = this.fachada.crearHabilidad("Electrocañon");
        this.habilidades.add(resul);
        resul = this.fachada.crearHabilidad("Golpe");
        this.habilidades.add(resul);
        resul = this.fachada.crearHabilidad("Llave");
        this.habilidades.add(resul);
        resul = this.fachada.crearHabilidad("Martillazo");
        this.habilidades.add(resul);
        resul = this.fachada.crearHabilidad("OjoLaser");
        this.habilidades.add(resul);
        resul = this.fachada.crearHabilidad("Patada");
        this.habilidades.add(resul);
        resul = this.fachada.crearHabilidad("SuperVelocidad");
        this.habilidades.add(resul);
        resul = this.fachada.crearHabilidad("Trueno");
        this.habilidades.add(resul);
        resul = this.fachada.crearHabilidad("Zumbidos");
        this.habilidades.add(resul);
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
    
  
    public static void main(String[] args) throws InterruptedException{
        ControladorVentanaPrincipal.vp = new VentanaPrincipal();
        
        ControladorJuego c = new ControladorJuego(); 
        ControladorVentanaPrincipal cvp = new ControladorVentanaPrincipal(c,juego.getPersonaje());
        ControladorVentanaPrincipal.vp.setVisible(true);
        
    }
}
