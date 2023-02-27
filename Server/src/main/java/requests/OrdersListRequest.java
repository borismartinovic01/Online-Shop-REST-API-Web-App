
package requests;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OrdersListRequest implements Serializable{
    
    private List<OrderRequest> orders;

    public List<OrderRequest> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderRequest> orders) {
        this.orders = orders;
    }
    
    
    
}
