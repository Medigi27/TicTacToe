

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;


public class Main {

    public static String net = "{\"youPlay\": 2, \"field\": [[0,0,0], [0,0,0], [0,0,0]], \"winner\": 0}";


    public static void main(String[] args) throws IOException, InterruptedException  {
        HttpClient client = HttpClient.newHttpClient();


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://tic-tac-toe-2019.herokuapp.com/play/"))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(net))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        System.out.print(response.body());
    }
}
