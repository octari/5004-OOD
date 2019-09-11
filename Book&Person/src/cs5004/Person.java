
package cs5004;

/**
 * This class represents a person The person has a first name, last name, and year of birth and
 * contact information.
 */

public class Person {
  /**
   * First name for this Person.
   */
  private String firstName;
  /**
   * Last name for this Person.
   */
  private String lastName;
  /**
   * Year this Person was born.
   */
  private int yearOfBirth;
  /**
   * Telephone number of this person.
   */
  private String telephone;
  /**
   * Email of this person.
   */
  private Email mail;

  /**
   * Constructs a Person object and initializes it to the given first name, last name and year of
   * birth.
   *
   * @param firstName   the first name of this person
   * @param lastName    the last name of this person
   * @param yearOfBirth the year of birth of this person
   * @param telephone   the telephone number of this person
   * @param mail        the Email address of this person
   */

  public Person(String firstName, String lastName, int yearOfBirth, String telephone, Email mail) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.yearOfBirth = yearOfBirth;
    this.telephone = telephone;
    this.mail = mail;
  }

  /**
   * Get the first name of this person.
   *
   * @return the first name of this person
   */
  public String getFirstName() {
    return this.firstName;
  }

  /**
   * Return the last name of this person.
   *
   * @return the last name of this person
   */

  public String getLastName() {
    return this.lastName;
  }

  /**
   * Return the year of birth of this person.
   *
   * @return the year of birth of this person
   */
 // public int getYearOfBirth() {
//    return this.yearOfBirth;
//  }

  /**
   * Return the telephone number of this person.
   *
   * @return the telephone number of this person
   */
  public String getTelephone() {
    return this.telephone;
  }

  /**
   * Get the email of this person.
   *
   * @return the email address of this person
   */
  public Email getMail() {
    return this.mail;
  }

}
