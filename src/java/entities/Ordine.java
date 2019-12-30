/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.sql.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ciro
 */
@XmlRootElement
public class Ordine implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private Date data;
    private Integer totaleCent;
    private Collection<Piatto> piatti;

    public Ordine() {
    }

    public Ordine(Date data, Integer totaleCent, Collection<Piatto> piatti) {
        this.data = data;
        this.totaleCent = totaleCent;
        this.piatti = piatti;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getTotaleCent() {
        return totaleCent;
    }

    public void setTotaleCent(Integer totaleCent) {
        this.totaleCent = totaleCent;
    }


    @XmlTransient
    public Collection<Piatto> getPiatti() {
        return piatti;
    }

    public void setPiatti(Collection<Piatto> piatti) {
        this.piatti = piatti;
    }
    
    public String getPrezzoString() {
        String price = Integer.toString(this.totaleCent);
        int size = price.length();
        switch (size) {
            case 1:
                return "0,0" + price;
            case 2:
                return "0," + price;
            default:
                return price.substring(0, size-2)+","+price.substring(size-2);
        }
    }

    @Override
    public String toString() {
        return "Ordine{" + "id=" + id + ", data=" + data + ", totaleCent=" + totaleCent + ", piatti=" + piatti + '}';
    }

    
    
}
