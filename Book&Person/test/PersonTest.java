import static org.junit.Assert.assertEquals;

import cs5004.Email;
import cs5004.Person;

import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the Person class.
 */
public class PersonTest {

  private Person john;
  private Email j;

  @Before
  public void setUp() {

    j = new Email("john", "@mail.com");
    john = new Person("John", "Doe", 1945, "1234561", j);
  }

  @Test
  public void testFirst() {
    assertEquals("John", john.getFirstName());

  }

  @Test
  public void testSecond() {
    assertEquals("Doe", john.getLastName());
  }

  @Test
  public void testYearOfBirth() {
    assertEquals(1945, john.getYearOfBirth());
  }

  @Test
  public void testTelephone() {
    assertEquals("1234561", john.getTelephone());
  }

  @Test
  public void testEmail() {
    assertEquals(j, john.getMail());
  }

}