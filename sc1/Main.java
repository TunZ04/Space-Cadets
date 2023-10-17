import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        System.out.println("***************************\nWelcome to ecs name search\n***************************\n");

        Boolean validsearch = false;
        while (!validsearch)
        {
            System.out.println("Enter the username");
            String uname = s.nextLine();

           try
           {
               Document d = Jsoup.connect("https://www.southampton.ac.uk/people/" + uname).get();
               String html = d.outerHtml();
               //System.out.println(html);
               int endpointer = html.indexOf("\"url");
               int startpointer = html.indexOf("\"name\": ", endpointer - 100);
               System.out.println(html.substring(startpointer+9, endpointer-15));
                validsearch = true;

           }
           catch (Exception e) { System.out.println("sorry, username couldn't be found"); }
        }
    }
}
