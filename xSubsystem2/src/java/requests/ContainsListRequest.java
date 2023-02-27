
package requests;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ContainsListRequest implements Serializable{
    
    private List<ContainsRequest> containsRequests;
    private double price;
    private String address;
    private String username;
    private String city;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public List<ContainsRequest> getContainsRequests() {
        return containsRequests;
    }

    public void setContainsRequests(List<ContainsRequest> containsRequests) {
        this.containsRequests = containsRequests;
    }
    
    
    
}
