/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ciro
 */
public class FotoIdManager {
    
    private static FotoIdManager instance = null;
    private int lastId;
    private File file;
    
    private FotoIdManager() {
        file = new File("lastIdFoto");
        try {
            if (file.createNewFile())
                lastId = -1;
            else {
                try (FileInputStream in = new FileInputStream(file)) {
                    Scanner sc = new Scanner(in);
                    lastId = sc.nextInt();
                }
            }
        } catch (IOException ex) {
            
        }
        
    }
    
    public static synchronized FotoIdManager getInstance() {
        if(instance == null)
            instance = new FotoIdManager();
        return instance;
    }
    
    public synchronized int getNewIndex() {
        try(FileOutputStream out = new FileOutputStream(file)) {
            PrintStream ps = new PrintStream(out);
            lastId++;
            ps.print(lastId);
            return lastId;
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {  
        }
        return -1;
    }
}
