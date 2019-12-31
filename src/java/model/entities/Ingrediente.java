/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ciro
 */
@XmlRootElement
public class Ingrediente implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String nome;
    private String categoria;
    private Integer sovrapprezzoCent;

    public Ingrediente() {
    }

    public Ingrediente(String nome, String categoria, Integer sovrapprezzoCent) {
        this.nome = nome;
        this.categoria = categoria;
        this.sovrapprezzoCent = sovrapprezzoCent;
    }

    public Ingrediente(Integer id, String nome, String categoria, Integer sovrapprezzoCent) {
        this.id = id;
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

    public String getSovrapprezzoString() {
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
