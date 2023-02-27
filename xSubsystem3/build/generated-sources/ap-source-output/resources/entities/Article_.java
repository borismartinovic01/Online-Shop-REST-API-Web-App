package resources.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import resources.entities.Item;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-02-22T06:34:16")
@StaticMetamodel(Article.class)
public class Article_ { 

    public static volatile SingularAttribute<Article, Integer> idarticle;
    public static volatile SingularAttribute<Article, Double> price;
    public static volatile SingularAttribute<Article, String> name;
    public static volatile SingularAttribute<Article, String> description;
    public static volatile SingularAttribute<Article, Double> discount;
    public static volatile ListAttribute<Article, Item> itemList;

}