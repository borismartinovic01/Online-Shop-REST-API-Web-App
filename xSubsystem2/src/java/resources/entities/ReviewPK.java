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
public class ReviewPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "iduser")
    private int iduser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idarticle")
    private int idarticle;

    public ReviewPK() {
    }

    public ReviewPK(int iduser, int idarticle) {
        this.iduser = iduser;
        this.idarticle = idarticle;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
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
        hash += (int) iduser;
        hash += (int) idarticle;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReviewPK)) {
            return false;
        }
        ReviewPK other = (ReviewPK) object;
        if (this.iduser != other.iduser) {
            return false;
        }
        if (this.idarticle != other.idarticle) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "resources.entities.ReviewPK[ iduser=" + iduser + ", idarticle=" + idarticle + " ]";
    }
    
}
