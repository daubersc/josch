package josch.services.interfaces;

import java.io.IOException;

/**
 * This is the interface for JSON Schema containment services that run on web servers.
 *
 * @author Kai Dauberschmidt
 */
public interface IWebComparisonService {

    /**
     * Checks whether two schemas are in a containment relationship and returns the relationship type
     * as a String. The following containment relationships possibly exist:
     *
     * <ul>
     *   <li>s1 is subset of s2
     *   <li>s1 is superset of s2
     *   <li>s1 is semantically equivalent to s2
     *   <li>s1 is contrary to s2
     * </ul>
     *
     * @param s1 The first JSON Schema as a String.
     * @param s2 The second JSON Schema as a String.
     * @param uri The uri on which the web service runs on.
     * @return The containment relationship or an error on exceptions.
     */
    String contains(String s1, String s2, String uri);
}
