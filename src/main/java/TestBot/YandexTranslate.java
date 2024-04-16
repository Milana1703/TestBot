package TestBot;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class YandexTranslate {
    private static final String REGEX = "text\"\s*:\s*\"([^\"]+)";

    /**
     * Данный метод предназначен для получения значения из поля "text":
     * при получении результата
     */
    private static String getTranslateText(StringBuilder t) {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(t);
        while (matcher.find()) {
            sb.append(matcher.group(1)).append(" ");
        }
        return sb.toString();
    }

    /**
     * метод предназначенный для перевода теста
     * @param text - текст, который требуется перевести
     * @return Возвращает переведённую строку (на русском) заданного текста
     */
    public static String translate(String language, String text) {
        String urlAddress = "https://translate.api.cloud.yandex.net/translate/v2/translate";
        final String iAmToken = System.getenv("iAmToken");
        final String folderId = System.getenv("folderId");
        String targetLanguage = language;          // target - цель
        // String[] texts = {"Hello", "I am working!"};

        OutputStream outputStream = null;
        BufferedReader bufferedReader = null;
        String[] texts = text.split(" ");

        StringBuilder response = new StringBuilder();
        JsonObject body = new JsonObject();
        body.addProperty("targetLanguageCode", targetLanguage);
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

            outputStream = connection.getOutputStream();
            byte[] input = body.toString().getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while ((inputLine = bufferedReader.readLine()) != null) {
                    response.append(inputLine);
                }

            } else {
                System.out.println("HTTP response code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(bufferedReader).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Objects.requireNonNull(outputStream).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return getTranslateText(response);
    }

//    public static void main(String[] args) {
//        System.out.println(translate("Melanin"));
//    }
}