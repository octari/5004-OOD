import static org.junit.Assert.assertEquals;

import cs5004.Book;
import cs5004.Email;
import cs5004.Person;

import org.junit.Before;
import org.junit.Test;

public class BookTest {
  private Book abc;
  private Person Abc;
  private Email j;

  @Before
  public void setUp() {
    j = new Email("john", "@mail.com");
    Abc = new Person("Abc", "Abc", 1945, "1234561",
            j);
    abc = new Book("John", Abc, 19.45f);
  }

  @Test
  public void testTile() {
    assertEquals("John", abc.getTitle());

  }

  @Test
  public void testAuthor() {
    assertEquals(Abc, abc.getAuthor());
  }


  @Test
  public void testPrice() {
    assertEquals(19.45f, abc.getPrice(), 0.01);
  }


}