package resources.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import resources.entities.Article;
import resources.entities.Category;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-02-22T06:35:35")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile SingularAttribute<Category, Integer> idcategory;
    public static volatile ListAttribute<Category, Article> articleList;
    public static volatile SingularAttribute<Category, String> name;
    public static volatile ListAttribute<Category, Category> categoryList;
    public static volatile SingularAttribute<Category, Category> subcategory;

}