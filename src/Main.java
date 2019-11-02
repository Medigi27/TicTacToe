

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import org.json.*;
import java.lang.String;
import java.util.ArrayList;


public class Main {

    public static String jsonString = "{\"youPlay\": 2, \"field\": [[0,0,0], [0,0,0], [0,0,0]], \"winner\": 0}";
    public static int[][] arr = new int[3][3];


    public static void main(String[] args) throws IOException, InterruptedException,JSONException {
        HttpClient client = HttpClient.newHttpClient();
        //отправляем запрос на сервер
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://tic-tac-toe-2019.herokuapp.com/play/"))
                .timeout(Duration.ofMinutes(1))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonString))
                .build();
        //получаем ответ сервера
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        String jsonStringServer = response.body();
        System.out.println(jsonStringServer);

        //парсим json
        JSONObject obj = new JSONObject(jsonStringServer);
        Integer youPlay = obj.getInt("youPlay");
        System.out.println(youPlay);
        Integer winner = obj.getInt("winner");
        System.out.println(winner);
        JSONArray arrJson = obj.getJSONArray("field");

        for (int i = 0; i < arrJson.length(); i++) {
            JSONArray arrJson2 = arrJson.getJSONArray(i);
            for(int j = 0; j < 3; j++){
                arr[i][j] = arrJson2.getInt(j);
            }
        }

        System.out.println(arrJson.get(0).toString());
        //System.out.println(arr.toString());

        for (int i = 0; i < 3; i++) {  //идём по строкам
            for (int j = 0; j < 3; j++) {//идём по столбцам
                System.out.print(" " + arr[i][j] + " "); //вывод элемента
            }
            System.out.println();//перенос строки ради визуального сохранения табличной формы
        }

    }
}