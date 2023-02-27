
package endpoints;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import resources.TestClass;
import javax.ws.rs.core.Response;
import requests.ArticleRequest;
import requests.CityRequest;
import requests.ContainsListRequest;
import requests.OrdersListRequest;
import requests.StringRequest;
import requests.UserRequest;
import requests.UsersListRequest;

@Path("users")
@Stateless
public class Users {
    
    @Resource(lookup = "jms/__defaultConnectionFactory")
    private ConnectionFactory cf;
    
    @Resource(lookup = "xxQueueR")
    private Queue myQueueR;
    
    @Resource(lookup = "xxQueue1")
    private Queue queue1;
    
    @Resource(lookup = "xxQueue2")
    private Queue queue2;
    
    @Resource(lookup = "xxQueue3")
    private Queue queue3;
    
    @POST
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response createUser(UserRequest ur){
        System.out.println("Usao");
        try {
            JMSContext context = cf.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(myQueueR);
            ObjectMessage om = context.createObjectMessage(ur);
            om.setIntProperty("idR", 2);
            producer.send(queue1, om);
            System.out.println("Poslao novog korisnika");
            Message msg = consumer.receive();
            ObjectMessage odg = (ObjectMessage)msg;
            CityRequest cr = (CityRequest)odg.getObject();
            if(cr.getVal().equals("error1")){
                consumer.close();
                context.close();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Taj username vec postoji").build();
            }
            if(cr.getVal().equals("error2")){
                consumer.close();
                context.close();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Taj grad ne postoji").build();
            }
            
            ObjectMessage om2 = context.createObjectMessage(ur);
            om2.setIntProperty("idR", 2);
            producer.send(queue2, om2);
            System.out.println("Poslao novog korisnika2");
            Message msg2 = consumer.receive();
            
            ObjectMessage om3 = context.createObjectMessage(ur);
            om3.setIntProperty("idR", 2);
            producer.send(queue3, om3);
            System.out.println("Poslao novog korisnika3");
            Message msg3 = consumer.receive();
            
            consumer.close();
            context.close();
            return Response.status(Response.Status.OK).entity("Korisnik uspesno kreiran").build();
        } catch (JMSException ex) {
            Logger.getLogger(Cities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Korisnik nije kreiran").build();
    }
    
    @PUT
    @Path("addamount")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response addMoney(UserRequest ur){
        System.out.println("Usao3");
        try {
            JMSContext context = cf.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(myQueueR);
            ObjectMessage om = context.createObjectMessage(ur);
            om.setIntProperty("idR", 3);
            producer.send(queue1, om);
            System.out.println("Poslao dodatni novac");
            Message msg = consumer.receive();
            ObjectMessage odg = (ObjectMessage)msg;
            CityRequest cr = (CityRequest)odg.getObject();
            if(cr.getVal().equals("error")){
                consumer.close();
                context.close();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Neuspesno dodavanje novca").build();
            }
            
            ObjectMessage om2 = context.createObjectMessage(ur);
            om2.setIntProperty("idR", 3);
            producer.send(queue2, om2);
            System.out.println("Poslao dodatni novac2");
            Message msg2 = consumer.receive();
            
            ObjectMessage om3 = context.createObjectMessage(ur);
            om3.setIntProperty("idR", 3);
            producer.send(queue3, om3);
            System.out.println("Poslao dodatni novac3");
            Message msg3 = consumer.receive();
            
            consumer.close();
            context.close();
            return Response.status(Response.Status.OK).entity("Novac uspesno dodat").build();
        } catch (JMSException ex) {
            Logger.getLogger(Cities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Neuspesno dodavanje novca").build();
    }
    
    @PUT
    @Path("setaddress")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response setAddressAndCity(UserRequest ur){
        System.out.println("Usao4");
        try {
            JMSContext context = cf.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(myQueueR);
            ObjectMessage om = context.createObjectMessage(ur);
            om.setIntProperty("idR", 4);
            producer.send(queue1, om);
            System.out.println("Poslao novu adresu i grad");
            Message msg = consumer.receive();
            ObjectMessage odg = (ObjectMessage)msg;
            CityRequest cr = (CityRequest)odg.getObject();
            if(cr.getVal().equals("error")){
                consumer.close();
                context.close();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Username ne postoji").build();
            }
            if(cr.getVal().equals("error1")){
                consumer.close();
                context.close();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Grad ne postoji").build();
            }
            
            ObjectMessage om2 = context.createObjectMessage(ur);
            om2.setIntProperty("idR", 4);
            producer.send(queue2, om2);
            System.out.println("Poslao novu adresu i grad3");
            Message msg2 = consumer.receive();
            
            ObjectMessage om3 = context.createObjectMessage(ur);
            om3.setIntProperty("idR", 4);
            producer.send(queue3, om3);
            System.out.println("Poslao novu adresu i grad3");
            Message msg3 = consumer.receive();
            
            consumer.close();
            context.close();
            return Response.status(Response.Status.OK).entity("Adresa i grad uspesno promenjeni").build();
        } catch (JMSException ex) {
            Logger.getLogger(Cities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Neuspesna promena grada i adrese").build();
    }
    
    @GET
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response getAllUsers(){
        System.out.println("Usao13");
        try {
            JMSContext context = cf.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(myQueueR);
            ObjectMessage om = context.createObjectMessage(new CityRequest());
            om.setIntProperty("idR", 13);
            producer.send(queue1, om);
            System.out.println("Poslao zahtev za sve korisnike");
            Message msg = consumer.receive();
            ObjectMessage odg = (ObjectMessage)msg;
            UsersListRequest cr = (UsersListRequest)odg.getObject();
            consumer.close();
            context.close();
            System.out.println("Saljem klijentu");
            return Response.status(Response.Status.OK).entity(cr).build();
        } catch (JMSException ex) {
            Logger.getLogger(Cities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Nije moguce dohvatiti sve korisnike").build();
    }
    
    @POST
    @Path("pay")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response pay(UserRequest ur){
        System.out.println("Usao11");
        try {
            JMSContext context = cf.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(myQueueR);
            
            //dohvatanje sadrzaja korpe
            
            ArticleRequest ar = new ArticleRequest();
            ar.setName(ur.getUsername());
            ObjectMessage om = context.createObjectMessage(ar);
            om.setIntProperty("idR", 16);
            producer.send(queue2, om);
            System.out.println("Poslao zahtev za sadrzaj korpe");
            Message msg = consumer.receive();
            ObjectMessage odg = (ObjectMessage)msg;
            ContainsListRequest clr = (ContainsListRequest)odg.getObject();
            
            //dohvatanje korisnika radi provere racuna
            
            ObjectMessage om2 = context.createObjectMessage(new CityRequest());
            om2.setIntProperty("idR", 13);
            producer.send(queue1, om2);
            System.out.println("Poslao zahtev za sve korisnike");
            Message msg2 = consumer.receive();
            ObjectMessage odg2 = (ObjectMessage)msg2;
            UsersListRequest cr = (UsersListRequest)odg2.getObject();
            
            UserRequest user = null;
            
            for (UserRequest urr : cr.getUsers()) {
                if(urr.getUsername().equals(ur.getUsername())){
                    user = urr;
                    break;
                }
            }
            
            if(user == null){
                consumer.close();
                context.close();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Username ne postoji").build();
            }
            
            //provera da li user ima dovoljno novca da plati
            
            if(user.getBalance() < clr.getPrice()){
                consumer.close();
                context.close();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Nemate dovoljno novca").build();
            }
            
            //pravljenje narudzbine, transackije i stavki
            clr.setUsername(ur.getUsername());
            ObjectMessage om4 = context.createObjectMessage(clr);
            om4.setIntProperty("idR", 11);
            producer.send(queue3, om4);
            System.out.println("Poslao zahtev da se napravi narudzbina");
            Message msg4 = consumer.receive();
            ObjectMessage odg4 = (ObjectMessage)msg4;
            StringRequest cr4 = (StringRequest)odg4.getObject();
            
            if(cr4.getVal().equals("error")){
                consumer.close();
                context.close();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Neuspesno kreiranje narudzbine").build();
            }
            //skidanje para sa racuna
            
            ur.setBalance(-clr.getPrice());
            System.out.println("Promeni balans " + clr.getPrice());
            ObjectMessage om3 = context.createObjectMessage(ur);
            om3.setIntProperty("idR", 3);
            producer.send(queue1, om3);
            System.out.println("Poslao zahtev za skidanje novca");
            Message msg3 = consumer.receive();
            ObjectMessage odg3 = (ObjectMessage)msg3;
            CityRequest cr3 = (CityRequest)odg3.getObject();
            
            ObjectMessage om5 = context.createObjectMessage(ur);
            om5.setIntProperty("idR", 3);
            producer.send(queue2, om5);
            System.out.println("Poslao zahtev za skidanje novca2");
            Message msg5 = consumer.receive();
            
            ObjectMessage om6 = context.createObjectMessage(ur);
            om6.setIntProperty("idR", 3);
            producer.send(queue3, om6);
            System.out.println("Poslao zahtev za skidanje novca2");
            Message msg6 = consumer.receive();
            
            //todo resetovanje korpe i brisanje iz contains
            
            ObjectMessage om7 = context.createObjectMessage(ur);
            om7.setIntProperty("idR", 20);
            producer.send(queue2, om7);
            System.out.println("Poslao zahtev za resetovanje korpe");
            Message msg7 = consumer.receive();
            
            consumer.close();
            context.close();
            return Response.status(Response.Status.OK).entity("Placanje izvrseno").build();
        } catch (JMSException ex) {
            Logger.getLogger(Cities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Neuspesno placanje").build();
    }
    
//    @Path("{username}")
//    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
//    public Response getMyCart(@PathParam("username") String username){
    
    @GET
    @Path("myorders/{username}")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response getMyOrders(@PathParam("username") String username){
        System.out.println("Usao17");
        try {
            JMSContext context = cf.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(myQueueR);
            ArticleRequest ar = new ArticleRequest();
            ar.setName(username);
            ObjectMessage om = context.createObjectMessage(ar);
            om.setIntProperty("idR", 17);
            producer.send(queue3, om);
            System.out.println("Poslao zahtev za moje narudzbine");
            Message msg = consumer.receive();
            ObjectMessage odg = (ObjectMessage)msg;
            OrdersListRequest cr = (OrdersListRequest)odg.getObject();
            consumer.close();
            context.close();
            return Response.status(Response.Status.OK).entity(cr).build();
        } catch (JMSException ex) {
            Logger.getLogger(Cities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Nije moguce dohvatiti narudzbine").build();
    }
    
    @GET
    @Path("orders")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response getAllOrders(){
        System.out.println("Usao18");
        try {
            JMSContext context = cf.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(myQueueR);
            ArticleRequest ar = new ArticleRequest();
            ObjectMessage om = context.createObjectMessage(ar);
            om.setIntProperty("idR", 18);
            producer.send(queue3, om);
            System.out.println("Poslao zahtev za sve narudzbine");
            Message msg = consumer.receive();
            ObjectMessage odg = (ObjectMessage)msg;
            OrdersListRequest cr = (OrdersListRequest)odg.getObject();
            consumer.close();
            context.close();
            return Response.status(Response.Status.OK).entity(cr).build();
        } catch (JMSException ex) {
            Logger.getLogger(Cities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Nije moguce dohvatiti narudzbine").build();
    }
    
    @GET
    @Path("transactions")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response getAllTransactions(){
        System.out.println("Usao19");
        try {
            JMSContext context = cf.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(myQueueR);
            ArticleRequest ar = new ArticleRequest();
            ObjectMessage om = context.createObjectMessage(ar);
            om.setIntProperty("idR", 19);
            producer.send(queue3, om);
            System.out.println("Poslao zahtev za sve transakcije");
            Message msg = consumer.receive();
            ObjectMessage odg = (ObjectMessage)msg;
            OrdersListRequest cr = (OrdersListRequest)odg.getObject();
            consumer.close();
            context.close();
            return Response.status(Response.Status.OK).entity(cr).build();
        } catch (JMSException ex) {
            Logger.getLogger(Cities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Nije moguce dohvatiti transakcije").build();
    }
}
