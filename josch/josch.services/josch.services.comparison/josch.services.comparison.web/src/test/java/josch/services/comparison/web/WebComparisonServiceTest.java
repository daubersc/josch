package josch.services.comparison.web;

import josch.model.enums.EContainmentRelations;
import josch.test.interfaces.AbstractBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * This class is used to test the comparison service using web services. It just tests the
 * connection and the expected result. Note that the web service has to be running at the given uri.
 *
 * @author Kai Dauberschmidt
 */
public class WebComparisonServiceTest extends AbstractBaseTest {

    /**
     * Not necessary for web tests.
     */
    @Override
    public void setup() {
    }

    /**
     * Checks for equivalence on reflexive Schema and correct call of script.
     */
    @Test
    public void contains_ReflexiveS1() {
        // Make sure the jss service is running on the given uri.
        String uri = "http://127.0.0.1:8833/compare";
        WebComparisonService service = new WebComparisonService();
        String result = service.contains(JSON_SCHEMA, JSON_SCHEMA, uri);

        Assertions.assertEquals(EContainmentRelations.EQV.toString(), result);
    }

    /**
     * Checks for errors on wrong uri.
     */
    @Test
    public void contains_falseUri() {
        String uri = "foobar";
        WebComparisonService service = new WebComparisonService();
        String result = service.contains(JSON_SCHEMA, JSON_SCHEMA, uri);

        Assertions.assertEquals("URI with undefined scheme", result);
    }
}
