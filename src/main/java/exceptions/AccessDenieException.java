package exceptions;

/**
 * Created by linhtran on 10/06/2017.
 */
public class AccessDenieException extends  Exception {
    public  static  final String ACCESS_NOT_ROLE_POST ="access.notrole_post";

    public  static final  String ACESS_NOT_ROLE_PAGE = "access.not_role_page";
    public AccessDenieException() {
    }

    public AccessDenieException(String s) {
        super(s);
    }
}
