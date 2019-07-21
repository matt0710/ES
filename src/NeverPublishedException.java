public class NeverPublishedException extends Exception {
    public NeverPublishedException (String s){
        System.err.println(s);
    }
}
