package de.uni.passau.fim.sdbs.josch.web.utils;

/**
 * This class encapsulates the containment check request parameters as POJOs and it is used as a
 * {@link org.springframework.web.bind.annotation.RequestBody}. POJOs are required in order to
 * to autogenerate this from a JSON request body.
 *
 * @author Kai Dauberschmidt
 */
public class CheckRequest {

    /** The JSON Schema S1 as a String. */
    private String s1;

    /** The JSON Schema S2 as a String. */
    private String s2;

    /** The abbreviation of the containment tool. */
    private String tool;

    /**
     * Gets the JSON Schema S1.
     *
     * @return The JSON Schema S1 as a String.
     */
    public String getS1() {
        return s1;
    }

    /**
     * Sets the JSON Schema S1.
     *
     * @param s1 The JSON Schema S1 as a String.
     */
    public void setS1(String s1) {
        this.s1 = s1;
    }

    /**
     * Gets the JSON Schema S2.
     *
     * @return The JSON Schema S2 as a String.
     */
    public String getS2() {
        return s2;
    }

    /**
     * Sets the JSON Schema S1.
     *
     * @param s2 The JSON Schema S2 as a String.
     */
    public void setS2(String s2) {
        this.s2 = s2;
    }

    /**
     * Gets the abbreviation of the containment tool.
     *
     * @return The abbreviation of the containment tool as a String.
     */
    public String getTool() {
        return tool;
    }

    /**
     * Sets the abbreviation of the containment tool.
     *
     * @param tool The abbreviation of the containment tool as a String.
     */
    public void setTool(String tool) {
        this.tool = tool;
    }
}
