
package xsubsystem1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import requests.CitiesListRequest;
import requests.CityRequest;
import requests.Request;
import requests.UserRequest;
import requests.UsersListRequest;
import resources.entities.City;
import resources.entities.User;
import responses.ZahtevR;

public class Main {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private static ConnectionFactory cf;
    
    @Resource(lookup = "xxQueueR")
    private static Queue myQueueR;
    
    @Resource(lookup = "xxQueue1")
    private static Queue queue1;
    
    public static void main(String[] args) {
        JMSContext context = cf.createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(queue1);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("xSubsystem1PU");
        EntityManager em = emf.createEntityManager();
        
        while(true){
            System.out.println("Pokrenut subsystem1");
            Message msg = consumer.receive();
            System.out.println("Primljena poruka");
            if(msg instanceof ObjectMessage){
                try {
                    ObjectMessage om = (ObjectMessage)msg;
                    int code = om.getIntProperty("idR");
                    if(code == 1){
                        City city = new City();
                        CityRequest cr = (CityRequest)om.getObject();
                        city.setName(cr.getVal());
                        
                        em.getTransaction().begin();
                        em.persist(city);
                        em.getTransaction().commit();
                        
                        System.out.println(city.getName());
                        cr.setVal("OK");
                        ObjectMessage objOdg = context.createObjectMessage(cr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                    }
                    else if(code == 2){
                        UserRequest ur = (UserRequest)om.getObject();
                        CityRequest cr = new CityRequest();
                        List<User> list = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", ur.getUsername()).getResultList();
                        if(!list.isEmpty()){
                            cr.setVal("error1");
                            ObjectMessage objOdg = context.createObjectMessage(cr);
                            producer.send(myQueueR, objOdg);
                            continue;
                        }
                        
                        List<City> cities = em.createNamedQuery("City.findByName", City.class).setParameter("name", ur.getCity()).getResultList();
                        
                        if(cities.isEmpty()){
                            cr.setVal("error2");
                            ObjectMessage objOdg = context.createObjectMessage(cr);
                            producer.send(myQueueR, objOdg);
                            continue;
                        }
                        
                        City city = cities.get(0);
                        
                        User user = new User();
                        user.setAddress(ur.getAddress());
                        user.setBalance(ur.getBalance());
                        user.setCity(city);
                        user.setName(ur.getName());
                        user.setPassword(ur.getPassword());
                        user.setSurname(ur.getSurname());
                        user.setUsername(ur.getUsername());
                        
                        em.getTransaction().begin();
                        em.persist(user);
                        em.getTransaction().commit();
                        
                        System.out.println(user.getName() + " unet u bazu");
                        cr.setVal("OK");
                        ObjectMessage objOdg = context.createObjectMessage(cr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                    }
                    else if(code == 3){
                        UserRequest ur = (UserRequest)om.getObject();
                        CityRequest cr = new CityRequest();
                        List<User> list = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", ur.getUsername()).getResultList();
                        if(list.isEmpty()){
                            cr.setVal("error");
                            ObjectMessage objOdg = context.createObjectMessage(cr);
                            producer.send(myQueueR, objOdg);
                            continue;
                        }
                        
                        User user = list.get(0);
                        
                        em.getTransaction().begin();
                        user.setBalance(user.getBalance() + ur.getBalance());
                        em.getTransaction().commit();
                        
                        System.out.println(ur.getBalance() + " dodato ovoliko novca u bazu");
                        cr.setVal("OK");
                        ObjectMessage objOdg = context.createObjectMessage(cr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                    }
                    else if(code == 4){
                        
                        UserRequest ur = (UserRequest)om.getObject();
                        CityRequest cr = new CityRequest();
                        List<User> list = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", ur.getUsername()).getResultList();
                        if(list.isEmpty()){
                            cr.setVal("error");
                            ObjectMessage objOdg = context.createObjectMessage(cr);
                            producer.send(myQueueR, objOdg);
                            continue;
                        }
                        
                        List<City> cities = em.createNamedQuery("City.findByName", City.class).setParameter("name", ur.getCity()).getResultList();
                        
                        if(cities.isEmpty()){
                            cr.setVal("error1");
                            ObjectMessage objOdg = context.createObjectMessage(cr);
                            producer.send(myQueueR, objOdg);
                            continue;
                        }
                        
                        City city = cities.get(0);
                        User user = list.get(0);
                        
                        em.getTransaction().begin();
                        user.setCity(city);
                        user.setAddress(ur.getAddress());
                        em.getTransaction().commit();
                        
                        System.out.println("dodata adresa i grad u bazu");
                        cr.setVal("OK");
                        ObjectMessage objOdg = context.createObjectMessage(cr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                        
                    }
                    else if(code == 12){
                        List<City> cities = em.createNamedQuery("City.findAll", City.class).getResultList();
                        ArrayList<CityRequest> requests = new ArrayList<>();
                        for (City city : cities) {
                            CityRequest cityRequest = new CityRequest();
                            cityRequest.setVal(city.getName());
                            requests.add(cityRequest);
                        }
                        
                        System.out.println("dohvaceni gradovi iz baze");
                        CitiesListRequest clr = new CitiesListRequest();
                        clr.setCities(requests);
                        ObjectMessage objOdg = context.createObjectMessage(clr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                    }
                    else if(code == 13){
                        List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
                        ArrayList<UserRequest> requests = new ArrayList<>();
                        for (User user: users) {
                            UserRequest userRequest = new UserRequest();
                            userRequest.setAddress(user.getAddress());
                            userRequest.setBalance(user.getBalance());
                            userRequest.setCity(user.getCity().getName());
                            userRequest.setName(user.getName());
                            userRequest.setPassword(user.getPassword());
                            userRequest.setSurname(user.getSurname());
                            userRequest.setUsername(user.getUsername());
                            requests.add(userRequest);
                        }
                        
                        System.out.println("dohvaceni korisnici iz baze");
                        UsersListRequest clr = new UsersListRequest();
                        clr.setUsers(requests);
                        ObjectMessage objOdg = context.createObjectMessage(clr);
                        producer.send(myQueueR, objOdg);
                        System.out.println("Poslat odgovor");
                    }
                } catch (JMSException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
