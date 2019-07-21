import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main (String[] args){

        Utente m = new Utente("m", "a","m@mail.it");
        Utente o = new Utente("o", "c","o@mail.it");
        Utente p = new Utente("p", "a","p@mail.it");

        Post post1 = new Post("post1", "01/01/01");
        Post post2 = new Post("post2", "02/02/02");
        Post post3 = new Post("post3", "03/03/03");

        HashMap<String, Utente> utentiRegistrati = new HashMap<>();
        HashMap<String, LinkedList<Post>> bacheca = new HashMap<>();

        PolibaBook facebook = new PolibaBook(utentiRegistrati, bacheca);

        LinkedList<Post> lista1 = new LinkedList<>();

        try {
            facebook.signIn(m);
            facebook.pubblica(m.getMail(), post1);
            //System.out.println(facebook);
            facebook.pubblica(m.getMail(), post2);
            System.out.println(facebook);
            System.out.println("______________________________________");

            //facebook.pubblica(o.getMail(), post1);
            facebook.signIn(o);
            facebook.pubblica(o.getMail(), post3);

            System.out.println(facebook);

            facebook.rimuovi(o.getMail(), post3);

            //facebook.rimuovi(o.getMail(), post3);
            //facebook.rimuovi(p.getMail(), post3);

        }catch (AlreadySignedInException asie){
            //System.out.println("ssss");
        }catch (UserNotSignedInException unsie){

        }catch (NeverPublishedException npe){

        }catch (PostNotFoundException pnfe){}

        try{
            Scanner sc = new Scanner(System.in);

            System.out.println("Inserisci la path utenti: ");
            String pathCliente = sc.nextLine();
            GetDataFromFile<Utente> getUtente = new GetDataFromFile<>(pathCliente, " ");
            System.out.println("*******************************************");
            System.out.println(getUtente.getData());

            System.out.println("Inserisci la path post: ");
            String pathPost = sc.nextLine();
            GetDataFromFile<Post> getPost = new GetDataFromFile<>(pathPost, " ");
            System.out.println(getPost.getData());
        }catch (IOException io){
            System.err.println(io.getMessage());
        }
    }
}
