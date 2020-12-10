import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

public class ContactTest {

    private static final Contact testContact1 = new Contact();
    private static final Contact testContact2 = new Contact("Irene", "4152231231");

    @Before
    public void setUp() throws Exception {
        testContact1.setName("Jen");
        testContact1.setNumber("4151112233");
    }

    @Test
    public void getName_equals_RetrievesCorrectInfo () {
        assertEquals("Jen", testContact1.getName());
        assertEquals("Irene", testContact2.getName());
        assertEquals("4151112233", testContact1.getNumber());
        assertEquals("4152231231", testContact2.getNumber());
    }

    public void modifyNumber_equals_ReturnsCorrectFormat() {
        assertEquals("(415)-111-2233", testContact1.modifyNumber());
        assertEquals("(415)-223-1231", testContact2.modifyNumber());
        assertNotEquals("4151112233", testContact1.modifyNumber());
        assertNotEquals("4152231231", testContact2.modifyNumber());
    }

    public void toString_equals_CorrectStringOutput() {
        assertEquals("Name: Jen\n Number: (415)-111-2233", testContact1.toString());
        assertEquals("Name: Irene\n Number: (415)-223-1231", testContact1.toString());
    }

}
