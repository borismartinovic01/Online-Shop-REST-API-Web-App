
package requests;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ArticlesListRequest implements Serializable{
    
    private ArrayList<ArticleRequest> articles;

    public ArrayList<ArticleRequest> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<ArticleRequest> articles) {
        this.articles = articles;
    }
    
    
}
