/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ciro
 */
@XmlRootElement
public class Menu implements Serializable{
    
    private Map<String, List<Piatto>> menu;

    public Menu() {
        menu = new TreeMap<>();
    }

    public Map<String, List<Piatto>> getMenu() {
        return menu;
    }
    
    public void addPiatto(Piatto p) {
        if(!menu.containsKey(p.getCategoria()))
            menu.put(p.getCategoria(), new ArrayList<>());
        menu.get(p.getCategoria()).add(p);
    }
    
}
