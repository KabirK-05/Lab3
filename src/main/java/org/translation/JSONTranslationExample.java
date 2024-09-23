package org.translation;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A minimal example of reading and using the JSON data from resources/sample.json.
 */
public class JSONTranslationExample {

    public static final int CANADA_INDEX = 30;
    public static final int ALPHA_THREE_ROW = 2;
    public static final int ID_ROW = 3;
    private final JSONArray jsonArray;

    // Note: CheckStyle is configured so that we are allowed to omit javadoc for constructors
    public JSONTranslationExample() {
        try {
            // this next line of code reads in a file from the resources folder as a String,
            // which we then create a new JSONArray object from.
            String jsonString = Files.readString(Paths.get(getClass().getClassLoader()
                    .getResource("sample.json").toURI()));
            this.jsonArray = new JSONArray(jsonString);
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * Returns the Spanish translation of Canada.
     * @return the Spanish translation of Canada
     */
    public String getCanadaCountryNameSpanishTranslation() {
        JSONObject canada = jsonArray.getJSONObject(CANADA_INDEX);
        return canada.getString("es");
    }

    // TODO Task: Complete the method below to generalize the above to get the country name
    //            for any country code and language code from sample.json.

    /**
     * Returns the name of the country based on the provided country and language codes.
     * @param countryCode the country, as its three-letter code.
     * @param languageCode the language to translate to, as its two-letter code.
     * @return the translation of country to the given language or "Country not found" if there is no translation.
     */
    public String getCountryNameTranslation(String countryCode, String languageCode) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject country = jsonArray.getJSONObject(i);

            if (country.getString("alpha3").toLowerCase().equals(countryCode)
                    && country.getString(languageCode) != null) {
                return country.getString(languageCode);
            }
        }
        return "Country not found";
    }

    /**
     * Prints the Spanish translation of Canada.
     * @param args not used
     */
    public static void main(String[] args) {
        JSONTranslationExample jsonTranslationExample = new JSONTranslationExample();

        System.out.println(jsonTranslationExample.getCanadaCountryNameSpanishTranslation());
        String translation = jsonTranslationExample.getCountryNameTranslation("can", "es");
        System.out.println(translation);
    }
}
