
package requests;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CitiesListRequest implements Serializable{
    ArrayList<CityRequest> cities;

    public ArrayList<CityRequest> getCities() {
        return cities;
    }

    public void setCities(ArrayList<CityRequest> cities) {
        this.cities = cities;
    }
}
