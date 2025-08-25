package OpenAssets.demo.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {
@Override
public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST")
            .allowedHeaders("*");
}

    @Value("${app.storage.path}")
    private String storagePath;

@Override
public void addResourceHandlers(ResourceHandlerRegistry registry){
    String fileSystemPathUri = Paths.get(storagePath).toUri().toString();

    String finalLocation = fileSystemPathUri.endsWith("/") ? fileSystemPathUri : fileSystemPathUri + "/";

    registry.addResourceHandler("/files/**")
            .addResourceLocations(finalLocation);
}

}
