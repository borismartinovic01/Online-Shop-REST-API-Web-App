package resources.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import resources.entities.Narudzbina;
import resources.entities.User;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-02-22T06:34:16")
@StaticMetamodel(City.class)
public class City_ { 

    public static volatile ListAttribute<City, User> userList;
    public static volatile ListAttribute<City, Narudzbina> narudzbinaList;
    public static volatile SingularAttribute<City, String> name;
    public static volatile SingularAttribute<City, Integer> idcity;

}