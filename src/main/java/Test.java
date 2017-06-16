import entities.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by linhtran on 10/06/2017.
 */
public class Test {
    public static void main(String[] args) {
//        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
//        System.out.println(passwordEncoder.matches("123456","$2a$10$CPK4hxZj48n4Tqm9o0Bn2uXY04jEh7MDlcPXe96d15H88lzsyJaKO"));
//
//
        Map<String, Configuration> integerMap = new HashMap<String, Configuration>();

        Configuration configuration = new Configuration();
        configuration.setName("1");
        integerMap.put("1", configuration);
        Configuration configuration2 = new Configuration();
        configuration2.setName("8");
        integerMap.put("1", configuration2);

        System.out.println(integerMap.get("1").getName());
        //System.out.println(integerMap.get("2").getName());
    }
}
