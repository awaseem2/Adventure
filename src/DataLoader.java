import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataLoader {

    /**Takes the url and uses Unirest to convert the body to a json and then returns that in the
     * form of a Map object.
     *
     * @param url the json file url.
     * @return a Map object initialized from the json file.
     * @throws UnirestException when the url can't be read.
     */
    public static void initializeMapFromUrl(String url) throws UnirestException{
        HttpRequest request = Unirest.get(url);
        String jsonFromUrl = request.asJson().getBody().toString();
        Gson gson = new Gson();
        Environment.setMap(gson.fromJson(jsonFromUrl, Map.class));
    }

}
