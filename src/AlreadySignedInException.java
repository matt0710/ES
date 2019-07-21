public class AlreadySignedInException extends Exception {
    public AlreadySignedInException(String s){
        System.err.println(s);
    }
}
