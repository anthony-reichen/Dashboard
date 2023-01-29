package dashboard.dashboardbackend.tests;

import com.google.gson.JsonParser;

import dashboard.dashboardbackend.utils.JwtUtils;

public class JwtUtilsTest {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJmaXJzdE5hbWUiOiJQYXNjYWwiLCJsYXN0TmFtZSI6IkJldHRpIiwiZW1haWwiOiJwYXNjYWxAZXBpdGVjaC5ldSIsInN1YiI6InBhc2NhbEBlcGl0ZWNoLmV1IiwianRpIjoiNjM2YmQ1NGM1ZTEyNzIxOWZmYTUxNzEzIiwiaWF0IjoxNjY4MDExMzU2LCJleHAiOjE2NjgwMTE2NTZ9.D1pMGNI8aR09o9850lbuohv0RByWgeW58qGos-sQ1C4";
        String payload = JwtUtils.getEmail(token);
        System.out.println(JsonParser.parseString(payload).getAsJsonObject().get("email"));
    }
}
