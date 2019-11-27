/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Ejercicios.Ejercicio;
import Estrategia.IStrategy;
import Modelo.Facade;
import Modelo.PersonajeGame;
import Vista.VentanaBaño;
import Vista.VentanaBodega;
import Vista.VentanaComedor;
import Vista.VentanaCurar;
import Vista.VentanaGimnasio;
import Vista.VentanaHuerto;
import Vista.VentanaPrincipal;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.ImageIcon;

/**
 *
 * @author maryp
 */
public class ControladorVentanaPrincipal implements ActionListener, MouseListener {
    public static VentanaPrincipal vp;
    private ControladorJuego controlador;
    PersonajeGame personaje;
//    Facade f;
    

    Thread th;
    boolean flag = true;
    
    public ControladorVentanaPrincipal(ControladorJuego controlador,PersonajeGame p) {
        this.personaje = p;
        this.controlador = controlador;
        this.vp.btnOpciones.addActionListener(this);
        this.vp.btnCurarEnfermedad.addActionListener(this);
        this.vp.btnCurarEnfermedad.setVisible(false);
//        f = new Facade();
//        Ejercicio map = f.crearEjercicio("Boxeo");
//        HashMap<String,Integer> m = map.ejercitarse();
//        p.actualizar(m);
        inicializar();
        
        
        
        pintarBolita();
        agregarListeners();
        
    }
    
    public void inicializar(){
       vp.lblAlegria.setText(Integer.toString(this.controlador.getPersonaje().getEstado().getAlegria()));
       vp.lblEnergia.setText(Integer.toString(this.controlador.getPersonaje().getEstado().getEnergia()));
       vp.lblSaludMental.setText(Integer.toString(this.controlador.getPersonaje().getEstado().getSaludMental()));
       vp.lblSaludFisica.setText(Integer.toString(this.controlador.getPersonaje().getEstado().getSaludFisica()));
       vp.lblComida.setText(Integer.toString(this.controlador.getPersonaje().getEstado().getComida()));
       vp.lblLiquidos.setText(Integer.toString(this.controlador.getPersonaje().getEstado().getLiquidos()));
       vp.lblMusculo.setText(Integer.toString(this.controlador.getPersonaje().getApariencia().getMusculo()));
       vp.lblGrasa.setText(Integer.toString(this.controlador.getPersonaje().getApariencia().getGrasa()));
       vp.lblFuerza.setText(Integer.toString(this.controlador.getPersonaje().getApariencia().getFuerza()));
       vp.lblEstatura.setText(Integer.toString(this.controlador.getPersonaje().getApariencia().getEstatura()));
       vp.lblRapidez.setText(Integer.toString(this.controlador.getPersonaje().getApariencia().getRapidez()));
       vp.lblEsfuerzo.setText(Integer.toString(this.controlador.getPersonaje().getApariencia().getEsfuerzo()));
       vp.lblAuxiliar.setText("Sin moverme");
       vp.lblEdad.setText(Integer.toString(this.controlador.getPersonaje().getEdad()));
       //FALTA AGREGAR LA IMAGEN
                
    }
    
    public void pintarBolita(){
        th = new Thread(){
            @Override
            public void run(){
                while(true){
                    //houseClickCounter++;
                    Graphics g = vp.panelImagen.getGraphics();
                    vp.ball.draw(g);
                    vp.repaint();
                }         
            }
        };
        th.start();
    }
    
    public void agregarListeners(){
        
        //Listener cuarto para dormir
        vp.lblCuarto.addMouseListener(new MouseAdapter(){  
        public void mouseClicked(MouseEvent e){  
           vp.goTo("Cuarto");
           dormir();
        }  
        }); 
        
        vp.lblBano.addMouseListener(new MouseAdapter(){  
        public void mouseClicked(MouseEvent e){  
           vp.goTo("Baño");
           usarBaño();
        }  
        }); 
        
        vp.lblBodega.addMouseListener(new MouseAdapter(){  
        public void mouseClicked(MouseEvent e){  
           vp.goTo("Bodega");
           bodega();
        }  
        });
        
        vp.lblGimnasio.addMouseListener(new MouseAdapter(){  
        public void mouseClicked(MouseEvent e){  
           vp.goTo("Gimnasio");
           ejercitarse();
        }  
        }); 
        
        vp.lblPiscina.addMouseListener(new MouseAdapter(){  
        public void mouseClicked(MouseEvent e){  
           vp.goTo("Piscina");
        }  
        });
        vp.lblJardin.addMouseListener(new MouseAdapter(){  
        public void mouseClicked(MouseEvent e){  
           vp.goTo("Jardin");
           meditar();
        }  
        });
    }
    
    
    //Llamar cuando toca el jardin
    public void meditar(){
        controlador.estrategia("Meditar");
        controlador.actualizarPorcentajes("Estoy meditando");
        ImageIcon imageIcon = new javax.swing.ImageIcon(getClass().getResource("/Vista/meditar.jpg"));
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        vp.lblPersonaje.setIcon(imageIcon);
    }
    //Llamar cuando toca el cuarto
    public void dormir(){
        
        controlador.estrategia("Dormir");
        controlador.actualizarPorcentajes("Estoy durmiendo");
        ImageIcon imageIcon = new javax.swing.ImageIcon(getClass().getResource("/Vista/dormir.jpg"));
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        vp.lblPersonaje.setIcon(imageIcon);
        
    }
    //Llamar cuando usa el baño
    public void usarBaño(){
        VentanaBaño ventana = new VentanaBaño();
        ControladorVentanaBaño cvb = new ControladorVentanaBaño(ventana,controlador,vp);
    }
    //Llamar cuando va al gimnasio
    public void ejercitarse(){
        VentanaGimnasio ventana = new VentanaGimnasio();
        ControladorGimnasio cg = new ControladorGimnasio(ventana,controlador,vp);
        ImageIcon imageIcon = new javax.swing.ImageIcon(getClass().getResource("/Vista/ejercitarse.gif"));

        vp.lblPersonaje.setIcon(imageIcon);
    }
    //Llamar cuando va al huerto
    public void recolectar(){
        VentanaHuerto ventana = new VentanaHuerto();
        ControladorHuerto cg = new ControladorHuerto(ventana,controlador,vp);
    }
    //Llamar cuando va al comedor
    public void comer(){
        VentanaComedor ventana = new VentanaComedor();
        ControladorComedor cg = new ControladorComedor(ventana,controlador,vp);
        ImageIcon imageIcon = new javax.swing.ImageIcon(getClass().getResource("/Vista/comer.gif"));

        vp.lblPersonaje.setIcon(imageIcon);
    }
    //Llamar cuando va a la bodega
    public void bodega(){
        VentanaBodega ventana = new VentanaBodega();
        ControladorBodega cb = new ControladorBodega(controlador,ventana,vp);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(vp.btnOpciones)){
           
        }
        else if(e.getSource().equals(vp.btnCurarEnfermedad)){
            VentanaCurar vc = new VentanaCurar();
            ControladorCurar cc = new ControladorCurar(vc, controlador);
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
