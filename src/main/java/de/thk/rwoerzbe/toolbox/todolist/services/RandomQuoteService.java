package de.thk.rwoerzbe.toolbox.todolist.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Proxy class that implements calls to a random quote service
 */
@Service
public class RandomQuoteService {

    private String quote = null;

    /**
     * Implements wrapper code that calls an external web service with
     * returns a random Chuck Norris quote of the day
     * @return A Chuck Norris quote
     */
    public String getRandomQuote() {
        if (quote == null) {
            URL url;
            try {
                url = new URL("https://api.api-ninjas.com/v1/chucknorris");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("accept", "application/json");
                connection.setRequestProperty("X-Api-Key", "FGe/+3E4pAEsCJ7V5sLCJw==3xWoJ34AiewuFJta");
                InputStream responseStream = connection.getInputStream();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(responseStream);
                quote = root.path("joke").asText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return quote;
    }

}