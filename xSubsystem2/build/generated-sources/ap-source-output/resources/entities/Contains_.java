package resources.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import resources.entities.Article;
import resources.entities.Cart;
import resources.entities.ContainsPK;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-02-22T06:35:35")
@StaticMetamodel(Contains.class)
public class Contains_ { 

    public static volatile SingularAttribute<Contains, ContainsPK> containsPK;
    public static volatile SingularAttribute<Contains, Integer> amount;
    public static volatile SingularAttribute<Contains, Article> article;
    public static volatile SingularAttribute<Contains, Cart> cart;

}