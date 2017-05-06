package Controller;

import DAO.AuthorDAOIML;
import Entities.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by linhtran on 06/05/2017.
 */

@Controller
public class AccountController {

    @Autowired
    AuthorDAOIML authorDAOIML;
    @RequestMapping(value = "/post")
    public  String viewPost()
    {
        return "post";

    }

    @RequestMapping(value = "/write")
    public  String viewWriter()
    {
        return "write";
    }

    @RequestMapping("/insert")
    public String insertAuthor()
    {
        System.out.println( authorDAOIML.insert(new Author("Tran Van linh","123444",1)));
        return "post";
    }
}
