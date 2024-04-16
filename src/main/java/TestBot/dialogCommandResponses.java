package TestBot;

/**
 * Расширение класса <code>DialogResponses</code>
 * Реализует необходимые поля и методы для обработки команд
 */

public class dialogCommandResponses //extends DialogResponses
{

// конструктор кароче
//    public String name;
//    public String description;
//
//    public BotCommandResponse(Long chatId){
//        super("", chatId);
//    }

    public String StartCommand()
    {
        return """
                Привет \uD83C\uDF1A\s
                   Я telegram-бот, люди создали меня, чтобы я помогал им в изучении английского языка\s
                   Я появился совсем недавно и еще немногому успел научиться.. но мои создатели каждый день стараются сделать меня лучше)\s
                   С помощью команды /help ты можешь ознакомиться со всем доступным функционалом и узнать как со мной взаимодействовать :з\s
                   Надеюсь, я буду полезен для тебя \uD83D\uDC49\uD83C\uDFFC\uD83D\uDC48\uD83C\uDFFC""";
    }
    public String HelpCommand()
    {
        return """
                Вот, чему меня пока что научили :\s
                /start – вывод стартового сообщение\s
                /help – демонстрирация списка доступных для взаимодействия с ботом команд\s
                /translate – включение режима простого переводчика\s
                /language – смена языка перевода (RU-EN)\s
                /stop – выход из режима переводчика\s
                /anecdote – шутение смешнявки\s
                """;
    }
//    public static String ChangeLanguage(){
//        pass;
//    }
    public String TranslateCommand(String language, String text){
        return YandexTranslate.translate(language, text);
    }

    public String AnecdoteCommand()
    {
        return "Работники дорожных служб клали асфальт, один дорожник упал, теперь он внедорожник ;)";
    }
}
