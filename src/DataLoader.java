import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;

public class DataLoader {

    /**Takes the url and uses Unirest to convert the body to a json and then returns that in the
     * form of a Layout object.
     *
     * @param url the json file url.
     * @return a Layout object initialized from the json file.
     * @throws UnirestException when the url can't be read.
     */
    public static void initializeMapFromUrl(String url) throws UnirestException{
        HttpRequest request = Unirest.get(url);
        String jsonFromUrl = request.asJson().getBody().toString();
        Gson gson = new Gson();
        Map.setLayout(gson.fromJson(jsonFromUrl, Layout.class));
        Map.getLayout().initializeMonsterMap();
    }
}
