package au.com.belong.phonenumberexplorer;

import au.com.belong.phonenumberexplorer.model.CustomerPhoneNumber;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PortMappingResponseTest {

    private CustomerPhoneNumber customerPhoneNumber;

    @Before
    public void setUp() {
        customerPhoneNumber = new CustomerPhoneNumber();
        customerPhoneNumber.setCustId("asasas");
     //   customerPhoneNumber.setEndPort("(EQUIP=SWAAS0000742)(CARD=1)(MDA=1)(PORT=1)");
    }


    @Test
    public void testStartPort() {
        assertEquals("SWEAS0000011 1/5", customerPhoneNumber.getPhoneNum());
    }
/*
    @Test
    public void testEndPort() {
        assertEquals("SWAAS0000742 1/1", portMappingResponse.getEndPort());
    }*/
}
