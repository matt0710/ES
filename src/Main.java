import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main (String[] args){

        Utente matteo = new Utente("matteo", "attimonelli","m@mail.it");
        Utente olimpia = new Utente("olimpia", "cic","o@mail.it");
        Utente pasquale = new Utente("pasquale", "attimonelli","p@mail.it");

        Post post1 = new Post("post1", "01/01/01");
        Post post2 = new Post("post2", "02/02/02");
        Post post3 = new Post("post3", "03/03/03");

        HashMap<String, Utente> utentiRegistrati = new HashMap<>();
        HashMap<String, LinkedList<Post>> bacheca = new HashMap<>();

        PolibaBook facebook = new PolibaBook(utentiRegistrati, bacheca);

        LinkedList<Post> lista1 = new LinkedList<>();

        try {
            facebook.signIn(matteo);
            facebook.pubblica(matteo.getMail(), post1);
            //System.out.println(facebook);
            facebook.pubblica(matteo.getMail(), post2);
            System.out.println(facebook);
            System.out.println("______________________________________");

            //facebook.pubblica(olimpia.getMail(), post1);
            facebook.signIn(olimpia);
            facebook.pubblica(olimpia.getMail(), post3);

            System.out.println(facebook);

            facebook.rimuovi(olimpia.getMail(), post3);

            //facebook.rimuovi(olimpia.getMail(), post3);
            //facebook.rimuovi(pasquale.getMail(), post3);

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
