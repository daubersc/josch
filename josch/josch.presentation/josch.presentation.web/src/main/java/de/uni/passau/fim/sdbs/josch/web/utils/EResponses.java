package de.uni.passau.fim.sdbs.josch.web.utils;

/**
 *  This enum is used to group response constant messages in an easy way. It groups the
 *  containment relation responses as well as validation responses. Furthermore it allows to get
 *  the css classes dependent on the response using
 *  {@link EResponses#getCssClasses(EResponses)} as well as the notification message
 *  dependent on the response using {@link EResponses#getMeaning(EResponses)}. <br>
 *
 *      Note that all responses are half sentences in order to set the identifier accordingly. E.g.
 *      when used for validation of Schema s1 it's message has to be appended to "The Schema
 *      S<sub>1</sub> "
 *
 * @author Kai Dauberschmidt
 */
public enum EResponses {

  /** The response for validity. */
  VALID("is valid."),

  /** The response for invalidity. */
  INVALID("is not valid."),

  /** The response for invalid JSON Syntax. */
  JSON_EXCEPTION("has no valid JSON syntax. "),

  /** The response for no JSON Schema drafts. */
  DRAFT_EXCEPTION("is no valid JSON Schema draft-04. "),

  /** The response for the equivalence containment relation. */
  EQV("equivalent"),

  /** The response for the incomparable containment relation. */
  NEQV("incomparable"),

  /** The response for the subset containment relation. */
  SUB("subset"),

  /** The response for the superset containment relation. */
  SUP("superset");

  /** The message of the response */
  private final String MESSAGE;

  /**
   * Constructs a response with a message.
   *
   * @param message The message of the response.
   */
  EResponses(String message) {
    this.MESSAGE = message;
  }

  /**
   * Gets the Response based on its message or {@code INVALID} if none is found.
   *
   * @return The respective {@link EResponses} or {@code INVALID} if none is found.
   */
  public static EResponses getMapping(String message) {
    for (EResponses mapping : EResponses.values()) {
      if (message.equals(mapping.MESSAGE)) {
        return mapping;
      }
    }
    return INVALID;
  }

  /**
   * Gets the Response's message.
   *
   * @return The message of this response as a String.
   */
  public String getMessage() {
    return MESSAGE;
  }


  /**
   * Gets meaning of the containment relation.
   *
   * @param relation The relation to determine its meaning.
   * @return The meaning of the relation as a String.
   */
  public static String getMeaning(EResponses relation) {
    return switch (relation) {
      case EQV -> "The schemas are equivalent.  That means all documents that validate against " +
              "Schema S<sub>1</sub> also validate against Schema S<sub>2</sub> and vice versa.";
      case NEQV -> "The schemas are incomparable. That means there exist documents that validate" +
              " against Schema S<sub>1</sub> but not to Schema S<sub>2</sub> and vice versa.";
      case SUB -> "The Schema S<sub>2</sub> contains the Schema S<sub>1</sub>. That means all " +
              "documents that " +
              "validate against Schema S<sub>1</sub> also validate against Schema S<sub>2</sub> " +
              "but not vice versa.";
      case SUP -> "The Schema S<sub>1</sub> contains the Schema S<sub>2</sub>. That means all " +
              "documents that " +
              "validate against the Schema S<sub>2</sub> also validate against Schema " +
              "S<sub>1</sub> " +
              "but not vice versa.";
      default -> "Error while accessing the containment service. Please contact the admin.";
    };
  }

  /**
   * Gets the containment relation's icon css classes.
   *
   * @param relation The relation or default the response.
   * @return The css classes of the icon as a String.
   */
  public static String getCssClasses(EResponses relation) {
    String cssClasses = switch (relation) {
      case EQV -> "pi-eqv";
      case NEQV -> "pi-neqv";
      case SUB -> "pi-sub";
      case SUP -> "pi-sup";
      default -> "pi-is-eqv";
    };
    cssClasses += " pc-primary";
    return cssClasses;
  }
}
