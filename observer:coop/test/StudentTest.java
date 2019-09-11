import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Test for the subjects and its observers.
 */
public class StudentTest {
  private Student jack;
  private Student mike;
  private COOPObserver coop;
  private StandingObserver standing;
  private CoreObserver core;
  private COOPObserver coopMike;
  private StandingObserver standingMike;
  private CoreObserver coreMike;


  @Before

  public void setUp() {
    jack = new Student("jack");//Align student.

    coop = new COOPObserver();
    standing = new StandingObserver();
    core = new CoreObserver();


    jack.register(coop);
    jack.register(standing);
    jack.register(core);

    mike = new Student("mike");//General student.

    coopMike = new COOPObserver();
    standingMike = new StandingObserver();
    coreMike = new CoreObserver();

    mike.register(coopMike);
    mike.register(standingMike);
    mike.register(coreMike);
  }

  @Test
  public void testAlign() {
    assertFalse(coop.getStatus());
    assertFalse(standing.getStatus());
    assertFalse(core.getStatus());
    assertEquals(0, jack.getGPA(), 0.001);

    jack.change("CS 5600", "A");
    assertTrue(standing.getStatus()); //gpa is above 3.0.
    assertFalse(core.getStatus()); //the student has not fulfilled all core requirements.
    assertFalse(coop.getStatus()); //gpa is above 3.0 but credits not enough.

    jack.change("CS 5800", "D-");
    jack.change("CS 5004", "C+");
    assertFalse(standing.getStatus()); //gpa is less than 3.0.
    assertFalse(core.getStatus()); //the student has not fulfilled all core requirements.
    assertFalse(coop.getStatus()); //credits not enough.

    jack.change("CS 5002", "A");
    assertFalse(standing.getStatus()); ////gpa is less than 3.0.
    assertFalse(core.getStatus()); //the student has not fulfilled all core requirements.
    assertFalse(coop.getStatus()); //credits enough but the cumulative GPA is less than 3.0.

    jack.change("CS 5600", "A");//retaking a core course and improving gpa.
    jack.change("CS 5800", "A");//retaking a core course and improving gpa.
    jack.change("CS 5004", "A");//retaking a core course and improving gpa.
    assertTrue(standing.getStatus()); //gpa is equal or above 3.0.
    assertFalse(core.getStatus()); //False because has not learn CS 5500
    assertTrue(coop.getStatus()); //credits enough and the cumulative GPA is above 3.0.


    jack.change("CS 5500", "B-");
    assertTrue(standing.getStatus()); //gpa is above 3.0.
    assertFalse(core.getStatus()); //False because CS 5500's grade is B-.
    assertTrue(coop.getStatus()); //credits enough and the cumulative GPA is above 3.0.

    jack.change("CS 5500", "A"); //retaking a core course
    assertTrue(standing.getStatus()); //gpa is above 3.0.
    assertTrue(core.getStatus()); //the student has fulfilled all core requirements.
    assertTrue(coop.getStatus()); //credits enough and the cumulative GPA is above 3.0.


  }

  @Test
  public void testGeneral() {
    assertFalse(coopMike.getStatus());
    assertFalse(standingMike.getStatus());
    assertFalse(coreMike.getStatus());
    assertEquals(0, mike.getGPA(), 0.001);

    mike.change("CS 5600", "A");
    assertTrue(standingMike.getStatus()); //gpa is above 3.0.
    assertFalse(coreMike.getStatus()); //the student has not fulfilled all core requirements.
    assertFalse(coopMike.getStatus()); //gpa is above 3.0 but credits not enough.

    mike.change("CS 5800", "D-");
    mike.change("CS 5010", "C+");
    assertFalse(standingMike.getStatus()); //gpa is less than 3.0.
    assertFalse(coreMike.getStatus()); //the student has not fulfilled all core requirements.
    assertFalse(coopMike.getStatus()); //credits not enough.

    mike.change("CS 5002", "A");
    assertFalse(standingMike.getStatus()); ////gpa is less than 3.0.
    assertFalse(coreMike.getStatus()); //the student has not fulfilled all core requirements.
    assertFalse(coopMike.getStatus()); //credits enough but the cumulative GPA is less than 3.0.

    mike.change("CS 5600", "A");//retaking a core course and improving gpa.
    mike.change("CS 5800", "A");//retaking a core course and improving gpa.
    mike.change("CS 5010", "A");//retaking a core course and improving gpa.
    assertTrue(standingMike.getStatus()); //gpa is equal or above 3.0.
    assertFalse(coreMike.getStatus()); //False because has not learn CS 5500
    assertTrue(coopMike.getStatus()); //credits enough and the cumulative GPA is above 3.0.


    mike.change("CS 5500", "B-");
    assertTrue(standingMike.getStatus()); //gpa is above 3.0.
    assertFalse(coreMike.getStatus()); //False because CS 5500's grade is B-.
    assertTrue(coopMike.getStatus()); //credits enough and the cumulative GPA is above 3.0.

    mike.change("CS 5500", "A"); //retaking a core course
    assertTrue(standingMike.getStatus()); //gpa is above 3.0.
    assertTrue(coreMike.getStatus()); //the student has fulfilled all core requirements.
    assertTrue(coopMike.getStatus()); //credits enough and the cumulative GPA is above 3.0.


  }


}