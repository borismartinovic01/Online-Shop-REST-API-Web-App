/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Win10
 */
@Entity
@Table(name = "review")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r"),
    @NamedQuery(name = "Review.findByIduser", query = "SELECT r FROM Review r WHERE r.reviewPK.iduser = :iduser"),
    @NamedQuery(name = "Review.findByIdarticle", query = "SELECT r FROM Review r WHERE r.reviewPK.idarticle = :idarticle"),
    @NamedQuery(name = "Review.findByDescription", query = "SELECT r FROM Review r WHERE r.description = :description"),
    @NamedQuery(name = "Review.findByGrade", query = "SELECT r FROM Review r WHERE r.grade = :grade")})
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReviewPK reviewPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "grade")
    private int grade;
    @JoinColumn(name = "idarticle", referencedColumnName = "idarticle", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Article article;
    @JoinColumn(name = "iduser", referencedColumnName = "iduser", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Review() {
    }

    public Review(ReviewPK reviewPK) {
        this.reviewPK = reviewPK;
    }

    public Review(ReviewPK reviewPK, String description, int grade) {
        this.reviewPK = reviewPK;
        this.description = description;
        this.grade = grade;
    }

    public Review(int iduser, int idarticle) {
        this.reviewPK = new ReviewPK(iduser, idarticle);
    }

    public ReviewPK getReviewPK() {
        return reviewPK;
    }

    public void setReviewPK(ReviewPK reviewPK) {
        this.reviewPK = reviewPK;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reviewPK != null ? reviewPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.reviewPK == null && other.reviewPK != null) || (this.reviewPK != null && !this.reviewPK.equals(other.reviewPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "resources.entities.Review[ reviewPK=" + reviewPK + " ]";
    }
    
}
