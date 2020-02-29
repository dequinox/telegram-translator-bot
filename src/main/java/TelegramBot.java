import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.Map;

public class TelegramBot extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {
        Map<String, String> langs = null;
        try {
            langs = TranslatorAPI.getLangs();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String input = update.getMessage().getText();

        String source = TranslatorAPI.getKey(langs, "russian");
        try {
            source = TranslatorAPI.detectLanguage(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String target = TranslatorAPI.getKey(langs, "english");
        if (langs.get(source) == langs.get(target))
        {
            target = TranslatorAPI.getKey(langs, "russian");
        }

        String output = null;
        try {
            output = TranslatorAPI.translate(input, source, target);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Detected lang: " + source + " (" + langs.get(source) + ")");

        System.out.println("Input: " + input);
        System.out.println("Output: " + output);

        System.out.println("Source: " + source);
        System.out.println("Target: " + target);

        System.out.println("langs.get(source): " + langs.get(source));
        System.out.println("langs.get(target): " + langs.get(target));
        System.out.println("TranslateAPI.getKey(langs, \"english\"): " + TranslatorAPI.getKey(langs, "english"));

        sendMsg(update.getMessage().getChatId().toString(), output);
    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId);
        message.setText(s);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            //log.log(Level.SEVERE, "Exception: ", e.toString());
        }
    }


    public String getBotUsername() {
        return "dev_translator_bot";
    }

    public String getBotToken() {
        return "1012476690:AAFwQ5coMPAuTOgd-07cbNXW1SndLbrDndU";
    }
}
