package dashboard.dashboardbackend.utils;

import java.util.Base64;

import com.google.gson.JsonParser;

public class JwtUtils {
    public static String getEmail(String token) {
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));
        String res = JsonParser.parseString(payload).getAsJsonObject().get("email").toString();
        res = res.replace("\"", "");
        return res;
    }
}
