/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ciro
 */
@Entity
@Table(name = "ingrediente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ingrediente.findAll", query = "SELECT i FROM Ingrediente i"),
    @NamedQuery(name = "Ingrediente.findById", query = "SELECT i FROM Ingrediente i WHERE i.id = :id"),
    @NamedQuery(name = "Ingrediente.findByCategoria", query = "SELECT i FROM Ingrediente i WHERE i.categoria = :categoria")})
public class Ingrediente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nome")
    private String nome;
    
    @Size(max = 20)
    @Column(name = "categoria")
    private String categoria;
    
    @Column(name = "sovrapprezzo_cent")
    private Integer sovrapprezzoCent;

    public Ingrediente() {
    }

    public Ingrediente(Integer id) {
        this.id = id;
    }

    public Ingrediente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Ingrediente(String nome, String categoria, Integer sovrapprezzoCent) {
        this.nome = nome;
        this.categoria = categoria;
        this.sovrapprezzoCent = sovrapprezzoCent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getSovrapprezzoCent() {
        return sovrapprezzoCent;
    }

    public void setSovrapprezzoCent(Integer sovrapprezzoCent) {
        this.sovrapprezzoCent = sovrapprezzoCent;
    }

    public String getPrezzoString() {
        String price = Integer.toString(this.sovrapprezzoCent);
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
        return "Ingrediente{" + "id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", sovrapprezzoCent=" + sovrapprezzoCent + '}';
    }

    
    
}
