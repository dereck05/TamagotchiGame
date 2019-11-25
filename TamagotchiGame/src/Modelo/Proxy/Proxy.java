/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Proxy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author naty9
 */
public class Proxy implements IProxy{
    private String filename;
    private String activity;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
    
    @Override
    public void guardar() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filename + ".txt", true));
            writer.append(this.activity);
            writer.append("\r\n");
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Proxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void obtener() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.filename + ".txt"));
            String line;
            while ((line = br.readLine()) != null) {
               System.out.println(line);
            }
        } catch(IOException ex) {
            Logger.getLogger(Proxy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
