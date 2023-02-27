/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Win10
 */
@Entity
@Table(name = "article")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
    @NamedQuery(name = "Article.findByIdarticle", query = "SELECT a FROM Article a WHERE a.idarticle = :idarticle"),
    @NamedQuery(name = "Article.findByName", query = "SELECT a FROM Article a WHERE a.name = :name"),
    @NamedQuery(name = "Article.findByPrice", query = "SELECT a FROM Article a WHERE a.price = :price"),
    @NamedQuery(name = "Article.findByDescription", query = "SELECT a FROM Article a WHERE a.description = :description"),
    @NamedQuery(name = "Article.findByDiscount", query = "SELECT a FROM Article a WHERE a.discount = :discount")})
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idarticle")
    private Integer idarticle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "discount")
    private double discount;
    @JoinTable(name = "selling", joinColumns = {
        @JoinColumn(name = "idarticle", referencedColumnName = "idarticle")}, inverseJoinColumns = {
        @JoinColumn(name = "iduser", referencedColumnName = "iduser")})
    @ManyToMany
    private List<User> userList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    private List<Contains> containsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    private List<Review> reviewList;
    @JoinColumn(name = "category", referencedColumnName = "idcategory")
    @ManyToOne(optional = false)
    private Category category;

    public Article() {
    }

    public Article(Integer idarticle) {
        this.idarticle = idarticle;
    }

    public Article(Integer idarticle, String name, double price, String description, double discount) {
        this.idarticle = idarticle;
        this.name = name;
        this.price = price;
        this.description = description;
        this.discount = discount;
    }

    public Integer getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(Integer idarticle) {
        this.idarticle = idarticle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @XmlTransient
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @XmlTransient
    public List<Contains> getContainsList() {
        return containsList;
    }

    public void setContainsList(List<Contains> containsList) {
        this.containsList = containsList;
    }

    @XmlTransient
    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idarticle != null ? idarticle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.idarticle == null && other.idarticle != null) || (this.idarticle != null && !this.idarticle.equals(other.idarticle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "resources.entities.Article[ idarticle=" + idarticle + " ]";
    }
    
}
