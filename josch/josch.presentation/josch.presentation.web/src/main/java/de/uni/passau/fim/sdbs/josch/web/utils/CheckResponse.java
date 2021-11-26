package de.uni.passau.fim.sdbs.josch.web.utils;

/**
 * This class encapsulates the containment check response parameters as POJOs and it is used as a
 * {@link org.springframework.web.bind.annotation.ResponseBody}. POJOs are required in order to to
 * autogenerate a JSON response body. <br>
 *
 * @author Kai Dauberschmidt
 */
public class CheckResponse {

  /**
   * The icon of the containment relation. To be precise: The css classes of the svg path that
   * represents the containment relation visually.
   */
  private String icon;

  /** The response message to display on successful callback. */
  private String message;

  /**
   * Gets the css classes of the svg path that represents the containment relation visually as a
   * String.
   *
   * @return a whitespace trimmable String of the css classes.
   */
  public String getIcon() {
    return icon;
  }

  /**
   * Sets the css classes of the svg path that represents the containment relation visually as a
   * String. The individual classes have to be concatenated in a single String and have to be
   * separated by whitespace only.
   *
   * @param icon the css classes of the icon as a concatenated String.
   */
  public void setIcon(String icon) {
    this.icon = icon;
  }

  /**
   * Gets the message that informs the user about the containment relation or about possible errors.
   *
   * @return The notification message as a String.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the message that informs the user about the containment relation or about possible errors.
   *
   * @param message The notification message as a String.
   */
  public void setMessage(String message) {
    this.message = message;
  }
}
