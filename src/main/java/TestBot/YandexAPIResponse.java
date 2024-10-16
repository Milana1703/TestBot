package TestBot;

import java.util.List;

public class YandexAPIResponse {
    private List<Translation> translations;

    public YandexAPIResponse() {
    }

    public List<Translation> getTranslations() {
        return translations;
    }

    public static class Translation {

        private String text;

        public Translation() {
        }

        public String getText() {
            return text;
        }
    }
}
