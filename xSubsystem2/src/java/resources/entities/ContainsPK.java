/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Win10
 */
@Embeddable
public class ContainsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idcart")
    private int idcart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idarticle")
    private int idarticle;

    public ContainsPK() {
    }

    public ContainsPK(int idcart, int idarticle) {
        this.idcart = idcart;
        this.idarticle = idarticle;
    }

    public int getIdcart() {
        return idcart;
    }

    public void setIdcart(int idcart) {
        this.idcart = idcart;
    }

    public int getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(int idarticle) {
        this.idarticle = idarticle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idcart;
        hash += (int) idarticle;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContainsPK)) {
            return false;
        }
        ContainsPK other = (ContainsPK) object;
        if (this.idcart != other.idcart) {
            return false;
        }
        if (this.idarticle != other.idarticle) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "resources.entities.ContainsPK[ idcart=" + idcart + ", idarticle=" + idarticle + " ]";
    }
    
}
