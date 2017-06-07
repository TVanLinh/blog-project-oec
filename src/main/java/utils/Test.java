package utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by linhtran on 08/05/2017.
 */
public class Test {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
        System.out.println(passwordEncoder.encode("123456"));
        System.out.println(passwordEncoder.matches("123456",passwordEncoder.encode("123456")));
//        Collections.sort();
    }
}
