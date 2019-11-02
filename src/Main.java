

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import org.json.*;
import java.lang.String;
import java.util.Random;


public class Main {

    public static String jsonString = "{\"youPlay\": 2, \"field\": [[0,0,0], [0,0,0], [0,0,0]], \"winner\": 0}";
    public static int[][] arr = new int[3][3];
    public static Integer winner = 0;
    public static void main(String[] args) throws IOException, InterruptedException,JSONException {
        //отправляем запрос на сервер
        for(int k = 0; k < 5; k++){
        HttpClient client = HttpClient.newHttpClient();
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
            if(winner == 1){
                System.out.println("Победил игрок № 1");
                break;
            }
            if(winner == 2){
                System.out.println("Победил игрок № 2");
                break;
            }
            if(winner == 3){
                System.out.println("Ничья");
                break;
            }
        JSONArray arrJson = obj.getJSONArray("field");

        //заполняем двумерный массив
        for (int i = 0; i < arrJson.length(); i++) {
            JSONArray arrJson2 = arrJson.getJSONArray(i);
            for (int j = 0; j < 3; j++) {
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

        //определяем стратегию победителя


        //делаем ход
        Random rnd = new Random(System.currentTimeMillis());
        outerloop:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] == 0) {
                    arr[i][j] = youPlay;
                    break outerloop;
                }
            }
        }

        //самопреверка хода
        for (int i = 0; i < 3; i++) {  //идём по строкам
            for (int j = 0; j < 3; j++) {//идём по столбцам
                System.out.print(" " + arr[i][j] + " "); //вывод элемента
            }
            System.out.println();//перенос строки ради визуального сохранения табличной формы
        }

        //конвертируем в JSON
        JSONArray jsArr = new JSONArray(arr);
        System.out.println(jsArr.toString());
        jsonString = "{\"youPlay\": 2, \"field\": " + jsArr + ", \"winner\": 0}";
        System.out.println(jsonString);


        //повторяем весь процесс
        }
    }
}