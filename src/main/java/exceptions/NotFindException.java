package exceptions;

/**
 * Created by linhtran on 10/06/2017.
 */
public class NotFindException extends Exception {
    public static  final String  POST_NOT_FOUND ="access.post.not.found";
    public NotFindException(String s) {
        super(s);
    }
}
