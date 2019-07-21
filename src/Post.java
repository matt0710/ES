public class Post {
    private String nome;
    private String data;

    public enum Stato{BOZZA,PUBBLICATO,RIMOSSO}

    private Stato stato;

    //public Post (){}
    public Post (String nome, String data){
        this.nome = nome;
        this.data = data;
        this.stato = Stato.BOZZA;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\tdata: " + this.data + "\tstato: " + this.stato;
    }
}
