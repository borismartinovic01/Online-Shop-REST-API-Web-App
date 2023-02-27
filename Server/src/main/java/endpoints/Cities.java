
package endpoints;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;
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
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import requests.CitiesListRequest;
import requests.CityRequest;
import requests.Request;
import resources.TestClass;

@Path("cities")
@Stateless
public class Cities {
    
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
    
    @GET
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response getAllCities(){
        System.out.println("Usao12");
        try {
            JMSContext context = cf.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(myQueueR);
            ObjectMessage om = context.createObjectMessage(new CityRequest());
            om.setIntProperty("idR", 12);
            producer.send(queue1, om);
            System.out.println("Poslao zahtev za sve gradove");
            Message msg = consumer.receive();
            ObjectMessage odg = (ObjectMessage)msg;
            CitiesListRequest cr = (CitiesListRequest)odg.getObject();
            consumer.close();
            context.close();
            return Response.status(Response.Status.OK).entity(cr).build();
        } catch (JMSException ex) {
            Logger.getLogger(Cities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Nije moguce dohvatiti sve gradove").build();
    }
    
    @POST
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response createCity(CityRequest newCity){
        System.out.println("Usao");
        try {
            JMSContext context = cf.createContext();
            JMSProducer producer = context.createProducer();
            JMSConsumer consumer = context.createConsumer(myQueueR);
            ObjectMessage om = context.createObjectMessage(newCity);
            om.setIntProperty("idR", 1);
            producer.send(queue1, om);
            System.out.println("Poslao grad");
            Message msg = consumer.receive();
            ObjectMessage odg = (ObjectMessage)msg;
            CityRequest cr = (CityRequest)odg.getObject();
            if(cr.getVal().equals("error")){
                consumer.close();
                context.close();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Grad nije kreiran").build();
            }
            
            ObjectMessage om2 = context.createObjectMessage(newCity);
            om2.setIntProperty("idR", 1);
            producer.send(queue3, om2);
            System.out.println("Poslao grad2");
            Message msg2 = consumer.receive();
            
            consumer.close();
            context.close();
            return Response.status(Response.Status.OK).entity("Grad uspesno kreiran").build();
        } catch (JMSException ex) {
            Logger.getLogger(Cities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Grad nije kreiran").build();
    }
    
//    @GET
//    public String getAllCities(@Context HttpHeaders httpHeaders){
//        List<String> authHeaderValues = httpHeaders.getRequestHeader("Authorization");
//        
//        if(authHeaderValues != null && authHeaderValues.size() > 0){
//            String authHeaderValue = authHeaderValues.get(0);
//            String decodedAuthHeaderValue = new String(Base64.getDecoder().decode(authHeaderValue.replaceFirst("Basic ", "")),StandardCharsets.UTF_8);
//            StringTokenizer stringTokenizer = new StringTokenizer(decodedAuthHeaderValue, ":");
//            String username = stringTokenizer.nextToken();
//            String password = stringTokenizer.nextToken();
//            return "neki gradovi" + username + password + " radiiii";
//        }
//        return "neki gradovi ";
//    }
    
}
