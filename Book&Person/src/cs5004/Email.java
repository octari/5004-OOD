package cs5004;
/**
 * This class represents an Email address. The email address has a username and a domain.
 */
public class Email {
  /** the username of the email address */
  private String username;
  /** the domain of the email address */
  private String domain;

  /**
   * Construct an Email object and initialize it
   * to the username and domain.
   * @param username the username of the email address
   * @param domain the domain of the email address
   */

  public Email(String username, String domain) {
    this.username = username;
    this.domain = domain;
  }

  /**
   * Get the username fo the email address
   * @return the username of the email address
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * Get the domain of the email address
   * @return the domain of the email address
   */
  public String getDomain() {
    return this.domain;
  }



}
