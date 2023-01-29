package dashboard.dashboardbackend.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class AppConf implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/user/login")
                .allowedOrigins("*")
                .allowedMethods("GET").allowedMethods("POST").allowedMethods("PATCH").allowedMethods("DELETE");
        registry.addMapping("/user/email/*")
                .allowedOrigins("*")
                .allowedMethods("GET").allowedMethods("POST").allowedMethods("PATCH").allowedMethods("DELETE");
        registry.addMapping("/user/id/*")
                .allowedOrigins("*")
                .allowedMethods("GET").allowedMethods("POST").allowedMethods("PATCH").allowedMethods("DELETE");
        registry.addMapping("/user/weather/addreport/*")
                .allowedOrigins("*")
                .allowedMethods("GET").allowedMethods("POST").allowedMethods("PATCH").allowedMethods("DELETE");
        registry.addMapping("/user/weather/edit/*/*")
                .allowedOrigins("*")
                .allowedMethods("GET").allowedMethods("POST").allowedMethods("PATCH").allowedMethods("DELETE");
        registry.addMapping("/user/all")
                .allowedOrigins("*")
                .allowedMethods("GET").allowedMethods("POST").allowedMethods("PATCH").allowedMethods("DELETE");
        registry.addMapping("/user/getWidgets")
                .allowedOrigins("*")
                .allowedMethods("GET").allowedMethods("POST").allowedMethods("PATCH").allowedMethods("DELETE");
        registry.addMapping("/weather/report/*")
                .allowedOrigins("*")
                .allowedMethods("GET").allowedMethods("POST").allowedMethods("PATCH").allowedMethods("DELETE");
        registry.addMapping("/weather/forecast/*")
                .allowedOrigins("*")
                .allowedMethods("GET").allowedMethods("POST").allowedMethods("PATCH").allowedMethods("DELETE");
    }
}