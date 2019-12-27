/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ciro
 */
@Entity
@Table(name = "ordine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordine.findAll", query = "SELECT o FROM Ordine o"),
    @NamedQuery(name = "Ordine.findById", query = "SELECT o FROM Ordine o WHERE o.id = :id"),
    @NamedQuery(name = "Ordine.findByData", query = "SELECT o FROM Ordine o WHERE o.data = :data"),
    @NamedQuery(name = "Ordine.findByTotale", query = "SELECT o FROM Ordine o WHERE o.totale = :totale")})
public class Ordine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    
    @Column(name = "totale")
    private BigDecimal totale;
    
    @JoinTable(name = "ordineXpiatto", joinColumns = {
        @JoinColumn(name = "id_ordine", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "id_piatto", referencedColumnName = "id")})
    @ManyToMany
    private Collection<Piatto> piattoCollection;

    public Ordine() {
    }

    public Ordine(Integer id) {
        this.id = id;
    }

    public Ordine(Date data, BigDecimal totale, Collection<Piatto> piattoCollection) {
        this.data = data;
        this.totale = totale;
        this.piattoCollection = piattoCollection;
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

    public BigDecimal getTotale() {
        return totale;
    }

    public void setTotale(BigDecimal totale) {
        this.totale = totale;
    }

    @XmlTransient
    public Collection<Piatto> getPiattoCollection() {
        return piattoCollection;
    }

    public void setPiattoCollection(Collection<Piatto> piattoCollection) {
        this.piattoCollection = piattoCollection;
    }

    @Override
    public String toString() {
        return "Ordine{" + "id=" + id + ", data=" + data + ", totale=" + totale + ", piattoCollection=" + piattoCollection + '}';
    }

    
    
}
