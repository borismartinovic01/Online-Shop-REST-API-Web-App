
package requests;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CityRequest implements Serializable{
    private String val;
    
    public CityRequest() {
        this.val = "none";
    }
    
    public CityRequest(String val) {
        this.val = val;
    }
    
    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
    
    
}
