/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Memento;

import java.util.ArrayList;

/**
 *
 * @author naty9
 */
public class Caretaker {
    private ArrayList<Memento> mementoList = new ArrayList<>();
    
    public void addMemento(Memento state){
        mementoList.add(state);
    }
    
    public Memento getMemento(int index){
        return mementoList.get(index);
    }
    public ArrayList<Memento> getList(){
        return this.mementoList;
    }
}
