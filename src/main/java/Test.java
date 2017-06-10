import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by linhtran on 10/06/2017.
 */
public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.matches("123456","$2a$10$CPK4hxZj48n4Tqm9o0Bn2uXY04jEh7MDlcPXe96d15H88lzsyJaKO"));
    }
}
