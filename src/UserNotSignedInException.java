public class UserNotSignedInException extends Exception {
    public UserNotSignedInException (String s){
        System.err.println(s);
    }
}
