/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ciro
 */
@XmlRootElement
public class OrdineStaff implements Serializable {
   private static final long serialVersionUID = 1L;
    
   private String tavolo;
   private List<PiattoStaff> piatti;
   private Integer totaleCent;

    public OrdineStaff(String tavolo, List<PiattoStaff> piatti, Integer TotaleCent) {
        this.tavolo = tavolo;
        this.piatti = piatti;
        this.totaleCent = TotaleCent;
    }

    public String getTavolo() {
        return tavolo;
    }

    public void setTavolo(String tavolo) {
        this.tavolo = tavolo;
    }

    public List<PiattoStaff> getPiatti() {
        return piatti;
    }

    public void setPiatti(List<PiattoStaff> piatti) {
        this.piatti = piatti;
    }
    
    public void addPiatti(List<PiattoStaff> piatti) {
        this.piatti.addAll(piatti);
    }

    public Integer getTotaleCent() {
        return totaleCent;
    }

    public void setTotaleCent(Integer totaleCent) {
        this.totaleCent = totaleCent;
    }
    
    public void addTotaleCent(Integer totaleCent) {
        this.totaleCent += totaleCent;
    }
   
}
