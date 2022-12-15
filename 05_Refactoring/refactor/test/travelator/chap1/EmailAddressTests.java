package travelator.chap1;

import org.junit.jupiter.api.Test;
import travelator.chap1.EmailAddress_java;
import travelator.chap1.EmailAddress_kt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailAddressTests {
    @Test
    public void parsing() {
        assertEquals(
                new EmailAddress_java("fred", "example.com"),
                EmailAddress_java.parse("fred@example.com")
        );

        assertEquals(
                new EmailAddress_kt("h970126", "gmail.com"),
                EmailAddress_kt.parse("h970126@gmail.com")
        );
   }

    @Test
    public void parsingFailures() {
        assertThrows(
                IllegalArgumentException.class,
                () -> EmailAddress_kt.parse("@")
        );
    }
}
