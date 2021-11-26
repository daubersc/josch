package josch.services.comparison.web;

import josch.services.interfaces.IWebComparisonService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * This {@code WebComparisonServices} uses a web service to handle the comparison, i.e. JSON Schema
 * containment. The differentiation of the tools happens to depend on the url, but the request is
 * designed the same way for both services, therefore, we don't need separate versions.
 *
 * @author Kai Dauberschmidt
 */
public class WebComparisonService implements IWebComparisonService {

  /** {@inheritDoc} */
  public String contains(String s1, String s2, String uri) {
    HttpClient client = HttpClient.newHttpClient();
    String data = "{" + "\"s1\":" + s1 + "," + "\"s2\":" + s2 + "}";

    // Build the request.
    try {
    HttpRequest request =
        HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(data))
            .build();

      // Send and get the response synchronously and return its body.
      try {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
      } catch (IOException | InterruptedException e) {
        return e.getMessage();
      }
    } catch (IllegalArgumentException e) {
      return e.getMessage();
    }


  }
}
