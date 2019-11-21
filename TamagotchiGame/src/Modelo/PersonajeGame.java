/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


public class PersonajeGame extends Personaje  {
    private int edad;
    private ArrayList<IStrategy> comportamientos;
    private ArrayList<AbstractAtaque> ataques;
    private Estado estado;
    private Apariencia apariencia;
    private String tipo;
    private AbstractEnfermedad enfermedadActual;

    public int getEdad() {
        return edad;
    }

    public PersonajeGame(int edad, ArrayList<IStrategy> comportamientos, ArrayList<AbstractAtaque> ataques, Estado estado, Apariencia apariencia, String tipo, AbstractEnfermedad enfermedadActual) {
        this.edad = edad;
        this.comportamientos = comportamientos;
        this.ataques = ataques;
        this.estado = estado;
        this.apariencia = apariencia;
        this.tipo = tipo;
        this.enfermedadActual = enfermedadActual;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ArrayList<IStrategy> getComportamientos() {
        return comportamientos;
    }

    public void setComportamientos(ArrayList<IStrategy> comportamientos) {
        this.comportamientos = comportamientos;
    }

    public ArrayList<AbstractAtaque> getAtaques() {
        return ataques;
    }

    public void setAtaques(ArrayList<AbstractAtaque> ataques) {
        this.ataques = ataques;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Apariencia getApariencia() {
        return apariencia;
    }

    public void setApariencia(Apariencia apariencia) {
        this.apariencia = apariencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public AbstractEnfermedad getEnfermedadActual() {
        return enfermedadActual;
    }

    public void setEnfermedadActual(AbstractEnfermedad enfermedadActual) {
        this.enfermedadActual = enfermedadActual;
    }
    
}
