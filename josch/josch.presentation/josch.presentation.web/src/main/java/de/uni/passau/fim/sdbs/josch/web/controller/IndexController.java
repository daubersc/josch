package de.uni.passau.fim.sdbs.josch.web.controller;

import de.uni.passau.fim.sdbs.josch.web.model.SchemaValidator;
import de.uni.passau.fim.sdbs.josch.web.utils.CheckRequest;
import de.uni.passau.fim.sdbs.josch.web.utils.CheckResponse;
import de.uni.passau.fim.sdbs.josch.web.utils.EResponses;
import josch.model.dto.ConnectionInfoDto;
import josch.model.dto.SchemaDto;
import josch.model.dto.SettingsDto;
import josch.model.enums.EDatabaseSystems;
import josch.services.factory.AbstractServiceFactory;
import josch.services.interfaces.IDatabaseService;
import josch.services.interfaces.IWebComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is the {@code Controller} class for the {@code index.html} page as indicated by
 * the @Controller annotation. The Controller contains the business logic of the specified web page.
 * <br>
 * The attributes annotated with @Value represent values from ../resources/application.properties
 * and are injected by Spring directly.
 *
 * @author Kai Dauberschmidt
 */
@Controller
@SessionAttributes("dto")
public class IndexController {

  /** Injected URL for JSS */
  @Value("${josch.containment.jss}")
  private String jssUrl;

  /** Injected URL for IJS */
  @Value("${josch.containment.ijs}")
  private String ijsUrl;

  /** The system's database service */
  private final IDatabaseService dbService;

  /** The system's web comparison service */
  private final IWebComparisonService comparisonService;

  /**
   * Constructs the Controller. The {@code Autowired} annotation allows spring to resolve and inject
   * collaborating beans into this.
   *
   * @param mongoDbUri The connection URI as taken from {@code application.properties}.
   * @param collection The currently inspected collection from {@code application.properties}.
   */
  @Autowired
  public IndexController(
      @Value("${spring.data.mongodb.uri}") String mongoDbUri,
      @Value("${josch.mongodb.collection}") String collection) {
    // create empty settings because they aren't exactly used.
    SettingsDto settings = new SettingsDto();
    settings.setDbms(EDatabaseSystems.MONGO);

    // Create and append the connection information given as uri in application.properties.
    ConnectionInfoDto connectionInfoDto = new ConnectionInfoDto(mongoDbUri);
    connectionInfoDto.setTimeout(1000); // 10.000 ms => 10 seconds before time out.
    settings.setConnectionInfo(connectionInfoDto);

    // Set the database service.
    dbService = AbstractServiceFactory.getDatabaseService(settings);

    // Load and set the collection with the name that is specified in application.properties.
    settings.setCollection(dbService.getCollection(collection));

    // Get the containment service.
    comparisonService = AbstractServiceFactory.getComparisonService();
  }

  /** Handles navigation (GET method mapping) for the index page. */
  @GetMapping(value = {"/index", "/"})
  public String navigate() {
    return "index";
  }

  /**
   * This method processes the containment check POST requests. It is being accessed by Ajax
   * (resources/static/js/containmentCheck.js) and therefore only accepts and produces {@link
   * MediaType#APPLICATION_JSON_VALUE}.
   *
   * @param request A {@link CheckRequest} that contains the two schemas as well as selected tool in
   *     the request's body (i.e. the data or -d) as JSON object.
   * @return Returns a {@link CheckResponse} that contains the new icon and the response message in
   *     the responses' body (i.e. the data or -d) as JSON object.
   */
  @RequestMapping(
      value = "/processContainment",
      method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody CheckResponse processContainment(@RequestBody CheckRequest request) {
    // Setup the local variables for request and response.
    String s1 = request.getS1();
    String s2 = request.getS2();
    String serviceUrl = (request.getTool().equals("jss")) ? jssUrl : ijsUrl;
    CheckResponse response = new CheckResponse();

    // Validate the schemas.
    String message = validate(s1, 1);
    message += validate(s2, 2);

    // If valid process service.
    if (message.equals("")) {
      // Process the service.
      message = comparisonService.contains(s1, s2, serviceUrl);
      EResponses relation = EResponses.getMapping(message);

      // Process response.
      response.setIcon(EResponses.getCssClasses(relation));
      response.setMessage(EResponses.getMeaning(relation));
    } else { // Else use default icon and previous message.
      response.setIcon(EResponses.getCssClasses(EResponses.JSON_EXCEPTION));
      response.setMessage(message);
    }

    return response;
  }

  /**
   * Validates a given JSON Schema as a String.
   *
   * @param schema The JSON Schema as a String.
   * @param index The index of the schema
   * @return Returns the empty String on valid schema or an error message.
   */
  private String validate(String schema, int index) {
    EResponses response = SchemaValidator.validate(schema);
    boolean isValid = response == EResponses.VALID;
    return isValid ? "" : "The Schema S<sub>" + index + "</sub> " + response.getMessage();
  }
  /**
   * Loads the schemas from the database and adds them to the application's model.
   *
   * @return Returns the {@link List<SchemaDto> list of dtos} as loaded from the database and adds
   *     it to the model's attributes.
   */
  @ModelAttribute("schemas")
  public List<SchemaDto> loadSchemas() {
    return dbService.getSchemas();
  }
}
