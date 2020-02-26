import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBot extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
    }

    public String getBotUsername() {
        return "dev_translator_bot";
    }

    public String getBotToken() {
        return "1012476690:AAFwQ5coMPAuTOgd-07cbNXW1SndLbrDndU";
    }
}
