package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "014712821";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        String statsResponse =  Request.Get("https://studies.cs.helsinki.fi/ohtustats/stats").execute().returnContent().asString();

        JsonParser parser = new JsonParser();
    	JsonObject parsittuData = parser.parse(statsResponse).getAsJsonObject();
    	int total_exercs = 0;
    	for (Map.Entry<String,JsonElement> asdf : parsittuData.entrySet()) {
    		JsonObject jo = asdf.getValue().getAsJsonObject();
    		total_exercs += jo.get("exercise_total").getAsInt();
    	}

        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );

        System.out.println("Kurssi: Ohjelmistotuotanto, syksy 2017");
        System.out.println();
        System.out.println("opiskelijanumero: " + studentNr);
        System.out.println();
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        
        int exercs = 0;
        int hours = 0;

        System.out.println("Oliot:");
        for (Submission submission : subs) {
            System.out.println(submission);
            exercs += submission.getExercises().size();
            hours += submission.getHours();
        }
        System.out.println();
        System.out.println("yhteensä: " + exercs + " tehtävää " + hours + " tuntia");
        System.out.println();
        System.out.println("kurssilla yhteensä " + parsittuData.entrySet().size() + " palautusta, palautettuja tehtäviä " + total_exercs + "kpl");


    }
}