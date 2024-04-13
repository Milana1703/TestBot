package TestBot;

/**
 * Расширение класса <code>DialogResponses</code>
 * Реализует необходимые поля и методы для обработки команд
 */

public class DialogCommandResponses //extends DialogResponses
{
    // конструктор кароче

    /* public String name;
    public String description;

    public BotCommandResponse(Long chatId){
        super("", chatId);
    }
*/
    public static String StartCommand()
    {
        return "Привет \uD83C\uDF1A \n" +
                "   Я telegram-бот, люди создали меня, чтобы я помогал им в изучении английского языка \n" +
                "   Я появился совсем недавно и еще немногому успел научиться.. но мои создатели каждый день стараются сделать меня лучше) \n" +
                "   С помощью команды /help ты можешь ознакомиться со всем доступным функционалом и узнать как со мной взаимодействовать :з \n" +
                "   Надеюсь, я буду полезен для тебя \uD83D\uDC49\uD83C\uDFFC\uD83D\uDC48\uD83C\uDFFC";
    }
    public static String HelpCommand()
    {
        return "Вот, чему меня пока что научили : \n" +
                "/start – выводит стартовое сообщение \n" +
                "/help – демонстрирует список доступных для взаимодействия с ботом команд \n" +
                "/anecdote – шутит смешнявку \n";
    }
    public static String AnecdoteCommand()
    {
        return "Работники дорожных служб клали асфальт, один дорожник упал, теперь он внедорожник ;)";
    }
}
