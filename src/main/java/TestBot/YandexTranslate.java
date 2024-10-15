package TestBot;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import TestBot.YandexAPIResponse.Translation;

import java.net.URL;
import java.net.HttpURLConnection;
import java.util.List;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Класс, предназначенный для корректного использования и обработки ответов на запросы Yandex Translate API.
 *  Атрибуты
 *  --------
 *  String text:
 *     Текст, который необходимо перевести.
 *  Медоты
 *  ------
 *  private static String getTranslateText(StringBuilder t)
 *  public static String translate(String language, String text)
 */

public class YandexTranslate {
    private static final Gson gson = new Gson();

    /**
     * Получение значения из поля "text" при получении результата
     */
    private static String getTranslateText(StringBuilder t) {
        YandexAPIResponse response = gson.fromJson(t.toString(), YandexAPIResponse.class);
        List<Translation> translations = response.getTranslations();
        StringBuilder resultText = new StringBuilder();
        for (Translation translation : translations) {
            resultText.append(translation.getText()).append(" ");
        }
        return resultText.deleteCharAt(resultText.length() - 1).toString();
    }

    /**
     * Перевод теста
     * @param languageTo - язык, НА который требуется перевести текст
     * @param text - текст, который требуется перевести
     * @return Возвращает переведённую строку (на русском) заданного текста
     */
    public static String translate(String languageTo, String text) {
        String urlAddress = "https://translate.api.cloud.yandex.net/translate/v2/translate";
        final String iAmToken = System.getenv("iAmToken");
        final String folderId = System.getenv("folderId");
        String[] texts = text.split(" ");

        StringBuilder response = new StringBuilder();
        JsonObject body = new JsonObject();
        body.addProperty("targetLanguageCode", languageTo);
        JsonArray textsArray = new JsonArray();
        for (String t : texts) {
            textsArray.add(t);
        }
        body.add("texts", textsArray);
        body.addProperty("folderId", folderId);

        try {
            URL url = new URL(urlAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + iAmToken);

            try (OutputStream outputStream = connection.getOutputStream();
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                byte[] input = body.toString().getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    String inputLine;
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        response.append(inputLine);
                    }
                } else {
                    System.out.println("HTTP response code: " + responseCode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getTranslateText(response);
    }
}