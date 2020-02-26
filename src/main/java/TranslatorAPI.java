import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TranslatorAPI {
    public static final String API_KEY = "trnsl.1.1.20200226T160535Z.020875383576e81c.6dd6494b3008021c5beb59f70490c7ecc320acf5";

    private static String request(String URL) throws IOException {
        java.net.URL url = new URL(URL);
        URLConnection urlConn = url.openConnection();
        urlConn.addRequestProperty("User-Agent", "Mozilla");

        InputStream inStream = urlConn.getInputStream();

        String recieved = new BufferedReader(new InputStreamReader(inStream)).readLine();

        inStream.close();
        return recieved;
    }

    public static Map<String, String> getLangs() throws IOException {
        String langs = request("https://translate.yandex.net/api/v1.5/tr.json/getLangs?key=" + API_KEY + "&ui=en");
        langs = langs.substring(langs.indexOf("langs")+7);
        langs = langs.substring(0, langs.length()-1);

        String[] splitLangs = langs.split(",");

        Map<String, String> languages = new HashMap<String, String>();
        for (String s : splitLangs) {
            String[] s2 = s.split(":");

            String key = s2[0].substring(1, s2[0].length()-1);
            String value = s2[1].substring(1, s2[1].length()-1);

            languages.put(key, value);
        }
        return languages;
    }
}
