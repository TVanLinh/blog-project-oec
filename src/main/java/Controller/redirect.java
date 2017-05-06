package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by linhtran on 06/05/2017.
 */
@Controller
public class redirect {
    @RequestMapping(value = "/view",method = RequestMethod.POST)
    public  String view()
    {
        return "view";
    }
}
