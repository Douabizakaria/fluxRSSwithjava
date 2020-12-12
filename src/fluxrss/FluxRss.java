/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fluxrss;

import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;
import java.io.*;
import java.net.URL;
import java.util.* ;
//import javax.print.DocFlavor.URL;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.* ;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 *
 * @author root
 */
public class FluxRss {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception{
 
      //Creating a HttpClient object
      CloseableHttpClient httpclient = HttpClients.createDefault();
       System.out.println(("Entrer le lien de la chainne "));
       Scanner scan = new Scanner(System.in) ;
       String chainne = scan.next() ;
       String[] chainne_code = chainne.split("/" );
       String rss_link = "https://www.youtube.com/feeds/videos.xml?channel_id="+chainne_code[4] ;
      while(true){
       HttpGet httpget = new HttpGet("https://www.youtube.com/feeds/videos.xml?channel_id="+chainne_code[4]);

      //Printing the method used
      System.out.println("Request Type: "+httpget.getMethod());

      //Executing the Get request
      HttpResponse httpresponse = httpclient.execute(httpget);

      Scanner sc = new Scanner(httpresponse.getEntity().getContent());
      Document doc = Jsoup.connect(rss_link).get();
      //String title = doc.title();
      //Element name = doc.getElementsByTag("name").first() ;
      Elements title = doc.getElementsByTag("title") ;
      ArrayList ttl = new ArrayList() ;
      for (Element src : title)
        {
            ttl.add(src.text())   ;
            //System.out.println(src.text());
           
          
        }
    Elements published = doc.getElementsByTag("published") ;
     ArrayList publi = new ArrayList() ;
     for (Element src : published)
        {
            publi.add(src.text())   ;
           
          //System.out.println(src.text());
        }
     Elements urls = doc.getElementsByAttribute("href") ;
     ArrayList url = new ArrayList() ;
     for(Element src : urls)
        {
          url.add(src.attr("href")) ;
         // System.out.println(src.attr("href"));
        }
      
      url.remove(0) ;
    
       String[] header = {
     "Title", " Date/Time" , "Video Link " 
    
};
    for(int i = 0 ; i < ttl.size() ; i++)
        {
            String[][] data = {
    { ttl.get(i).toString(), publi.get(i).toString(),url.get(i).toString() }
    
};
   System.out.println(FlipTable.of(header, data));

        }
    // List<Item> people = Arrays.asList(new Item("Foo", "Bar"), new Item("Kit", "Kat"));
     //System.out.println(FlipTableConverters.fromIterable(people, Item.class));
     //System.out.println(FlipTable.of(header,pe;

     Thread.sleep(60000) ;
     
      }
}  
     /*//Printing the status line
      System.out.println(httpresponse.getStatusLine());
      while(sc.hasNext()) {
         
      
      }*/
    
}
