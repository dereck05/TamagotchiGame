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
public class Originator {
    private String state;
    
    public void setState(String state){
      this.state = state;
   }

   public String getState(){
      return state;
   }

   public Memento saveStateToMemento(){
      return new Memento(state);
   }

   public String getStateFromMemento(Memento memento){
      state = memento.getState();
      return state;
   }
}
