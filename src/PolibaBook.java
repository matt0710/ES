import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class PolibaBook {
    private HashMap<String, Utente> utentiRegistrati =  new HashMap<>();
    private HashMap<String, LinkedList<Post>> bacheca =  new HashMap<>();

    //public PolibaBook (){}
    public PolibaBook (HashMap<String, Utente> utentiRegistrati, HashMap<String, LinkedList<Post>> bacheca){
        this.utentiRegistrati = utentiRegistrati;
        this.bacheca = bacheca;
    }

    public void signIn (Utente utente) throws AlreadySignedInException{
        if (this.utentiRegistrati.containsKey(utente.getMail())){
            throw new AlreadySignedInException("The user is already signed in");
        }
        this.utentiRegistrati.put(utente.getMail(), utente);
    }

    public boolean isSignedIn (String mail) {
        return this.utentiRegistrati.containsKey(mail);
    }

    public void pubblica (String mail, Post post) throws UserNotSignedInException{

        if (isSignedIn(mail)){
            LinkedList<Post>  lista = new LinkedList<>();
            if (this.bacheca.get(mail) == null){
                this.bacheca.put(mail, lista);
                lista.add(post);
            }
            else{
                LinkedList<Post> listaVecchia = new LinkedList<>();
                for (int i=0; i<this.bacheca.get(mail).size(); i++){
                    listaVecchia.add(this.bacheca.get(mail).get(i));
                }
                listaVecchia.add(post);
                this.bacheca.remove(mail);
                this.bacheca.put(mail, listaVecchia);
            }

        }
        else
            throw new UserNotSignedInException("Impossibile pubblicare perchè l'utente non è registrato");
    }

    public void rimuovi (String mail, Post post) throws NeverPublishedException, UserNotSignedInException, PostNotFoundException{
        boolean trovato = false;
        if (isSignedIn(mail)){
            if (this.bacheca.get(mail).size() == 0)
                throw new NeverPublishedException("L'utente non ha mai pubblicato un post");
            for (int i=0; i<this.bacheca.get(mail).size(); i++){
                if (this.bacheca.get(mail).get(i) == post){
                    trovato = true;
                    this.bacheca.get(mail).get(i).setStato(Post.Stato.RIMOSSO);
                }
            }
            if (trovato) {
                this.bacheca.get(mail).remove(post);
                //his.bacheca.get(mail).
            }
            else
                throw new PostNotFoundException("Impossibile eliminare il post perchè non è stato trovato");
        }else
            throw new UserNotSignedInException("Impossibile rimuovere il post poichè l'utente non è registrato");
    }

    public HashMap<String, Utente> getUtentiRegistrati() {
        return utentiRegistrati;
    }

    public void setUtentiRegistrati(HashMap<String, Utente> utentiRegistrati) {
        this.utentiRegistrati = utentiRegistrati;
    }

    public HashMap<String, LinkedList<Post>> getBacheca() {
        return bacheca;
    }

    public void setBacheca(HashMap<String, LinkedList<Post>> bacheca) {
        this.bacheca = bacheca;
    }

    @Override
    public String toString() {
        Set<String> set = this.bacheca.keySet();
        Iterator<String> iter = set.iterator();
        String s = "";

        while (iter.hasNext()){
            String key = iter.next();
            s += key + "\n";
            for (Post p : this.bacheca.get(key))
                s += "\t" + p + "\n";
        }
        return s;
    }
}
