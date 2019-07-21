import java.io.*;
import java.util.LinkedList;

public class GetDataFromFile<E> {
    private String path;
    private String regex;

    //public GetDataFromFile (){}
    public GetDataFromFile (String path, String regex){
        this.path = path;
        this.regex = regex;
    }

    public LinkedList<E> getData () throws IOException{
        File file = new File (path);

        if (file.exists()){
            if (file.isFile()){
                BufferedReader reader = new BufferedReader(new FileReader(this.path));
                String s = reader.readLine();
                String[] str;
                Utente u;
                Post p;
                E e;
                LinkedList<E> lista = new LinkedList<>();

                while (s!=null){
                    str = s.split(this.regex);
                    if (str.length == 3){
                        u = new Utente(str[0], str[1], str[2]);
                        e = (E)u;
                        lista.add(e);
                    }else if (str.length == 2){
                        p = new Post(str[0], str[1]);
                        e = (E)p;
                        lista.add(e);
                    }

                    s = reader.readLine();
                }
                reader.close();
                return lista;
            }
            else if (file.isDirectory()){
                BufferedWriter writer;
                File fileDaScrivere = new File(this.path + "out.txt");
                writer = new BufferedWriter(new FileWriter(fileDaScrivere));

                String[] directory = file.list();

                for (String s : directory){
                    writer.write(s + "\n");
                }
                writer.close();
                return new LinkedList<>();
            }
            else
                return new LinkedList<>();
        }
        else
            throw new IOException("file not found");
    }
}
