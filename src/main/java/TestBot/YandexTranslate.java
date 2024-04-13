package TestBot;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.HashMap;


public class YandexTranslate {
    public static void main(String[] args) {
        final String iAmToken = System.getenv("iAmToken");
        final String folderId = System.getenv("folderId");
        String target_language = "ru";
        String[] texts = {"Hello", "World!"};

        Map<String, Object> body = new HashMap<>();
        body.put("targetLanguageCode", target_language);
        JsonArray textArray = new JsonArray();
        for (String text : texts) {
            textArray.add(text);
        }
        body.put("texts", textArray);
        body.put("folderId", folderId);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer " + iAmToken);

        try {
            URL url = new URL("https://translate.api.cloud.yandex.net/translate/v2/translate");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            JsonObject jsonBody = new JsonObject();
            for (Map.Entry<String, Object> entry : body.entrySet()) {
                jsonBody.add(entry.getKey(), JsonParser.parseString(entry.getValue().toString()));
            }

            byte[] outputInBytes = jsonBody.toString().getBytes("UTF-8");
            connection.getOutputStream().write(outputInBytes);

            for (Map.Entry<String, String> header : headers.entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JsonObject responseJson = JsonParser.parseString(new String(connection.getInputStream().readAllBytes())).getAsJsonObject();
                System.out.println(responseJson.toString());
            } else {
                System.out.println("HTTP response code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
