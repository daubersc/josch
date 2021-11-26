package de.uni.passau.fim.sdbs.josch.web.model;

import de.uni.passau.fim.sdbs.josch.web.utils.EResponses;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class is used to validate JSON Schemas. It is separated from the controllers in order to
 * keep them simple.
 *
 * @author Kai Dauberschmidt
 */
public class SchemaValidator  {

    /** JSON Schema Draft-04 */
    private static final String DRAFT =
            String.join(
                    "\n",
                    new String[] {
                            "{"
                                    + "  \"$schema\": \"http://json-schema.org/draft-04/schema#\","
                                    + "  \"description\": \"Modified JSON Schema draft v4 that includes the optional '$ref' and 'format'\","
                                    + "  \"definitions\": {"
                                    + "    \"schemaArray\": {"
                                    + "      \"type\": \"array\","
                                    + "      \"minItems\": 1,"
                                    + "      \"items\": { \"$ref\": \"#\" }"
                                    + "    },"
                                    + "    \"positiveInteger\": {"
                                    + "      \"type\": \"integer\","
                                    + "      \"minimum\": 0"
                                    + "    },"
                                    + "    \"positiveIntegerDefault0\": {"
                                    + "      \"allOf\": [ { \"$ref\": \"#/definitions/positiveInteger\" }, { \"default\": 0 } ]"
                                    + "    },"
                                    + "    \"simpleTypes\": {"
                                    + "      \"enum\": [ \"array\", \"boolean\", \"integer\", \"null\", \"number\", \"object\", \"string\" ]"
                                    + "    },"
                                    + "    \"stringArray\": {"
                                    + "      \"type\": \"array\","
                                    + "      \"items\": { \"type\": \"string\" },"
                                    + "      \"minItems\": 1,"
                                    + "      \"uniqueItems\": true"
                                    + "    }"
                                    + "  },"
                                    + "  \"type\": \"object\","
                                    + "  \"properties\": {"
                                    + "    \"id\": {"
                                    + "      \"type\": \"string\","
                                    + "      \"format\": \"uri\""
                                    + "    },"
                                    + "    \"$schema\": {"
                                    + "      \"type\": \"string\","
                                    + "      \"format\": \"uri\""
                                    + "    },"
                                    + "    \"$ref\": {"
                                    + "      \"type\": \"string\""
                                    + "    },"
                                    + "    \"format\": {"
                                    + "      \"type\": \"string\""
                                    + "    },"
                                    + "    \"title\": {"
                                    + "      \"type\": \"string\""
                                    + "    },"
                                    + "    \"description\": {"
                                    + "      \"type\": \"string\""
                                    + "    },"
                                    + "    \"default\": { },"
                                    + "    \"multipleOf\": {"
                                    + "      \"type\": \"number\","
                                    + "      \"minimum\": 0,"
                                    + "      \"exclusiveMinimum\": true"
                                    + "    },"
                                    + "    \"maximum\": {"
                                    + "      \"type\": \"number\""
                                    + "    },"
                                    + "    \"exclusiveMaximum\": {"
                                    + "      \"type\": \"boolean\","
                                    + "      \"default\": false"
                                    + "    },"
                                    + "    \"minimum\": {"
                                    + "      \"type\": \"number\""
                                    + "    },"
                                    + "    \"exclusiveMinimum\": {"
                                    + "      \"type\": \"boolean\","
                                    + "      \"default\": false"
                                    + "    },"
                                    + "    \"maxLength\": { \"$ref\": \"#/definitions/positiveInteger\" },"
                                    + "    \"minLength\": { \"$ref\": \"#/definitions/positiveIntegerDefault0\" },"
                                    + "    \"pattern\": {"
                                    + "      \"type\": \"string\","
                                    + "      \"format\": \"regex\""
                                    + "    },"
                                    + "    \"additionalItems\": {"
                                    + "      \"anyOf\": ["
                                    + "        { \"type\": \"boolean\" },"
                                    + "        { \"$ref\": \"#\" }"
                                    + "      ],"
                                    + "      \"default\": { }"
                                    + "    },"
                                    + "    \"items\": {"
                                    + "      \"anyOf\": ["
                                    + "        { \"$ref\": \"#\" },"
                                    + "        { \"$ref\": \"#/definitions/schemaArray\" }"
                                    + "      ],"
                                    + "      \"default\": { }"
                                    + "    },"
                                    + "    \"maxItems\": { \"$ref\": \"#/definitions/positiveInteger\" },"
                                    + "    \"minItems\": { \"$ref\": \"#/definitions/positiveIntegerDefault0\" },"
                                    + "    \"uniqueItems\": {"
                                    + "      \"type\": \"boolean\","
                                    + "      \"default\": false"
                                    + "    },"
                                    + "    \"maxProperties\": { \"$ref\": \"#/definitions/positiveInteger\" },"
                                    + "    \"minProperties\": { \"$ref\": \"#/definitions/positiveIntegerDefault0\" },"
                                    + "    \"required\": { \"$ref\": \"#/definitions/stringArray\" },"
                                    + "    \"additionalProperties\": {"
                                    + "      \"anyOf\": ["
                                    + "        { \"type\": \"boolean\" },"
                                    + "        { \"$ref\": \"#\" }"
                                    + "      ],"
                                    + "      \"default\": { }"
                                    + "    },"
                                    + "    \"definitions\": {"
                                    + "      \"type\": \"object\","
                                    + "      \"additionalProperties\": { \"$ref\": \"#\" },"
                                    + "      \"default\": { }"
                                    + "    },"
                                    + "    \"properties\": {"
                                    + "      \"type\": \"object\","
                                    + "      \"additionalProperties\": { \"$ref\": \"#\" },"
                                    + "      \"default\": { }"
                                    + "    },"
                                    + "    \"patternProperties\": {"
                                    + "      \"type\": \"object\","
                                    + "      \"additionalProperties\": { \"$ref\": \"#\" },"
                                    + "      \"default\": { }"
                                    + "    },"
                                    + "    \"dependencies\": {"
                                    + "      \"type\": \"object\","
                                    + "      \"additionalProperties\": {"
                                    + "        \"anyOf\": ["
                                    + "          { \"$ref\": \"#\" },"
                                    + "          { \"$ref\": \"#/definitions/stringArray\" }"
                                    + "        ]"
                                    + "      }"
                                    + "    },"
                                    + "    \"enum\": {"
                                    + "      \"type\": \"array\","
                                    + "      \"minItems\": 1,"
                                    + "      \"uniqueItems\": true"
                                    + "    },"
                                    + "    \"type\": {"
                                    + "      \"anyOf\": ["
                                    + "        { \"$ref\": \"#/definitions/simpleTypes\" },"
                                    + "        {"
                                    + "          \"type\": \"array\","
                                    + "          \"items\": { \"$ref\": \"#/definitions/simpleTypes\" },"
                                    + "          \"minItems\": 1,"
                                    + "          \"uniqueItems\": true"
                                    + "        }"
                                    + "      ]"
                                    + "    },"
                                    + "    \"allOf\": { \"$ref\": \"#/definitions/schemaArray\" },"
                                    + "    \"anyOf\": { \"$ref\": \"#/definitions/schemaArray\" },"
                                    + "    \"oneOf\": { \"$ref\": \"#/definitions/schemaArray\" },"
                                    + "    \"not\": { \"$ref\": \"#\" }"
                                    + "  },"
                                    + "  \"dependencies\": {"
                                    + "    \"exclusiveMaximum\": [ \"maximum\" ],"
                                    + "    \"exclusiveMinimum\": [ \"minimum\" ]"
                                    + "  },"
                                    + "  \"default\": { }"
                                    + "}"
                    });


    /**
     * Validates a schema against the meta schema of draft-04.
     *
     * @param schema The actual schema to validate.
     * @return a {@code response code} that represents valid or an exception.
     */
    public static EResponses validate(String schema) {
        try {
            JSONObject draft = new JSONObject(DRAFT);
            JSONObject jsonSchema = new JSONObject(schema);

            // Validate schema against the draft.
            Schema draftSchema = SchemaLoader.load(draft);
            draftSchema.validate(jsonSchema);
            return EResponses.VALID;

        } catch (JSONException e) {
            return EResponses.JSON_EXCEPTION;
        } catch (ValidationException e) {
            return EResponses.DRAFT_EXCEPTION;
        }
    }
}
