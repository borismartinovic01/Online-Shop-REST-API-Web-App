package resources.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import resources.entities.Article;
import resources.entities.Narudzbina;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-02-22T06:34:16")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, Integer> iditem;
    public static volatile SingularAttribute<Item, Integer> amount;
    public static volatile SingularAttribute<Item, Narudzbina> narudzbina;
    public static volatile SingularAttribute<Item, Double> price;
    public static volatile SingularAttribute<Item, Article> article;

}