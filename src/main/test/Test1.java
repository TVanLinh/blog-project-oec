import junit.framework.TestCase;
import org.junit.Test;
import utils.string.StringUtils;

/**
 * Created by linhtran on 07/06/2017.
 */
public class Test1 extends TestCase{

    public Test1(String name) {
        super(name);
    }

    @Test
    public void testCheckInsertUser()
    {
//        StringUtils  stringUtils=new StringUtils();
        assertEquals("Test",true,StringUtils.checkVid(";"));
    }

    public static void main(String[] args) {
        Test1 test1= new Test1("test");
        test1.testCheckInsertUser();
    }
}
