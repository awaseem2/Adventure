import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

public class DataLoader {

    /**Takes the url and uses Unirest to convert the body to a json and then returns that in the
     * form of a Map object.
     *
     * @param url the json file url.
     * @return a Map object initialized from the json file.
     * @throws UnirestException
     */
    public static void initializeMapFromUrl(String url) throws UnirestException{
        HttpRequest request = Unirest.get(url);
        String jsonFromUrl = request.asJson().getBody().toString();
        Gson gson = new Gson();
        Environment.setMap(gson.fromJson(UtilityFunctions.getFileContentsAsString("SiebelDatingSimulator.json"), Map.class));
    }

}
