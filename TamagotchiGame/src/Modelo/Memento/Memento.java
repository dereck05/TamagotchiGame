/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Memento;

/**
 *
 * @author naty9
 */
public class Memento {
    private String state;
    
    public Memento(String pState){
        this.state = pState;
    }
    
    public String getState(){
        return state;
    }
}
